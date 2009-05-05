/*******************************************************************************
 * Copyright (c) 2009 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.papyrus.tabbedproperties.uml.parts.forms;

// Start of user code for imports

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;

import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.papyrus.tabbedproperties.uml.providers.UMLMessages;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.api.policies.IPropertiesEditionPolicy;
import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionPolicyProvider;
import org.eclipse.emf.eef.runtime.impl.policies.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.services.PropertiesEditionPolicyProviderService;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.emf.eef.runtime.api.parts.EEFMessageManager;
import org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.jface.viewers.StructuredSelection;
import java.util.Iterator;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFModelViewerDialog;
import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;
import org.eclipse.emf.eef.runtime.api.providers.IPropertiesEditionPolicyProvider;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.InterruptibleActivityRegion;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;
import java.util.Map;
import org.eclipse.emf.eef.runtime.impl.utils.EMFListEditUtil;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.LinkEndData;
import org.eclipse.uml2.uml.InputPin;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.emf.eef.runtime.ui.widgets.EMFComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import org.eclipse.papyrus.tabbedproperties.uml.parts.UMLViewsRepository;

// End of user code
/**
 * @author <a href="mailto:jerome.benois@obeo.fr">Jerome Benois</a>
 */
public class CreateLinkActionPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, CreateLinkActionPropertiesEditionPart {

	private EMFListEditUtil ownedCommentEditUtil;

	private ReferencesTable<?> ownedComment;

	private Text name;

	private EMFComboViewer visibility;

	private EMFListEditUtil clientDependencyEditUtil;

	private ReferencesTable<?> clientDependency;

	private Button isLeaf;

	private EMFListEditUtil outgoingEditUtil;

	private ReferencesTable<?> outgoing;

	private EMFListEditUtil incomingEditUtil;

	private ReferencesTable<?> incoming;

	private EMFListEditUtil inPartitionEditUtil;

	private ReferencesTable<?> inPartition;

	private EMFListEditUtil inInterruptibleRegionEditUtil;

	private ReferencesTable<?> inInterruptibleRegion;

	private EMFListEditUtil redefinedNodeEditUtil;

	private ReferencesTable<?> redefinedNode;

	private EMFListEditUtil handlerEditUtil;

	private ReferencesTable<?> handler;

	private EMFListEditUtil localPreconditionEditUtil;

	private ReferencesTable<?> localPrecondition;

	private EMFListEditUtil localPostconditionEditUtil;

	private ReferencesTable<?> localPostcondition;

	private EMFListEditUtil endDataEditUtil;

	private ReferencesTable<?> endData;

	private EMFListEditUtil inputValueEditUtil;

	private ReferencesTable<?> inputValue;

	public CreateLinkActionPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	public Composite createFigure(final Composite parent, final FormToolkit widgetFactory) {
		ScrolledForm scrolledForm = widgetFactory.createScrolledForm(parent);
		Form form = scrolledForm.getForm();
		view = form.getBody();
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		view.setLayout(layout);
		createControls(widgetFactory, view, new EEFMessageManager(scrolledForm, widgetFactory));
		return scrolledForm;
	}

	public void createControls(final FormToolkit widgetFactory, Composite view, IMessageManager messageManager) {
		this.messageManager = messageManager;
		createPropertiesGroup(widgetFactory, view);
		// Start of user code for additional ui definition

		// End of user code
	}

