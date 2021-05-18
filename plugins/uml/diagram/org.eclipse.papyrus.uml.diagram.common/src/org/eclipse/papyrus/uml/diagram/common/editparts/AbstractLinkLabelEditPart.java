/*****************************************************************************
* Copyright (c) 2021 CEA LIST, ARTAL
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse public final License 2.0
* which accompanies this distribution, and is available at
* https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*   Etienne ALLOGO (ARTAL) - Initial API and implementation
*   Etienne ALLOGO (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : generate less dead or duplicate code
*****************************************************************************/

package org.eclipse.papyrus.uml.diagram.common.editparts;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.infra.gmfdiag.common.editpart.IControlParserForDirectEdit;
import org.eclipse.papyrus.infra.gmfdiag.common.editpart.PapyrusLabelEditPart;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.IMaskManagedLabelEditPolicy;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.IndirectMaskLabelEditPolicy;
import org.eclipse.papyrus.infra.gmfdiag.common.parsers.ParserUtil;
import org.eclipse.papyrus.infra.gmfdiag.common.utils.DiagramEditPartsUtil;
import org.eclipse.papyrus.infra.gmfdiag.extensionpoints.editors.Activator;
import org.eclipse.papyrus.infra.gmfdiag.extensionpoints.editors.configuration.IAdvancedEditorConfiguration;
import org.eclipse.papyrus.infra.gmfdiag.extensionpoints.editors.configuration.ICustomDirectEditorConfiguration;
import org.eclipse.papyrus.infra.gmfdiag.extensionpoints.editors.configuration.IDirectEditorConfiguration;
import org.eclipse.papyrus.infra.gmfdiag.extensionpoints.editors.configuration.IPopupEditorConfiguration;
import org.eclipse.papyrus.infra.gmfdiag.extensionpoints.editors.ui.ExtendedDirectEditionDialog;
import org.eclipse.papyrus.infra.gmfdiag.extensionpoints.editors.ui.ILabelEditorDialog;
import org.eclipse.papyrus.infra.gmfdiag.extensionpoints.editors.ui.IPopupEditorHelper;
import org.eclipse.papyrus.infra.gmfdiag.extensionpoints.editors.utils.DirectEditorsUtil;
import org.eclipse.papyrus.infra.gmfdiag.extensionpoints.editors.utils.IDirectEditorsIds;
import org.eclipse.papyrus.uml.diagram.common.directedit.MultilineLabelDirectEditManager;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.IDirectEdition;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.UMLTextSelectionEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.figure.node.ILabelFigure;
import org.eclipse.papyrus.uml.diagram.common.locator.MultilineCellEditorLocator;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Feature;

/**
 * An intermediate class with the common methods used by the link labels (inherit from PapyrusLabelEditPart).
 *
 * @author allogo
 */
public abstract class AbstractLinkLabelEditPart extends PapyrusLabelEditPart implements ITextAwareEditPart, IControlParserForDirectEdit {

	/** The manager. */
	protected DirectEditManager manager;

	/** The parser. */
	protected IParser parser;

	/** The parser elements. */
	private List<?> parserElements;

	/** The default text. */
	private String defaultText;

	/**
	 * direct edition mode (default, undefined, registered editor, etc.)
	 **/
	protected int directEditionMode = IDirectEdition.UNDEFINED_DIRECT_EDITOR;

	/** configuration from a registered edit dialog. */
	protected IDirectEditorConfiguration configuration;

	/**
	 * Constructor.
	 *
	 * @param view
	 *            the view
	 */
	public AbstractLinkLabelEditPart(View view) {
		super(view);
	}

	/**
	 * Gets the label text helper.
	 *
	 * @param figure
	 *            the figure
	 * @return the label text helper
	 */
	protected final String getLabelTextHelper(IFigure figure) {
		if (figure instanceof WrappingLabel) {
			return ((WrappingLabel) figure).getText();
		} else if (figure instanceof ILabelFigure) {
			return ((ILabelFigure) figure).getText();
		} else {
			return ((Label) figure).getText();
		}
	}

	/**
	 * Sets the label text helper.
	 *
	 * @param figure
	 *            the figure
	 * @param text
	 *            the text
	 */
	protected final void setLabelTextHelper(IFigure figure, String text) {
		if (figure instanceof WrappingLabel) {
			((WrappingLabel) figure).setText(text);
		} else if (figure instanceof ILabelFigure) {
			((ILabelFigure) figure).setText(text);
		} else {
			((Label) figure).setText(text);
		}
	}

