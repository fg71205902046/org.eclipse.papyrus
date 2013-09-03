/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.cdo.uml.search.internal.ui.query;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.cdo.util.CDOURIUtil;
import org.eclipse.emf.cdo.view.CDOQuery;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.papyrus.cdo.core.resource.CDOAwareModelSet;
import org.eclipse.papyrus.cdo.internal.core.CDOUtils;
import org.eclipse.papyrus.cdo.internal.core.IInternalPapyrusRepository;
import org.eclipse.papyrus.cdo.internal.core.PapyrusRepositoryManager;
import org.eclipse.papyrus.cdo.uml.search.internal.ui.Activator;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForResourceInitializerService;
import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForResourceSet;
import org.eclipse.papyrus.infra.services.labelprovider.service.LabelProviderService;
import org.eclipse.papyrus.infra.services.labelprovider.service.impl.LabelProviderServiceImpl;
import org.eclipse.papyrus.infra.services.openelement.service.OpenElementService;
import org.eclipse.papyrus.infra.services.openelement.service.impl.OpenElementServiceImpl;
import org.eclipse.papyrus.uml.search.ui.query.AbstractPapyrusQuery;
import org.eclipse.papyrus.uml.search.ui.query.CompositePapyrusQuery;
import org.eclipse.papyrus.uml.search.ui.query.IPapyrusQueryProvider;
import org.eclipse.papyrus.uml.search.ui.query.QueryInfo;
import org.eclipse.papyrus.views.search.utils.DefaultServiceRegistryTracker;
import org.eclipse.papyrus.views.search.utils.IServiceRegistryTracker;
import org.eclipse.uml2.uml.UMLPackage;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;


/**
 * A search-query provider for CDO model repositories.
 */
public class CDOSearchQueryProvider implements IPapyrusQueryProvider {

	public CDOSearchQueryProvider() {
		super();
	}

	@Override
	public boolean canProvideFor(URI scope) {
		return CDOUtils.isCDOURI(scope);
	}

	@Override
	public AbstractPapyrusQuery createSimpleSearchQuery(QueryInfo queryInfo) {
		String searchPattern = PatternUtil.wrap(queryInfo.getQueryText(), queryInfo.isCaseSensitive(), queryInfo.isRegularExpression());
		final boolean isRegexMatch = searchPattern != null;
		if(!isRegexMatch) {
			searchPattern = queryInfo.getQueryText();
		}

		IServiceRegistryTracker tracker = new DefaultServiceRegistryTracker();

		Multimap<CDOView, URI> views = getViews(queryInfo.getScope());
		List<AbstractPapyrusQuery> result = Lists.newArrayListWithCapacity(views.keySet().size());
		for(CDOView view : views.keySet()) {
			Map<String, Object> parameters = Maps.newHashMap();
			String ocl = createOCLExpression(searchPattern, isRegexMatch, queryInfo.isSearchAllStringAttributes(), views.get(view), parameters);
			CDOQuery query = view.createQuery("ocl", ocl, UMLPackage.Literals.NAMED_ELEMENT);

			// variables referenced by the OCL query expression
			for(Map.Entry<String, ?> next : parameters.entrySet()) {
				query.setParameter(next.getKey(), next.getValue());
			}

			// parameters for the server-side OCL query handler
			query.setParameter("cdoImplicitRootClass", EcorePackage.Literals.EOBJECT);

			AbstractPapyrusQuery searchQuery = new CDOPapyrusQuery(queryInfo.getQueryText(), view, query);
			result.add(searchQuery);

			try {
				// automatically clean up the view and services registry when no longer needed
				tracker.track(searchQuery, ServiceUtilsForResourceSet.getInstance().getServiceRegistry(view.getResourceSet()));
			} catch (ServiceException e) {
				Activator.log.error("Cannot track services registry for automatic clean-up.", e); //$NON-NLS-1$
			}
		}

		return CompositePapyrusQuery.compose(result);
	}

	@Override
	public AbstractPapyrusQuery createAdvancedSearchQuery(QueryInfo queryInfo) {
		// TODO Auto-generated method stub
		return AbstractPapyrusQuery.Empty.INSTANCE;
	}