	protected void createPropertiesGroup(FormToolkit widgetFactory, final Composite view) {
		Section propertiesSection = widgetFactory.createSection(view, Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		propertiesSection.setText(UMLMessages.CreateLinkActionPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesSectionData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesSectionData.horizontalSpan = 3;
		propertiesSection.setLayoutData(propertiesSectionData);
		Composite propertiesGroup = widgetFactory.createComposite(propertiesSection);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		createOwnedCommentTableComposition(widgetFactory, propertiesGroup);
		createNameText(widgetFactory, propertiesGroup);
		createVisibilityEEnumViewer(widgetFactory, propertiesGroup);
		createClientDependencyReferencesTable(widgetFactory, propertiesGroup);
		createIsLeafCheckbox(widgetFactory, propertiesGroup);
		createOutgoingReferencesTable(widgetFactory, propertiesGroup);
		createIncomingReferencesTable(widgetFactory, propertiesGroup);
		createInPartitionReferencesTable(widgetFactory, propertiesGroup);
		createInInterruptibleRegionReferencesTable(widgetFactory, propertiesGroup);
		createRedefinedNodeReferencesTable(widgetFactory, propertiesGroup);
		createHandlerTableComposition(widgetFactory, propertiesGroup);
		createLocalPreconditionTableComposition(widgetFactory, propertiesGroup);
		createLocalPostconditionTableComposition(widgetFactory, propertiesGroup);
		createEndDataTableComposition(widgetFactory, propertiesGroup);
		createInputValueTableComposition(widgetFactory, propertiesGroup);
		propertiesSection.setClient(propertiesGroup);
	}

	/**
	 * @param container
	 */
	protected void createOwnedCommentTableComposition(FormToolkit widgetFactory, Composite parent) {
		this.ownedComment = new ReferencesTable<Comment>(UMLMessages.CreateLinkActionPropertiesEditionPart_OwnedCommentLabel, new ReferencesTableListener<Comment>() {

			public void handleAdd() {
				addToOwnedComment();
			}

			public void handleEdit(Comment element) {
				editOwnedComment(element);
			}

			public void handleMove(Comment element, int oldIndex, int newIndex) {
				moveOwnedComment(element, oldIndex, newIndex);
			}

			public void handleRemove(Comment element) {
				removeFromOwnedComment(element);
			}

			public void navigateTo(Comment element) {
			}
		});
		this.ownedComment.setHelpText(propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.ownedComment, UMLViewsRepository.FORM_KIND));
		this.ownedComment.createControls(parent, widgetFactory);
		GridData ownedCommentData = new GridData(GridData.FILL_HORIZONTAL);
		ownedCommentData.horizontalSpan = 3;
		this.ownedComment.setLayoutData(ownedCommentData);
	}

	/**
	 * 
	 */
	protected void moveOwnedComment(Comment element, int oldIndex, int newIndex) {
		EObject editedElement = ownedCommentEditUtil.foundCorrespondingEObject(element);
		ownedCommentEditUtil.moveElement(element, oldIndex, newIndex);
		ownedComment.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.ownedComment,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));

	}

	/**
	 * 
	 */
	protected void addToOwnedComment() {

		// Start of user code addToOwnedComment() method body

		Comment eObject = UMLFactory.eINSTANCE.createComment();
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(eObject);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(eObject);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(propertiesEditionComponent, eObject, resourceSet));
			if (propertiesEditionObject != null) {
				ownedCommentEditUtil.addElement(propertiesEditionObject);
				ownedComment.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.ownedComment,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, propertiesEditionObject));
			}
		}

		// End of user code
	}

	/**
	 * 
	 */
	protected void removeFromOwnedComment(Comment element) {

		// Start of user code for the removeFromOwnedComment() method body

		EObject editedElement = ownedCommentEditUtil.foundCorrespondingEObject(element);
		ownedCommentEditUtil.removeElement(element);
		ownedComment.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.ownedComment,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

		// End of user code
	}

	/**
	 * 
	 */
	protected void editOwnedComment(Comment element) {

		// Start of user code editOwnedComment() method body

		EObject editedElement = ownedCommentEditUtil.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element, resourceSet));
			if (propertiesEditionObject != null) {
				ownedCommentEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
				ownedComment.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.ownedComment,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
			}
		}

		// End of user code
	}

	protected void createNameText(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UMLMessages.CreateLinkActionPropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(UMLViewsRepository.CreateLinkAction.name,
				UMLViewsRepository.FORM_KIND));
		name = widgetFactory.createText(parent, ""); //$NON-NLS-1$
		name.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		widgetFactory.paintBordersFor(parent);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(nameData);
		name.addModifyListener(new ModifyListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
			 */
			public void modifyText(ModifyEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.name,
							PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, name.getText()));
			}

		});
		name.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 */
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.name,
							PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
			}

		});
		name.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 */
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.name,
								PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.name, UMLViewsRepository.FORM_KIND), null); //$NON-NLS-1$

	}

	protected void createVisibilityEEnumViewer(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, UMLMessages.CreateLinkActionPropertiesEditionPart_VisibilityLabel, propertiesEditionComponent.isRequired(
				UMLViewsRepository.CreateLinkAction.visibility, UMLViewsRepository.FORM_KIND));
		visibility = new EMFComboViewer(parent);
		visibility.setContentProvider(new ArrayContentProvider());
		visibility.setLabelProvider(new AdapterFactoryLabelProvider(new EcoreAdapterFactory()));
		GridData visibilityData = new GridData(GridData.FILL_HORIZONTAL);
		visibility.getCombo().setLayoutData(visibilityData);
		visibility.addSelectionChangedListener(new ISelectionChangedListener() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.visibility,
							PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, getVisibility()));
			}

		});
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.visibility, UMLViewsRepository.FORM_KIND), null); //$NON-NLS-1$
	}

	protected void createClientDependencyReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.clientDependency = new ReferencesTable<Dependency>(UMLMessages.CreateLinkActionPropertiesEditionPart_ClientDependencyLabel, new ReferencesTableListener<Dependency>() {

			public void handleAdd() {
				ViewerFilter clientDependencyFilter = new EObjectFilter(UMLPackage.eINSTANCE.getDependency());
				ViewerFilter viewerFilter = new ViewerFilter() {

					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!clientDependencyEditUtil.contains((EObject) element));
						return false;
					}
				};
				List filters = new ArrayList();
				filters.add(clientDependencyFilter);
				filters.add(viewerFilter);
				TabElementTreeSelectionDialog<Dependency> dialog = new TabElementTreeSelectionDialog<Dependency>(resourceSet, filters, "Dependency", UMLPackage.eINSTANCE.getDependency()) {

					@Override
					public void process(IStructuredSelection selection) {
						for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
							EObject elem = (EObject) iter.next();
							if (!clientDependencyEditUtil.getVirtualList().contains(elem))
								clientDependencyEditUtil.addElement(elem);
							propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this,
									UMLViewsRepository.CreateLinkAction.clientDependency, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
						}
						clientDependency.refresh();
					}
				};
				dialog.open();
			}

			public void handleEdit(Dependency element) {
				editClientDependency(element);
			}

			public void handleMove(Dependency element, int oldIndex, int newIndex) {
				moveClientDependency(element, oldIndex, newIndex);
			}

			public void handleRemove(Dependency element) {
				removeFromClientDependency(element);
			}

			public void navigateTo(Dependency element) {
			}
		});
		this.clientDependency.setHelpText(propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.clientDependency, UMLViewsRepository.FORM_KIND));
		this.clientDependency.createControls(parent, widgetFactory);
		GridData clientDependencyData = new GridData(GridData.FILL_HORIZONTAL);
		clientDependencyData.horizontalSpan = 3;
		this.clientDependency.setLayoutData(clientDependencyData);
		this.clientDependency.disableMove();
	}

	/**
	 * 
	 */
	protected void moveClientDependency(Dependency element, int oldIndex, int newIndex) {
		EObject editedElement = clientDependencyEditUtil.foundCorrespondingEObject(element);
		clientDependencyEditUtil.moveElement(element, oldIndex, newIndex);
		clientDependency.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.clientDependency,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));
	}

	/**
	 * 
	 */
	protected void removeFromClientDependency(Dependency element) {

		// Start of user code for the removeFromClientDependency() method body

		EObject editedElement = clientDependencyEditUtil.foundCorrespondingEObject(element);
		clientDependencyEditUtil.removeElement(element);
		clientDependency.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.clientDependency,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

		// End of user code
	}

	/**
	 * 
	 */
	protected void editClientDependency(Dependency element) {

		// Start of user code editClientDependency() method body

		EObject editedElement = clientDependencyEditUtil.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element, resourceSet));
			if (propertiesEditionObject != null) {
				clientDependencyEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
				clientDependency.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.clientDependency,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
			}
		}

		// End of user code
	}

	protected void createIsLeafCheckbox(FormToolkit widgetFactory, Composite parent) {
		isLeaf = widgetFactory.createButton(parent, UMLMessages.CreateLinkActionPropertiesEditionPart_IsLeafLabel, SWT.CHECK);
		isLeaf.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected(SelectionEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.isLeaf,
							PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, new Boolean(isLeaf.getSelection())));
			}

		});
		GridData isLeafData = new GridData(GridData.FILL_HORIZONTAL);
		isLeafData.horizontalSpan = 2;
		isLeaf.setLayoutData(isLeafData);
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.isLeaf, UMLViewsRepository.FORM_KIND), null); //$NON-NLS-1$
	}

	protected void createOutgoingReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.outgoing = new ReferencesTable<ActivityEdge>(UMLMessages.CreateLinkActionPropertiesEditionPart_OutgoingLabel, new ReferencesTableListener<ActivityEdge>() {

			public void handleAdd() {
				ViewerFilter outgoingFilter = new EObjectFilter(UMLPackage.eINSTANCE.getActivityEdge());
				ViewerFilter viewerFilter = new ViewerFilter() {

					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!outgoingEditUtil.contains((EObject) element));
						return false;
					}
				};
				List filters = new ArrayList();
				filters.add(outgoingFilter);
				filters.add(viewerFilter);
				TabElementTreeSelectionDialog<ActivityEdge> dialog = new TabElementTreeSelectionDialog<ActivityEdge>(resourceSet, filters, "ActivityEdge", UMLPackage.eINSTANCE.getActivityEdge()) {

					@Override
					public void process(IStructuredSelection selection) {
						for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
							EObject elem = (EObject) iter.next();
							if (!outgoingEditUtil.getVirtualList().contains(elem))
								outgoingEditUtil.addElement(elem);
							propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.outgoing,
									PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
						}
						outgoing.refresh();
					}
				};
				dialog.open();
			}

			public void handleEdit(ActivityEdge element) {
				editOutgoing(element);
			}

			public void handleMove(ActivityEdge element, int oldIndex, int newIndex) {
				moveOutgoing(element, oldIndex, newIndex);
			}

			public void handleRemove(ActivityEdge element) {
				removeFromOutgoing(element);
			}

			public void navigateTo(ActivityEdge element) {
			}
		});
		this.outgoing.setHelpText(propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.outgoing, UMLViewsRepository.FORM_KIND));
		this.outgoing.createControls(parent, widgetFactory);
		GridData outgoingData = new GridData(GridData.FILL_HORIZONTAL);
		outgoingData.horizontalSpan = 3;
		this.outgoing.setLayoutData(outgoingData);
		this.outgoing.disableMove();
	}

	/**
	 * 
	 */
	protected void moveOutgoing(ActivityEdge element, int oldIndex, int newIndex) {
	}

	/**
	 * 
	 */
	protected void removeFromOutgoing(ActivityEdge element) {

		// Start of user code for the removeFromOutgoing() method body

		EObject editedElement = outgoingEditUtil.foundCorrespondingEObject(element);
		outgoingEditUtil.removeElement(element);
		outgoing.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.outgoing,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

		// End of user code
	}

	/**
	 * 
	 */
	protected void editOutgoing(ActivityEdge element) {

		// Start of user code editOutgoing() method body

		EObject editedElement = outgoingEditUtil.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element, resourceSet));
			if (propertiesEditionObject != null) {
				outgoingEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
				outgoing.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.outgoing,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
			}
		}

		// End of user code
	}

	protected void createIncomingReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.incoming = new ReferencesTable<ActivityEdge>(UMLMessages.CreateLinkActionPropertiesEditionPart_IncomingLabel, new ReferencesTableListener<ActivityEdge>() {

			public void handleAdd() {
				ViewerFilter incomingFilter = new EObjectFilter(UMLPackage.eINSTANCE.getActivityEdge());
				ViewerFilter viewerFilter = new ViewerFilter() {

					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!incomingEditUtil.contains((EObject) element));
						return false;
					}
				};
				List filters = new ArrayList();
				filters.add(incomingFilter);
				filters.add(viewerFilter);
				TabElementTreeSelectionDialog<ActivityEdge> dialog = new TabElementTreeSelectionDialog<ActivityEdge>(resourceSet, filters, "ActivityEdge", UMLPackage.eINSTANCE.getActivityEdge()) {

					@Override
					public void process(IStructuredSelection selection) {
						for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
							EObject elem = (EObject) iter.next();
							if (!incomingEditUtil.getVirtualList().contains(elem))
								incomingEditUtil.addElement(elem);
							propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.incoming,
									PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
						}
						incoming.refresh();
					}
				};
				dialog.open();
			}

			public void handleEdit(ActivityEdge element) {
				editIncoming(element);
			}

			public void handleMove(ActivityEdge element, int oldIndex, int newIndex) {
				moveIncoming(element, oldIndex, newIndex);
			}

			public void handleRemove(ActivityEdge element) {
				removeFromIncoming(element);
			}

			public void navigateTo(ActivityEdge element) {
			}
		});
		this.incoming.setHelpText(propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.incoming, UMLViewsRepository.FORM_KIND));
		this.incoming.createControls(parent, widgetFactory);
		GridData incomingData = new GridData(GridData.FILL_HORIZONTAL);
		incomingData.horizontalSpan = 3;
		this.incoming.setLayoutData(incomingData);
		this.incoming.disableMove();
	}

	/**
	 * 
	 */
	protected void moveIncoming(ActivityEdge element, int oldIndex, int newIndex) {
	}

	/**
	 * 
	 */
	protected void removeFromIncoming(ActivityEdge element) {

		// Start of user code for the removeFromIncoming() method body

		EObject editedElement = incomingEditUtil.foundCorrespondingEObject(element);
		incomingEditUtil.removeElement(element);
		incoming.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.incoming,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

		// End of user code
	}

	/**
	 * 
	 */
	protected void editIncoming(ActivityEdge element) {

		// Start of user code editIncoming() method body

		EObject editedElement = incomingEditUtil.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element, resourceSet));
			if (propertiesEditionObject != null) {
				incomingEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
				incoming.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.incoming,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
			}
		}

		// End of user code
	}

	protected void createInPartitionReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.inPartition = new ReferencesTable<ActivityPartition>(UMLMessages.CreateLinkActionPropertiesEditionPart_InPartitionLabel, new ReferencesTableListener<ActivityPartition>() {

			public void handleAdd() {
				ViewerFilter inPartitionFilter = new EObjectFilter(UMLPackage.eINSTANCE.getActivityPartition());
				ViewerFilter viewerFilter = new ViewerFilter() {

					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!inPartitionEditUtil.contains((EObject) element));
						return false;
					}
				};
				List filters = new ArrayList();
				filters.add(inPartitionFilter);
				filters.add(viewerFilter);
				TabElementTreeSelectionDialog<ActivityPartition> dialog = new TabElementTreeSelectionDialog<ActivityPartition>(resourceSet, filters, "ActivityPartition", UMLPackage.eINSTANCE
						.getActivityPartition()) {

					@Override
					public void process(IStructuredSelection selection) {
						for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
							EObject elem = (EObject) iter.next();
							if (!inPartitionEditUtil.getVirtualList().contains(elem))
								inPartitionEditUtil.addElement(elem);
							propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this,
									UMLViewsRepository.CreateLinkAction.inPartition, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
						}
						inPartition.refresh();
					}
				};
				dialog.open();
			}

			public void handleEdit(ActivityPartition element) {
				editInPartition(element);
			}

			public void handleMove(ActivityPartition element, int oldIndex, int newIndex) {
				moveInPartition(element, oldIndex, newIndex);
			}

			public void handleRemove(ActivityPartition element) {
				removeFromInPartition(element);
			}

			public void navigateTo(ActivityPartition element) {
			}
		});
		this.inPartition.setHelpText(propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.inPartition, UMLViewsRepository.FORM_KIND));
		this.inPartition.createControls(parent, widgetFactory);
		GridData inPartitionData = new GridData(GridData.FILL_HORIZONTAL);
		inPartitionData.horizontalSpan = 3;
		this.inPartition.setLayoutData(inPartitionData);
		this.inPartition.disableMove();
	}

	/**
	 * 
	 */
	protected void moveInPartition(ActivityPartition element, int oldIndex, int newIndex) {
		EObject editedElement = inPartitionEditUtil.foundCorrespondingEObject(element);
		inPartitionEditUtil.moveElement(element, oldIndex, newIndex);
		inPartition.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.inPartition,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));
	}

	/**
	 * 
	 */
	protected void removeFromInPartition(ActivityPartition element) {

		// Start of user code for the removeFromInPartition() method body

		EObject editedElement = inPartitionEditUtil.foundCorrespondingEObject(element);
		inPartitionEditUtil.removeElement(element);
		inPartition.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.inPartition,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

		// End of user code
	}

	/**
	 * 
	 */
	protected void editInPartition(ActivityPartition element) {

		// Start of user code editInPartition() method body

		EObject editedElement = inPartitionEditUtil.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element, resourceSet));
			if (propertiesEditionObject != null) {
				inPartitionEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
				inPartition.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.inPartition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
			}
		}

		// End of user code
	}

	protected void createInInterruptibleRegionReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.inInterruptibleRegion = new ReferencesTable<InterruptibleActivityRegion>(UMLMessages.CreateLinkActionPropertiesEditionPart_InInterruptibleRegionLabel,
				new ReferencesTableListener<InterruptibleActivityRegion>() {

					public void handleAdd() {
						ViewerFilter inInterruptibleRegionFilter = new EObjectFilter(UMLPackage.eINSTANCE.getInterruptibleActivityRegion());
						ViewerFilter viewerFilter = new ViewerFilter() {

							public boolean select(Viewer viewer, Object parentElement, Object element) {
								if (element instanceof EObject)
									return (!inInterruptibleRegionEditUtil.contains((EObject) element));
								return false;
							}
						};
						List filters = new ArrayList();
						filters.add(inInterruptibleRegionFilter);
						filters.add(viewerFilter);
						TabElementTreeSelectionDialog<InterruptibleActivityRegion> dialog = new TabElementTreeSelectionDialog<InterruptibleActivityRegion>(resourceSet, filters,
								"InterruptibleActivityRegion", UMLPackage.eINSTANCE.getInterruptibleActivityRegion()) {

							@Override
							public void process(IStructuredSelection selection) {
								for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
									EObject elem = (EObject) iter.next();
									if (!inInterruptibleRegionEditUtil.getVirtualList().contains(elem))
										inInterruptibleRegionEditUtil.addElement(elem);
									propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this,
											UMLViewsRepository.CreateLinkAction.inInterruptibleRegion, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
								}
								inInterruptibleRegion.refresh();
							}
						};
						dialog.open();
					}

					public void handleEdit(InterruptibleActivityRegion element) {
						editInInterruptibleRegion(element);
					}

					public void handleMove(InterruptibleActivityRegion element, int oldIndex, int newIndex) {
						moveInInterruptibleRegion(element, oldIndex, newIndex);
					}

					public void handleRemove(InterruptibleActivityRegion element) {
						removeFromInInterruptibleRegion(element);
					}

					public void navigateTo(InterruptibleActivityRegion element) {
					}
				});
		this.inInterruptibleRegion.setHelpText(propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.inInterruptibleRegion, UMLViewsRepository.FORM_KIND));
		this.inInterruptibleRegion.createControls(parent, widgetFactory);
		GridData inInterruptibleRegionData = new GridData(GridData.FILL_HORIZONTAL);
		inInterruptibleRegionData.horizontalSpan = 3;
		this.inInterruptibleRegion.setLayoutData(inInterruptibleRegionData);
		this.inInterruptibleRegion.disableMove();
	}

	/**
	 * 
	 */
	protected void moveInInterruptibleRegion(InterruptibleActivityRegion element, int oldIndex, int newIndex) {
		EObject editedElement = inInterruptibleRegionEditUtil.foundCorrespondingEObject(element);
		inInterruptibleRegionEditUtil.moveElement(element, oldIndex, newIndex);
		inInterruptibleRegion.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.inInterruptibleRegion,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));
	}

	/**
	 * 
	 */
	protected void removeFromInInterruptibleRegion(InterruptibleActivityRegion element) {

		// Start of user code for the removeFromInInterruptibleRegion() method body

		EObject editedElement = inInterruptibleRegionEditUtil.foundCorrespondingEObject(element);
		inInterruptibleRegionEditUtil.removeElement(element);
		inInterruptibleRegion.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.inInterruptibleRegion,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

		// End of user code
	}

	/**
	 * 
	 */
	protected void editInInterruptibleRegion(InterruptibleActivityRegion element) {

		// Start of user code editInInterruptibleRegion() method body

		EObject editedElement = inInterruptibleRegionEditUtil.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element, resourceSet));
			if (propertiesEditionObject != null) {
				inInterruptibleRegionEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
				inInterruptibleRegion.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.inInterruptibleRegion,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
			}
		}

		// End of user code
	}

	protected void createRedefinedNodeReferencesTable(FormToolkit widgetFactory, Composite parent) {
		this.redefinedNode = new ReferencesTable<ActivityNode>(UMLMessages.CreateLinkActionPropertiesEditionPart_RedefinedNodeLabel, new ReferencesTableListener<ActivityNode>() {

			public void handleAdd() {
				ViewerFilter redefinedNodeFilter = new EObjectFilter(UMLPackage.eINSTANCE.getActivityNode());
				ViewerFilter viewerFilter = new ViewerFilter() {

					public boolean select(Viewer viewer, Object parentElement, Object element) {
						if (element instanceof EObject)
							return (!redefinedNodeEditUtil.contains((EObject) element));
						return false;
					}
				};
				List filters = new ArrayList();
				filters.add(redefinedNodeFilter);
				filters.add(viewerFilter);
				TabElementTreeSelectionDialog<ActivityNode> dialog = new TabElementTreeSelectionDialog<ActivityNode>(resourceSet, filters, "ActivityNode", UMLPackage.eINSTANCE.getActivityNode()) {

					@Override
					public void process(IStructuredSelection selection) {
						for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
							EObject elem = (EObject) iter.next();
							if (!redefinedNodeEditUtil.getVirtualList().contains(elem))
								redefinedNodeEditUtil.addElement(elem);
							propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this,
									UMLViewsRepository.CreateLinkAction.redefinedNode, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
						}
						redefinedNode.refresh();
					}
				};
				dialog.open();
			}

			public void handleEdit(ActivityNode element) {
				editRedefinedNode(element);
			}

			public void handleMove(ActivityNode element, int oldIndex, int newIndex) {
				moveRedefinedNode(element, oldIndex, newIndex);
			}

			public void handleRemove(ActivityNode element) {
				removeFromRedefinedNode(element);
			}

			public void navigateTo(ActivityNode element) {
			}
		});
		this.redefinedNode.setHelpText(propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.redefinedNode, UMLViewsRepository.FORM_KIND));
		this.redefinedNode.createControls(parent, widgetFactory);
		GridData redefinedNodeData = new GridData(GridData.FILL_HORIZONTAL);
		redefinedNodeData.horizontalSpan = 3;
		this.redefinedNode.setLayoutData(redefinedNodeData);
		this.redefinedNode.disableMove();
	}

	/**
	 * 
	 */
	protected void moveRedefinedNode(ActivityNode element, int oldIndex, int newIndex) {
	}

	/**
	 * 
	 */
	protected void removeFromRedefinedNode(ActivityNode element) {

		// Start of user code for the removeFromRedefinedNode() method body

		EObject editedElement = redefinedNodeEditUtil.foundCorrespondingEObject(element);
		redefinedNodeEditUtil.removeElement(element);
		redefinedNode.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.redefinedNode,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

		// End of user code
	}

	/**
	 * 
	 */
	protected void editRedefinedNode(ActivityNode element) {

		// Start of user code editRedefinedNode() method body

		EObject editedElement = redefinedNodeEditUtil.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element, resourceSet));
			if (propertiesEditionObject != null) {
				redefinedNodeEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
				redefinedNode.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.redefinedNode,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
			}
		}

		// End of user code
	}

	/**
	 * @param container
	 */
	protected void createHandlerTableComposition(FormToolkit widgetFactory, Composite parent) {
		this.handler = new ReferencesTable<ExceptionHandler>(UMLMessages.CreateLinkActionPropertiesEditionPart_HandlerLabel, new ReferencesTableListener<ExceptionHandler>() {

			public void handleAdd() {
				addToHandler();
			}

			public void handleEdit(ExceptionHandler element) {
				editHandler(element);
			}

			public void handleMove(ExceptionHandler element, int oldIndex, int newIndex) {
				moveHandler(element, oldIndex, newIndex);
			}

			public void handleRemove(ExceptionHandler element) {
				removeFromHandler(element);
			}

			public void navigateTo(ExceptionHandler element) {
			}
		});
		this.handler.setHelpText(propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.handler, UMLViewsRepository.FORM_KIND));
		this.handler.createControls(parent, widgetFactory);
		GridData handlerData = new GridData(GridData.FILL_HORIZONTAL);
		handlerData.horizontalSpan = 3;
		this.handler.setLayoutData(handlerData);
	}

	/**
	 * 
	 */
	protected void moveHandler(ExceptionHandler element, int oldIndex, int newIndex) {
		EObject editedElement = handlerEditUtil.foundCorrespondingEObject(element);
		handlerEditUtil.moveElement(element, oldIndex, newIndex);
		handler.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.handler,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));

	}

	/**
	 * 
	 */
	protected void addToHandler() {

		// Start of user code addToHandler() method body

		ExceptionHandler eObject = UMLFactory.eINSTANCE.createExceptionHandler();
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(eObject);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(eObject);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(propertiesEditionComponent, eObject, resourceSet));
			if (propertiesEditionObject != null) {
				handlerEditUtil.addElement(propertiesEditionObject);
				handler.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.handler,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, propertiesEditionObject));
			}
		}

		// End of user code
	}

	/**
	 * 
	 */
	protected void removeFromHandler(ExceptionHandler element) {

		// Start of user code for the removeFromHandler() method body

		EObject editedElement = handlerEditUtil.foundCorrespondingEObject(element);
		handlerEditUtil.removeElement(element);
		handler.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.handler,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

		// End of user code
	}

	/**
	 * 
	 */
	protected void editHandler(ExceptionHandler element) {

		// Start of user code editHandler() method body

		EObject editedElement = handlerEditUtil.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element, resourceSet));
			if (propertiesEditionObject != null) {
				handlerEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
				handler.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.handler,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
			}
		}

		// End of user code
	}

	/**
	 * @param container
	 */
	protected void createLocalPreconditionTableComposition(FormToolkit widgetFactory, Composite parent) {
		this.localPrecondition = new ReferencesTable<Constraint>(UMLMessages.CreateLinkActionPropertiesEditionPart_LocalPreconditionLabel, new ReferencesTableListener<Constraint>() {

			public void handleAdd() {
				addToLocalPrecondition();
			}

			public void handleEdit(Constraint element) {
				editLocalPrecondition(element);
			}

			public void handleMove(Constraint element, int oldIndex, int newIndex) {
				moveLocalPrecondition(element, oldIndex, newIndex);
			}

			public void handleRemove(Constraint element) {
				removeFromLocalPrecondition(element);
			}

			public void navigateTo(Constraint element) {
			}
		});
		this.localPrecondition.setHelpText(propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.localPrecondition, UMLViewsRepository.FORM_KIND));
		this.localPrecondition.createControls(parent, widgetFactory);
		GridData localPreconditionData = new GridData(GridData.FILL_HORIZONTAL);
		localPreconditionData.horizontalSpan = 3;
		this.localPrecondition.setLayoutData(localPreconditionData);
	}

	/**
	 * 
	 */
	protected void moveLocalPrecondition(Constraint element, int oldIndex, int newIndex) {
		EObject editedElement = localPreconditionEditUtil.foundCorrespondingEObject(element);
		localPreconditionEditUtil.moveElement(element, oldIndex, newIndex);
		localPrecondition.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.localPrecondition,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));

	}

	/**
	 * 
	 */
	protected void addToLocalPrecondition() {

		// Start of user code addToLocalPrecondition() method body

		Constraint eObject = UMLFactory.eINSTANCE.createConstraint();
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(eObject);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(eObject);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(propertiesEditionComponent, eObject, resourceSet));
			if (propertiesEditionObject != null) {
				localPreconditionEditUtil.addElement(propertiesEditionObject);
				localPrecondition.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.localPrecondition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, propertiesEditionObject));
			}
		}

		// End of user code
	}

	/**
	 * 
	 */
	protected void removeFromLocalPrecondition(Constraint element) {

		// Start of user code for the removeFromLocalPrecondition() method body

		EObject editedElement = localPreconditionEditUtil.foundCorrespondingEObject(element);
		localPreconditionEditUtil.removeElement(element);
		localPrecondition.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.localPrecondition,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

		// End of user code
	}

	/**
	 * 
	 */
	protected void editLocalPrecondition(Constraint element) {

		// Start of user code editLocalPrecondition() method body

		EObject editedElement = localPreconditionEditUtil.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element, resourceSet));
			if (propertiesEditionObject != null) {
				localPreconditionEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
				localPrecondition.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.localPrecondition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
			}
		}

		// End of user code
	}

	/**
	 * @param container
	 */
	protected void createLocalPostconditionTableComposition(FormToolkit widgetFactory, Composite parent) {
		this.localPostcondition = new ReferencesTable<Constraint>(UMLMessages.CreateLinkActionPropertiesEditionPart_LocalPostconditionLabel, new ReferencesTableListener<Constraint>() {

			public void handleAdd() {
				addToLocalPostcondition();
			}

			public void handleEdit(Constraint element) {
				editLocalPostcondition(element);
			}

			public void handleMove(Constraint element, int oldIndex, int newIndex) {
				moveLocalPostcondition(element, oldIndex, newIndex);
			}

			public void handleRemove(Constraint element) {
				removeFromLocalPostcondition(element);
			}

			public void navigateTo(Constraint element) {
			}
		});
		this.localPostcondition.setHelpText(propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.localPostcondition, UMLViewsRepository.FORM_KIND));
		this.localPostcondition.createControls(parent, widgetFactory);
		GridData localPostconditionData = new GridData(GridData.FILL_HORIZONTAL);
		localPostconditionData.horizontalSpan = 3;
		this.localPostcondition.setLayoutData(localPostconditionData);
	}

	/**
	 * 
	 */
	protected void moveLocalPostcondition(Constraint element, int oldIndex, int newIndex) {
		EObject editedElement = localPostconditionEditUtil.foundCorrespondingEObject(element);
		localPostconditionEditUtil.moveElement(element, oldIndex, newIndex);
		localPostcondition.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.localPostcondition,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));

	}

	/**
	 * 
	 */
	protected void addToLocalPostcondition() {

		// Start of user code addToLocalPostcondition() method body

		Constraint eObject = UMLFactory.eINSTANCE.createConstraint();
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(eObject);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(eObject);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(propertiesEditionComponent, eObject, resourceSet));
			if (propertiesEditionObject != null) {
				localPostconditionEditUtil.addElement(propertiesEditionObject);
				localPostcondition.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.localPostcondition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, propertiesEditionObject));
			}
		}

		// End of user code
	}

	/**
	 * 
	 */
	protected void removeFromLocalPostcondition(Constraint element) {

		// Start of user code for the removeFromLocalPostcondition() method body

		EObject editedElement = localPostconditionEditUtil.foundCorrespondingEObject(element);
		localPostconditionEditUtil.removeElement(element);
		localPostcondition.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.localPostcondition,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

		// End of user code
	}

	/**
	 * 
	 */
	protected void editLocalPostcondition(Constraint element) {

		// Start of user code editLocalPostcondition() method body

		EObject editedElement = localPostconditionEditUtil.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element, resourceSet));
			if (propertiesEditionObject != null) {
				localPostconditionEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
				localPostcondition.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.localPostcondition,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
			}
		}

		// End of user code
	}

	/**
	 * @param container
	 */
	protected void createEndDataTableComposition(FormToolkit widgetFactory, Composite parent) {
		this.endData = new ReferencesTable<LinkEndData>(UMLMessages.CreateLinkActionPropertiesEditionPart_EndDataLabel, new ReferencesTableListener<LinkEndData>() {

			public void handleAdd() {
				addToEndData();
			}

			public void handleEdit(LinkEndData element) {
				editEndData(element);
			}

			public void handleMove(LinkEndData element, int oldIndex, int newIndex) {
				moveEndData(element, oldIndex, newIndex);
			}

			public void handleRemove(LinkEndData element) {
				removeFromEndData(element);
			}

			public void navigateTo(LinkEndData element) {
			}
		});
		this.endData.setHelpText(propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.endData, UMLViewsRepository.FORM_KIND));
		this.endData.createControls(parent, widgetFactory);
		GridData endDataData = new GridData(GridData.FILL_HORIZONTAL);
		endDataData.horizontalSpan = 3;
		this.endData.setLayoutData(endDataData);
	}

	/**
	 * 
	 */
	protected void moveEndData(LinkEndData element, int oldIndex, int newIndex) {
		EObject editedElement = endDataEditUtil.foundCorrespondingEObject(element);
		endDataEditUtil.moveElement(element, oldIndex, newIndex);
		endData.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.endData,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));

	}

	/**
	 * 
	 */
	protected void addToEndData() {

		// Start of user code addToEndData() method body

		LinkEndData eObject = UMLFactory.eINSTANCE.createLinkEndData();
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(eObject);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(eObject);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(propertiesEditionComponent, eObject, resourceSet));
			if (propertiesEditionObject != null) {
				endDataEditUtil.addElement(propertiesEditionObject);
				endData.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.endData,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, propertiesEditionObject));
			}
		}

		// End of user code
	}

	/**
	 * 
	 */
	protected void removeFromEndData(LinkEndData element) {

		// Start of user code for the removeFromEndData() method body

		EObject editedElement = endDataEditUtil.foundCorrespondingEObject(element);
		endDataEditUtil.removeElement(element);
		endData.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.endData,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

		// End of user code
	}

	/**
	 * 
	 */
	protected void editEndData(LinkEndData element) {

		// Start of user code editEndData() method body

		EObject editedElement = endDataEditUtil.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element, resourceSet));
			if (propertiesEditionObject != null) {
				endDataEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
				endData.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.endData,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
			}
		}

		// End of user code
	}

	/**
	 * @param container
	 */
	protected void createInputValueTableComposition(FormToolkit widgetFactory, Composite parent) {
		this.inputValue = new ReferencesTable<InputPin>(UMLMessages.CreateLinkActionPropertiesEditionPart_InputValueLabel, new ReferencesTableListener<InputPin>() {

			public void handleAdd() {
				addToInputValue();
			}

			public void handleEdit(InputPin element) {
				editInputValue(element);
			}

			public void handleMove(InputPin element, int oldIndex, int newIndex) {
				moveInputValue(element, oldIndex, newIndex);
			}

			public void handleRemove(InputPin element) {
				removeFromInputValue(element);
			}

			public void navigateTo(InputPin element) {
			}
		});
		this.inputValue.setHelpText(propertiesEditionComponent.getHelpContent(UMLViewsRepository.CreateLinkAction.inputValue, UMLViewsRepository.FORM_KIND));
		this.inputValue.createControls(parent, widgetFactory);
		GridData inputValueData = new GridData(GridData.FILL_HORIZONTAL);
		inputValueData.horizontalSpan = 3;
		this.inputValue.setLayoutData(inputValueData);
	}

	/**
	 * 
	 */
	protected void moveInputValue(InputPin element, int oldIndex, int newIndex) {
		EObject editedElement = inputValueEditUtil.foundCorrespondingEObject(element);
		inputValueEditUtil.moveElement(element, oldIndex, newIndex);
		inputValue.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.inputValue,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, editedElement, newIndex));

	}

	/**
	 * 
	 */
	protected void addToInputValue() {

		// Start of user code addToInputValue() method body

		InputPin eObject = UMLFactory.eINSTANCE.createInputPin();
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(eObject);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(eObject);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(propertiesEditionComponent, eObject, resourceSet));
			if (propertiesEditionObject != null) {
				inputValueEditUtil.addElement(propertiesEditionObject);
				inputValue.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.inputValue,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, propertiesEditionObject));
			}
		}

		// End of user code
	}

	/**
	 * 
	 */
	protected void removeFromInputValue(InputPin element) {

		// Start of user code for the removeFromInputValue() method body

		EObject editedElement = inputValueEditUtil.foundCorrespondingEObject(element);
		inputValueEditUtil.removeElement(element);
		inputValue.refresh();
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.inputValue,
				PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, editedElement));

		// End of user code
	}

	/**
	 * 
	 */
	protected void editInputValue(InputPin element) {

		// Start of user code editInputValue() method body

		EObject editedElement = inputValueEditUtil.foundCorrespondingEObject(element);
		IPropertiesEditionPolicyProvider policyProvider = PropertiesEditionPolicyProviderService.getInstance().getProvider(element);
		IPropertiesEditionPolicy editionPolicy = policyProvider.getEditionPolicy(editedElement);
		if (editionPolicy != null) {
			EObject propertiesEditionObject = editionPolicy.getPropertiesEditionObject(new EObjectPropertiesEditionContext(null, element, resourceSet));
			if (propertiesEditionObject != null) {
				inputValueEditUtil.putElementToRefresh(editedElement, propertiesEditionObject);
				inputValue.refresh();
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(CreateLinkActionPropertiesEditionPartForm.this, UMLViewsRepository.CreateLinkAction.inputValue,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, editedElement, propertiesEditionObject));
			}
		}

		// End of user code
	}

	public void firePropertiesChanged(PropertiesEditionEvent event) {
		// Start of user code for tab synchronization

		// End of user code
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getOwnedCommentToAdd()
	 */
	public List getOwnedCommentToAdd() {
		return ownedCommentEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getOwnedCommentToRemove()
	 */
	public List getOwnedCommentToRemove() {
		return ownedCommentEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getOwnedCommentToEdit()
	 */
	public Map getOwnedCommentToEdit() {
		return ownedCommentEditUtil.getElementsToRefresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getOwnedCommentToMove()
	 */
	public List getOwnedCommentToMove() {
		return ownedCommentEditUtil.getElementsToMove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getOwnedCommentTable()
	 */
	public List getOwnedCommentTable() {
		return ownedCommentEditUtil.getVirtualList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initOwnedComment(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initOwnedComment(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			ownedCommentEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			ownedCommentEditUtil = new EMFListEditUtil(current, feature);
		this.ownedComment.setInput(ownedCommentEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#updateOwnedComment(EObject newValue)
	 */
	public void updateOwnedComment(EObject newValue) {
		if (ownedCommentEditUtil != null) {
			ownedCommentEditUtil.reinit(newValue);
			ownedComment.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getName()
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#setName(String newValue)
	 */
	public void setName(String newValue) {
		name.setText(newValue);
	}

	public void setMessageForName(String msg, int msgLevel) {
		messageManager.addMessage("Name_key", msg, null, msgLevel, name);
	}

	public void unsetMessageForName() {
		messageManager.removeMessage("Name_key", name);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getVisibility()
	 */
	public Enumerator getVisibility() {
		EEnumLiteral selection = (EEnumLiteral) ((StructuredSelection) visibility.getSelection()).getFirstElement();
		return selection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initVisibility(EEnum eenum, Enumerator current)
	 */
	public void initVisibility(EEnum eenum, Enumerator current) {
		visibility.setInput(eenum.getELiterals());
		visibility.setSelection(new StructuredSelection(current));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#setVisibility(Enumerator newValue)
	 */
	public void setVisibility(Enumerator newValue) {
		visibility.modelUpdating(new StructuredSelection(newValue));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getClientDependencyToAdd()
	 */
	public List getClientDependencyToAdd() {
		return clientDependencyEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getClientDependencyToRemove()
	 */
	public List getClientDependencyToRemove() {
		return clientDependencyEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initClientDependency(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initClientDependency(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			clientDependencyEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			clientDependencyEditUtil = new EMFListEditUtil(current, feature);
		this.clientDependency.setInput(clientDependencyEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#updateClientDependency(EObject newValue)
	 */
	public void updateClientDependency(EObject newValue) {
		if (clientDependencyEditUtil != null) {
			clientDependencyEditUtil.reinit(newValue);
			clientDependency.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getIsLeaf()
	 */
	public Boolean getIsLeaf() {
		return Boolean.valueOf(isLeaf.getSelection());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#setIsLeaf(Boolean newValue)
	 */
	public void setIsLeaf(Boolean newValue) {
		if (newValue != null) {
			isLeaf.setSelection(newValue.booleanValue());
		} else {
			isLeaf.setSelection(false);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getOutgoingToAdd()
	 */
	public List getOutgoingToAdd() {
		return outgoingEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getOutgoingToRemove()
	 */
	public List getOutgoingToRemove() {
		return outgoingEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initOutgoing(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initOutgoing(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			outgoingEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			outgoingEditUtil = new EMFListEditUtil(current, feature);
		this.outgoing.setInput(outgoingEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#updateOutgoing(EObject newValue)
	 */
	public void updateOutgoing(EObject newValue) {
		if (outgoingEditUtil != null) {
			outgoingEditUtil.reinit(newValue);
			outgoing.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getIncomingToAdd()
	 */
	public List getIncomingToAdd() {
		return incomingEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getIncomingToRemove()
	 */
	public List getIncomingToRemove() {
		return incomingEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initIncoming(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initIncoming(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			incomingEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			incomingEditUtil = new EMFListEditUtil(current, feature);
		this.incoming.setInput(incomingEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#updateIncoming(EObject newValue)
	 */
	public void updateIncoming(EObject newValue) {
		if (incomingEditUtil != null) {
			incomingEditUtil.reinit(newValue);
			incoming.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getInPartitionToAdd()
	 */
	public List getInPartitionToAdd() {
		return inPartitionEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getInPartitionToRemove()
	 */
	public List getInPartitionToRemove() {
		return inPartitionEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initInPartition(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initInPartition(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			inPartitionEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			inPartitionEditUtil = new EMFListEditUtil(current, feature);
		this.inPartition.setInput(inPartitionEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#updateInPartition(EObject newValue)
	 */
	public void updateInPartition(EObject newValue) {
		if (inPartitionEditUtil != null) {
			inPartitionEditUtil.reinit(newValue);
			inPartition.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getInInterruptibleRegionToAdd()
	 */
	public List getInInterruptibleRegionToAdd() {
		return inInterruptibleRegionEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getInInterruptibleRegionToRemove()
	 */
	public List getInInterruptibleRegionToRemove() {
		return inInterruptibleRegionEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initInInterruptibleRegion(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initInInterruptibleRegion(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			inInterruptibleRegionEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			inInterruptibleRegionEditUtil = new EMFListEditUtil(current, feature);
		this.inInterruptibleRegion.setInput(inInterruptibleRegionEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#updateInInterruptibleRegion(EObject newValue)
	 */
	public void updateInInterruptibleRegion(EObject newValue) {
		if (inInterruptibleRegionEditUtil != null) {
			inInterruptibleRegionEditUtil.reinit(newValue);
			inInterruptibleRegion.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getRedefinedNodeToAdd()
	 */
	public List getRedefinedNodeToAdd() {
		return redefinedNodeEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getRedefinedNodeToRemove()
	 */
	public List getRedefinedNodeToRemove() {
		return redefinedNodeEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initRedefinedNode(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initRedefinedNode(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			redefinedNodeEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			redefinedNodeEditUtil = new EMFListEditUtil(current, feature);
		this.redefinedNode.setInput(redefinedNodeEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#updateRedefinedNode(EObject newValue)
	 */
	public void updateRedefinedNode(EObject newValue) {
		if (redefinedNodeEditUtil != null) {
			redefinedNodeEditUtil.reinit(newValue);
			redefinedNode.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getHandlerToAdd()
	 */
	public List getHandlerToAdd() {
		return handlerEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getHandlerToRemove()
	 */
	public List getHandlerToRemove() {
		return handlerEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getHandlerToEdit()
	 */
	public Map getHandlerToEdit() {
		return handlerEditUtil.getElementsToRefresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getHandlerToMove()
	 */
	public List getHandlerToMove() {
		return handlerEditUtil.getElementsToMove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getHandlerTable()
	 */
	public List getHandlerTable() {
		return handlerEditUtil.getVirtualList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initHandler(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initHandler(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			handlerEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			handlerEditUtil = new EMFListEditUtil(current, feature);
		this.handler.setInput(handlerEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#updateHandler(EObject newValue)
	 */
	public void updateHandler(EObject newValue) {
		if (handlerEditUtil != null) {
			handlerEditUtil.reinit(newValue);
			handler.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getLocalPreconditionToAdd()
	 */
	public List getLocalPreconditionToAdd() {
		return localPreconditionEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getLocalPreconditionToRemove()
	 */
	public List getLocalPreconditionToRemove() {
		return localPreconditionEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getLocalPreconditionToEdit()
	 */
	public Map getLocalPreconditionToEdit() {
		return localPreconditionEditUtil.getElementsToRefresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getLocalPreconditionToMove()
	 */
	public List getLocalPreconditionToMove() {
		return localPreconditionEditUtil.getElementsToMove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getLocalPreconditionTable()
	 */
	public List getLocalPreconditionTable() {
		return localPreconditionEditUtil.getVirtualList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initLocalPrecondition(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initLocalPrecondition(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			localPreconditionEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			localPreconditionEditUtil = new EMFListEditUtil(current, feature);
		this.localPrecondition.setInput(localPreconditionEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#updateLocalPrecondition(EObject newValue)
	 */
	public void updateLocalPrecondition(EObject newValue) {
		if (localPreconditionEditUtil != null) {
			localPreconditionEditUtil.reinit(newValue);
			localPrecondition.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getLocalPostconditionToAdd()
	 */
	public List getLocalPostconditionToAdd() {
		return localPostconditionEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getLocalPostconditionToRemove()
	 */
	public List getLocalPostconditionToRemove() {
		return localPostconditionEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getLocalPostconditionToEdit()
	 */
	public Map getLocalPostconditionToEdit() {
		return localPostconditionEditUtil.getElementsToRefresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getLocalPostconditionToMove()
	 */
	public List getLocalPostconditionToMove() {
		return localPostconditionEditUtil.getElementsToMove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getLocalPostconditionTable()
	 */
	public List getLocalPostconditionTable() {
		return localPostconditionEditUtil.getVirtualList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initLocalPostcondition(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initLocalPostcondition(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			localPostconditionEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			localPostconditionEditUtil = new EMFListEditUtil(current, feature);
		this.localPostcondition.setInput(localPostconditionEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#updateLocalPostcondition(EObject newValue)
	 */
	public void updateLocalPostcondition(EObject newValue) {
		if (localPostconditionEditUtil != null) {
			localPostconditionEditUtil.reinit(newValue);
			localPostcondition.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getEndDataToAdd()
	 */
	public List getEndDataToAdd() {
		return endDataEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getEndDataToRemove()
	 */
	public List getEndDataToRemove() {
		return endDataEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getEndDataToEdit()
	 */
	public Map getEndDataToEdit() {
		return endDataEditUtil.getElementsToRefresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getEndDataToMove()
	 */
	public List getEndDataToMove() {
		return endDataEditUtil.getElementsToMove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getEndDataTable()
	 */
	public List getEndDataTable() {
		return endDataEditUtil.getVirtualList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initEndData(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initEndData(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			endDataEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			endDataEditUtil = new EMFListEditUtil(current, feature);
		this.endData.setInput(endDataEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#updateEndData(EObject newValue)
	 */
	public void updateEndData(EObject newValue) {
		if (endDataEditUtil != null) {
			endDataEditUtil.reinit(newValue);
			endData.refresh();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getInputValueToAdd()
	 */
	public List getInputValueToAdd() {
		return inputValueEditUtil.getElementsToAdd();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getInputValueToRemove()
	 */
	public List getInputValueToRemove() {
		return inputValueEditUtil.getElementsToRemove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getInputValueToEdit()
	 */
	public Map getInputValueToEdit() {
		return inputValueEditUtil.getElementsToRefresh();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getInputValueToMove()
	 */
	public List getInputValueToMove() {
		return inputValueEditUtil.getElementsToMove();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#getInputValueTable()
	 */
	public List getInputValueTable() {
		return inputValueEditUtil.getVirtualList();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#initInputValue(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initInputValue(EObject current, EReference containingFeature, EReference feature) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		if (containingFeature != null)
			inputValueEditUtil = new EMFListEditUtil(current, containingFeature, feature);
		else
			inputValueEditUtil = new EMFListEditUtil(current, feature);
		this.inputValue.setInput(inputValueEditUtil.getVirtualList());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.tabbedproperties.uml.parts.CreateLinkActionPropertiesEditionPart#updateInputValue(EObject newValue)
	 */
	public void updateInputValue(EObject newValue) {
		if (inputValueEditUtil != null) {
			inputValueEditUtil.reinit(newValue);
			inputValue.refresh();
		}
	}

	// Start of user code additional methods

	// End of user code
}