	/**
	 * Gets the label icon helper.
	 *
	 * @param figure
	 *            the figure
	 * @return the label icon helper
	 */
	protected final Image getLabelIconHelper(IFigure figure) {
		if (figure instanceof WrappingLabel) {
			return ((WrappingLabel) figure).getIcon();
		} else if (figure instanceof ILabelFigure) {
			return ((ILabelFigure) figure).getIcon();
		} else {
			return ((Label) figure).getIcon();
		}
	}

	/**
	 * Sets the label icon helper.
	 *
	 * @param figure
	 *            the figure
	 * @param icon
	 *            the icon
	 */
	protected final void setLabelIconHelper(IFigure figure, Image icon) {
		if (figure instanceof WrappingLabel) {
			((WrappingLabel) figure).setIcon(icon);
		} else if (figure instanceof ILabelFigure) {
			((ILabelFigure) figure).setIcon(icon);
		} else {
			((Label) figure).setIcon(icon);
		}
	}

	/**
	 * Sets the label.
	 *
	 * @param figure
	 *            the new label
	 */
	public final void setLabel(IFigure figure) {
		unregisterVisuals();
		setFigure(figure);
		defaultText = getLabelTextHelper(figure);
		registerVisuals();
		refreshVisuals();
	}

	@Override
	protected final List<?> getModelChildren() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public final IGraphicalEditPart getChildBySemanticHint(String semanticHint) {
		return null;
	}

	@Override
	public final void setParser(IParser parser) {
		this.parser = parser;
	}

	/**
	 * Gets the label text.
	 *
	 * @return the label text
	 */
	protected final String getLabelText() {
		String text = null;
		EObject parserElement = getParserElement();
		if (parserElement != null && getParser() != null) {
			text = getParser().getPrintString(
					ParserUtil.getParserAdapter(getParserElement(), this),
					getParserOptions().intValue());
		}
		if (text == null || text.length() == 0) {
			text = defaultText;
		}
		return text;
	}

	@Override
	public final String getEditText() {
		if (getParserElement() == null || getParser() == null) {
			return ""; //$NON-NLS-1$
		}
		return getParser().getEditString(
				ParserUtil.getParserAdapter(getParserElement(), this),
				getParserOptions().intValue());
	}

	@Override
	public final IContentAssistProcessor getCompletionProcessor() {
		if (getParserElement() == null || getParser() == null) {
			return null;
		}
		return getParser().getCompletionProcessor(ParserUtil.getParserAdapter(getParserElement(), this));
	}

	@Override
	public final ParserOptions getParserOptions() {
		return ParserOptions.NONE;
	}

	/**
	 * Sets the manager.
	 *
	 * @param manager
	 *            the new manager
	 */
	protected final void setManager(DirectEditManager manager) {
		this.manager = manager;
	}