	protected Multimap<CDOView, URI> getViews(Collection<URI> scope) {
		Multimap<CDOView, URI> result = HashMultimap.create();
		Map<IInternalPapyrusRepository, CDOView> views = Maps.newHashMap();

		try {
			for(URI uri : scope) {
				IInternalPapyrusRepository repo = PapyrusRepositoryManager.INSTANCE.getRepositoryForURI(uri);
				if((repo != null) && repo.isConnected()) {
					CDOView view = views.get(repo);
					if(view == null) {
						// no view, yet, for this repo

						ServicesRegistry services = new ServicesRegistry();
						services.add(LabelProviderService.class, 10, new LabelProviderServiceImpl());
						services.add(OpenElementService.class, 10, new OpenElementServiceImpl());
						services.startRegistry();

						services.add(ModelSet.class, 10, new CDOAwareModelSet(PapyrusRepositoryManager.INSTANCE));
						services.add(ServiceUtilsForResourceInitializerService.class, 10, new ServiceUtilsForResourceInitializerService());
						services.startServicesByClassKeys(ModelSet.class, ServiceUtilsForResourceInitializerService.class);

						// create our own transaction for the model-set
						view = repo.getCDOView(repo.createTransaction(ServiceUtils.getInstance().getModelSet(services)));
						views.put(repo, view);
					}
					result.put(view, uri);
				}
			}
		} catch (ServiceException e) {
			Activator.log.error("Failed to initialize service registry for CDO search query.", e); //$NON-NLS-1$
		}

		return result;
	}

	protected String createOCLExpression(String searchPattern, boolean isRegexMatch, boolean isAllStringAttributes, Collection<URI> scope, Map<String, Object> parameters) {
		StringBuilder result = new StringBuilder();

		// parameters to pass through to OCL
		parameters.put("searchPattern", searchPattern); //$NON-NLS-1$

		// first, build the CDOResource.allInstances() select clause for the scope
		StringBuilder scopeClause = new StringBuilder();
		boolean first = true;
		for(URI uri : scope) {
			String path = CDOURIUtil.extractResourcePath(uri);
			if(uri.hasTrailingPathSeparator() && !path.endsWith("/")) { //$NON-NLS-1$
				path = path + "/"; //$NON-NLS-1$
			}
			if((path.length() > 1) || (!path.startsWith("/") && (path.length() > 0))) { //$NON-NLS-1$
				if(first) {
					first = false;
				} else {
					scopeClause.append(" or "); //$NON-NLS-1$
				}

				scopeClause.append("r.path.startsWith('"); //$NON-NLS-1$
				scopeClause.append(oclQuoteString(path));
				scopeClause.append("')"); //$NON-NLS-1$
			}
		}

		// based on the CDOResource scope clause, find the candidate NamedElements
		if(scopeClause.length() == 0) {
			// easy case.  Do an allInstances() query
			result.append("NamedElement.allInstances()"); //$NON-NLS-1$
		} else {
			// iterate the contents of resources matching the scope criteria
			result.append("eresource::CDOResource.allInstances()->select(r | ");

			result.append(scopeClause);

			// close the CDOResource.allInstances()->select(...) scope clause
			result.append(")"); //$NON-NLS-1$

			// and collect all of the NamedElements within those resources
			result.append("->collect(r | r.cdoAllProperContents(NamedElement))"); //$NON-NLS-1$
		}

		// from our candidate NamedElements, select those that match
		if(isAllStringAttributes) {
			result.append("->select(e | e.cdoMatches(searchPattern))"); //$NON-NLS-1$
		} else {
			result.append("->select(e | not e.name.oclIsUndefined() and e.name."); //$NON-NLS-1$
			if(isRegexMatch) {
				result.append("matches(searchPattern)"); //$NON-NLS-1$
			} else {
				result.append("indexOf(searchPattern) > 0"); //$NON-NLS-1$
			}

			// close the ->select(...)
			result.append(")"); //$NON-NLS-1$
		}

		return result.toString();
	}

	static String oclQuoteString(String s) {
		return s.replace("'", "\\'") //$NON-NLS-1$ //$NON-NLS-2$
		.replace("\\", "\\\\"); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