	/**
	 * Perform direct edit.
	 */
	protected final void performDirectEdit() {
		BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

			@Override
			public final void run() {
				getManager().show();
			}
		});
	}

	/**
	 * Perform direct edit.
	 *
	 * @param eventLocation
	 *            the event location
	 */
	protected final void performDirectEdit(Point eventLocation) {
		if (getManager() instanceof TextDirectEditManager) {
			((TextDirectEditManager) getManager()).show(eventLocation.getSWTPoint());
		}
	}

	/**
	 * Perform direct edit.
	 *
	 * @param initialCharacter
	 *            the initial character
	 */
	protected final void performDirectEdit(char initialCharacter) {
		if (getManager() instanceof TextDirectEditManager) {
			((TextDirectEditManager) getManager()).show(initialCharacter);
		} else {
			performDirectEdit();
		}
	}

	/**
	 * Initialize direct edit manager.
	 *
	 * @param request
	 *            the request
	 */
	protected final void initializeDirectEditManager(final Request request) {
		// initialize the direct edit manager
		try {
			getEditingDomain().runExclusive(new Runnable() {
				@Override
				public final void run() {
					if (isActive() && isEditable()) {
						if (request.getExtendedData().get(
								RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR) instanceof Character) {
							Character initialChar = (Character) request.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR);
							performDirectEdit(initialChar.charValue());
						} else {
							performDirectEdit();
						}
					}
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshLabel();
		refreshFont();
		refreshFontColor();
		refreshUnderline();
		refreshStrikeThrough();
	}

	/**
	 * Refresh underline.
	 */
	protected final void refreshUnderline() {
		FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(
				NotationPackage.eINSTANCE.getFontStyle());
		if (style != null && getFigure() instanceof WrappingLabel) {
			((WrappingLabel) getFigure()).setTextUnderline(style.isUnderline());
		}
		if (resolveSemanticElement() instanceof Feature) {
			if (((Feature) resolveSemanticElement()).isStatic()) {
				((WrappingLabel) getFigure()).setTextUnderline(true);
			} else {
				((WrappingLabel) getFigure()).setTextUnderline(false);
			}
		}
	}

	/**
	 * Refresh strike through.
	 */
	protected final void refreshStrikeThrough() {
		FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if (style != null && getFigure() instanceof WrappingLabel) {
			((WrappingLabel) getFigure()).setTextStrikeThrough(style.isStrikeThrough());
		}
	}

	@Override
	protected final void refreshFont() {
		FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(
				NotationPackage.eINSTANCE.getFontStyle());
		if (style != null) {
			FontData fontData = new FontData(style.getFontName(), style.getFontHeight(),
					(style.isBold() ? org.eclipse.swt.SWT.BOLD : org.eclipse.swt.SWT.NORMAL) |
							(style.isItalic() ? org.eclipse.swt.SWT.ITALIC : org.eclipse.swt.SWT.NORMAL));
			setFont(fontData);
		}
	}

	@Override
	protected final void setFontColor(Color color) {
		getFigure().setForegroundColor(color);
	}

	@Override
	protected final void addSemanticListeners() {
		if (getParser() instanceof ISemanticParser) {
			EObject element = resolveSemanticElement();
			parserElements = ((ISemanticParser) getParser()).getSemanticElementsBeingParsed(element);
			for (int i = 0; i < parserElements.size(); i++) {
				addListenerFilter("SemanticModel" + i, this, (EObject) parserElements.get(i)); //$NON-NLS-1$
			}
		} else {
			super.addSemanticListeners();
		}
	}

	@Override
	protected final void removeSemanticListeners() {
		if (parserElements != null) {
			for (int i = 0; i < parserElements.size(); i++) {
				removeListenerFilter("SemanticModel" + i); //$NON-NLS-1$
			}
		} else {
			super.removeSemanticListeners();
		}
	}

	@Override
	protected final AccessibleEditPart getAccessibleEditPart() {
		if (accessibleEP == null) {
			accessibleEP = new AccessibleGraphicalEditPart() {

				@Override
				public final void getName(AccessibleEvent e) {
					e.result = getLabelTextHelper(getFigure());
				}
			};
		}
		return accessibleEP;
	}

	/**
	 * Checks if an extended editor is present.
	 *
	 * @return <code>true</code> if an extended editor is present.
	 */
	protected final boolean checkExtendedEditor() {
		if (resolveSemanticElement() != null) {
			return DirectEditorsUtil.hasSpecificEditorConfiguration(resolveSemanticElement(), this);
		}
		return false;
	}

	/**
	 * Checks if a default direct edition is available.
	 *
	 * @return <code>true</code> if a default direct edition is available
	 */
	protected final boolean checkDefaultEdition() {
		return (getParser() != null);
	}

	/**
	 * Initializes the extended editor configuration.
	 */
	protected final void initExtendedEditorConfiguration() {
		if (configuration == null) {
			final String languagePreferred = Activator.getDefault().getPreferenceStore()
					.getString(IDirectEditorsIds.EDITOR_FOR_ELEMENT + resolveSemanticElement().eClass().getInstanceClassName());
			if (languagePreferred != null && !languagePreferred.equals("")) {
				configuration = DirectEditorsUtil.findEditorConfiguration(languagePreferred, resolveSemanticElement(), this);
			} else {
				configuration = DirectEditorsUtil.findEditorConfiguration(IDirectEditorsIds.UML_LANGUAGE, resolveSemanticElement(),
						this);
			}
		}
	}

	/**
	 * Updates the preference configuration.
	 */
	protected final void updateExtendedEditorConfiguration() {
		String languagePreferred = Activator.getDefault().getPreferenceStore().getString(
				IDirectEditorsIds.EDITOR_FOR_ELEMENT + resolveSemanticElement().eClass().getInstanceClassName());
		if (languagePreferred != null && !languagePreferred.equals("") && !languagePreferred.equals(configuration.getLanguage())) {
			configuration = DirectEditorsUtil.findEditorConfiguration(languagePreferred, resolveSemanticElement(), this);
		} else if (IDirectEditorsIds.SIMPLE_DIRECT_EDITOR.equals(languagePreferred)) {
			configuration = null;
		}
	}

	/**
	 * Performs the direct edit usually used by GMF editors.
	 *
	 * @param theRequest
	 *            the direct edit request that starts the direct edit system
	 */
	protected final void performDefaultDirectEditorEdit(final Request theRequest) {
		// initialize the direct edit manager
		try {
			getEditingDomain().runExclusive(new Runnable() {

				@Override
				public final void run() {
					if (isActive() && isEditable()) {
						if (theRequest.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR) instanceof Character) {
							Character initialChar = (Character) theRequest.getExtendedData().get(
									RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR);
							performDirectEdit(initialChar.charValue());
						} else if ((theRequest instanceof DirectEditRequest) && (getEditText().equals(getLabelText()))) {
							DirectEditRequest editRequest = (DirectEditRequest) theRequest;
							performDirectEdit(editRequest.getLocation());
						} else {
							performDirectEdit();
						}
					}
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the manager.
	 *
	 * @return the manager
	 */
	protected final DirectEditManager getManager() {
		if (manager == null) {
			setManager(new MultilineLabelDirectEditManager(this,
					MultilineLabelDirectEditManager.getTextCellEditorClass(this),
					MultilineCellEditorLocator.getTextCellEditorLocator(this)));
		}
		return manager;
	}

	/**
	 * Checks if is editable.
	 *
	 * @return true, if is editable
	 */
	protected boolean isEditable() {
		return getParser() != null;
	}

	/**
	 * Returns the kind of associated editor for direct edition.
	 *
	 * @return an <code>int</code> corresponding to the kind of direct editor, @see IDirectEdition
	 */
	public int getDirectEditionType() {
		if (checkExtendedEditor()) {
			initExtendedEditorConfiguration();
			return IDirectEdition.EXTENDED_DIRECT_EDITOR;
		}

		if (checkDefaultEdition()) {
			return IDirectEdition.DEFAULT_DIRECT_EDITOR;
		}
		// not a named element. no specific editor => do nothing
		return IDirectEdition.NO_DIRECT_EDITION;
	}

	/**
	 * Gets the label icon.
	 *
	 * @return the label icon
	 */
	protected Image getLabelIcon() {
		return DiagramEditPartsUtil.getIcon(getParserElement(), getViewer());
	}

	/**
	 * Gets the font style owner view.
	 *
	 * @return the font style owner view
	 */
	protected final View getFontStyleOwnerView() {
		return getPrimaryView();
	}

	/**
	 * Refresh label.
	 */
	protected final void refreshLabel() {
		EditPolicy maskLabelPolicy = getEditPolicy(IMaskManagedLabelEditPolicy.MASK_MANAGED_LABEL_EDIT_POLICY);
		if (maskLabelPolicy == null) {
			maskLabelPolicy = getEditPolicy(IndirectMaskLabelEditPolicy.INDRIRECT_MASK_MANAGED_LABEL);
		}
		if (maskLabelPolicy == null) {
			View view = (View) getModel();
			if (view.isVisible()) {
				setLabelTextHelper(getFigure(), getLabelText());
				setLabelIconHelper(getFigure(), getLabelIcon());
			} else {
				setLabelTextHelper(getFigure(), ""); //$NON-NLS-1$
				setLabelIconHelper(getFigure(), null);
			}
		}
		Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		if (pdEditPolicy instanceof UMLTextSelectionEditPolicy) {
			((UMLTextSelectionEditPolicy) pdEditPolicy).refreshFeedback();
		}
		Object sfEditPolicy = getEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
		if (sfEditPolicy instanceof UMLTextSelectionEditPolicy) {
			((UMLTextSelectionEditPolicy) sfEditPolicy).refreshFeedback();
		}
	}

	@Override
	public final void setLabelText(String text) {
		setLabelTextHelper(getFigure(), text);
		Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		if (pdEditPolicy instanceof UMLTextSelectionEditPolicy) {
			((UMLTextSelectionEditPolicy) pdEditPolicy).refreshFeedback();
		}
		Object sfEditPolicy = getEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
		if (sfEditPolicy instanceof UMLTextSelectionEditPolicy) {
			((UMLTextSelectionEditPolicy) sfEditPolicy).refreshFeedback();
		}
	}

	@Override
	public int getKeyPoint() {
		return ConnectionLocator.MIDDLE;
	}

	@Override
	public final ICellEditorValidator getEditTextValidator() {
		return new ICellEditorValidator() {

			@Override
			public String isValid(final Object value) {
				if (value instanceof String) {
					final IParser parser = getParser();
					try {
						IParserEditStatus valid = (IParserEditStatus) getEditingDomain()
								.runExclusive(new RunnableWithResult.Impl<>() {

									@Override
									public void run() {
										setResult(
												parser.isValidEditString(
														ParserUtil.getParserAdapter(getParserElement(),
																AbstractLinkLabelEditPart.this),
														(String) value));
									}
								});
						return valid.getCode() == IParserEditStatus.EDITABLE ? null : valid.getMessage();
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
				}

				// shouldn't get here
				return null;
			}
		};
	}

	@Override
	protected final void performDirectEditRequest(Request request) {

		final Request theRequest = request;

		if (IDirectEdition.UNDEFINED_DIRECT_EDITOR == directEditionMode) {
			directEditionMode = getDirectEditionType();
		}
		switch (directEditionMode) {
		case IDirectEdition.NO_DIRECT_EDITION:
			// no direct edition mode => does nothing
			return;
		case IDirectEdition.EXTENDED_DIRECT_EDITOR:
			updateExtendedEditorConfiguration();
			if (configuration == null || configuration.getLanguage() == null) {
				// Create default edit manager
				setManager(new MultilineLabelDirectEditManager(this,
						MultilineLabelDirectEditManager.getTextCellEditorClass(this),
						MultilineCellEditorLocator.getTextCellEditorLocator(this)));
				performDefaultDirectEditorEdit(theRequest);
			} else {
				configuration.preEditAction(resolveSemanticElement());
				Dialog dialog = null;
				if (configuration instanceof ICustomDirectEditorConfiguration) {
					setManager(((ICustomDirectEditorConfiguration) configuration).createDirectEditManager(this));
					initializeDirectEditManager(theRequest);
					return;
				} else if (configuration instanceof IPopupEditorConfiguration) {
					IPopupEditorHelper helper = ((IPopupEditorConfiguration) configuration)
							.createPopupEditorHelper(this);
					helper.showEditor();
					return;
				} else if (configuration instanceof IAdvancedEditorConfiguration) {
					dialog = ((IAdvancedEditorConfiguration) configuration).createDialog(
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), resolveSemanticElement(),
							configuration.getTextToEdit(resolveSemanticElement()));
				} else if (configuration instanceof IDirectEditorConfiguration) {
					dialog = new ExtendedDirectEditionDialog(
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), resolveSemanticElement(),
							configuration.getTextToEdit(resolveSemanticElement()), configuration);
				} else {
					return;
				}
				final Dialog finalDialog = dialog;

				if (Window.OK == dialog.open()) {
					TransactionalEditingDomain domain = getEditingDomain();
					RecordingCommand command = new RecordingCommand(domain, "Edit Label") {

						@Override
						protected void doExecute() {
							configuration.postEditAction(resolveSemanticElement(),
									((ILabelEditorDialog) finalDialog).getValue());

						}
					};
					domain.getCommandStack().execute(command);
				}
			}
			break;
		case IDirectEdition.DEFAULT_DIRECT_EDITOR:
			initializeDirectEditManager(theRequest);
			break;
		default:
			break;
		}
	}

	/**
	 * Gets the parser element.
	 *
	 * @return the parser element
	 */
	protected EObject getParserElement() {
		return resolveSemanticElement();
	}

	@Override
	protected final IFigure createFigure() {
		// Parent should assign one using setLabel() method
		return null;
	}

	@Override
	protected void handleNotificationEvent(Notification event) {
		Object feature = event.getFeature();
		if (NotationPackage.eINSTANCE.getFontStyle_FontColor().equals(feature)) {
			Integer c = (Integer) event.getNewValue();
			setFontColor(DiagramColorRegistry.getInstance().getColor(c));
		} else if (NotationPackage.eINSTANCE.getFontStyle_Underline().equals(feature)) {
			refreshUnderline();
		} else if (NotationPackage.eINSTANCE.getFontStyle_StrikeThrough().equals(feature)) {
			refreshStrikeThrough();
		} else if (NotationPackage.eINSTANCE.getFontStyle_FontHeight().equals(feature) ||
				NotationPackage.eINSTANCE.getFontStyle_FontName().equals(feature) ||
				NotationPackage.eINSTANCE.getFontStyle_Bold().equals(feature) ||
				NotationPackage.eINSTANCE.getFontStyle_Italic().equals(feature)) {
			refreshFont();
		} else {
			if (getParser() != null && getParser().isAffectingEvent(event, getParserOptions().intValue())) {
				refreshLabel();
			}
			if (getParser() instanceof ISemanticParser) {
				ISemanticParser modelParser = (ISemanticParser) getParser();
				if (modelParser.areSemanticElementsAffected(null, event)) {
					removeSemanticListeners();
					if (resolveSemanticElement() != null) {
						addSemanticListeners();
					}
					refreshLabel();
				}
			}
		}
		super.handleNotificationEvent(event);
	}
}
