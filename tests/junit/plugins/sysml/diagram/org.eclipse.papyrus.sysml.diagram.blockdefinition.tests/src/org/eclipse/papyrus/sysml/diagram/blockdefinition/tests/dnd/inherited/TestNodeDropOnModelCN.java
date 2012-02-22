package org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.dnd.inherited;

import static org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.utils.EditorUtils.getDiagramView;
import static org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.utils.TestPrepareUtils.createElement;
import static org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.utils.TestUtils.dropFromModelExplorer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.provider.ElementTypes;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.AbstractTest;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.utils.TestPrepareUtils;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit tests for element Drop test (via palette tools) on Model CN.
 */
public class TestNodeDropOnModelCN extends AbstractTest {

	public static View containerView;

	@BeforeClass
	public static void prepareContainerForTest() throws Exception {
		try {
			containerView = TestPrepareUtils.createGraphicalNode(UMLElementTypes.MODEL, ElementTypes.MODEL_CN.getSemanticHint(), getDiagramView());
			if(containerView == null) {
				throw new Exception("Unable to prepare container for test.");
			}
		} catch (Exception e) {
			throw new Exception("Unable to prepare container for test.", e);
		}
	}

	@Test
	public void testDropActor() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.ACTOR, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropActorContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.ACTOR, containerView, false);
	}

	@Test
	public void testDropActorPart() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, getDiagramView());
		EObject droppedElement = createElement(SysMLElementTypes.ACTOR_PART_PROPERTY, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropBlock() throws Exception {
		EObject droppedElement = createElement(SysMLElementTypes.BLOCK, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropBlockContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.BLOCK, containerView, false);
	}

	@Test
	public void testDropComment() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.COMMENT, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropCommentContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.COMMENT, containerView, false);
	}

	@Test
	public void testDropConstraintBlock() throws Exception {
		EObject droppedElement = createElement(SysMLElementTypes.CONSTRAINT_BLOCK, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropConstraintBlockContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.CONSTRAINT_BLOCK, containerView, false);
	}

	@Test
	public void testDropConstraint() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.CONSTRAINT, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropConstraintContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.CONSTRAINT, containerView, false);
	}

	@Test
	public void testDropConstraintProperty() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.CONSTRAINT_BLOCK, getDiagramView());
		EObject droppedElement = createElement(SysMLElementTypes.CONSTRAINT_PROPERTY, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropDataType() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.DATA_TYPE, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropDataTypeContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.DATA_TYPE, containerView, false);
	}

	@Test
	public void testDropDimension() throws Exception {
		EObject droppedElement = createElement(SysMLElementTypes.DIMENSION, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropDimensionContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.DIMENSION, containerView, false);
	}

	@Test
	public void testDropEnumeration() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.ENUMERATION, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropEnumerationContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.ENUMERATION, containerView, false);
	}

	@Test
	public void testDropEnumerationLiteral() throws Exception {
		EObject intermediateContainer = createElement(UMLElementTypes.ENUMERATION, getDiagramView());
		EObject droppedElement = createElement(UMLElementTypes.ENUMERATION_LITERAL, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropFlowPort() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, getDiagramView());
		EObject droppedElement = createElement(SysMLElementTypes.FLOW_PORT, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropFlowProperty() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.FLOW_SPECIFICATION, getDiagramView());
		EObject droppedElement = createElement(SysMLElementTypes.FLOW_PROPERTY, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropFlowSpecification() throws Exception {
		EObject droppedElement = createElement(SysMLElementTypes.FLOW_SPECIFICATION, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropFlowSpecificationContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.FLOW_SPECIFICATION, containerView, false);
	}

	@Test
	public void testDropInstanceSpecification() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.INSTANCE_SPECIFICATION, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropInstanceSpecificationContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.INSTANCE_SPECIFICATION, containerView, false);
	}

	@Test
	public void testDropInterface() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.INTERFACE, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropInterfaceContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.INTERFACE, containerView, false);
	}

	@Test
	public void testDropModel() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.MODEL, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropModelContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.MODEL, containerView, false);
	}

	@Test
	public void testDropOperation() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, getDiagramView());
		EObject droppedElement = createElement(UMLElementTypes.OPERATION, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropPackage() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.PACKAGE, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropPackageContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.PACKAGE, containerView, false);
	}

	@Test
	public void testDropPart() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, getDiagramView());
		EObject droppedElement = createElement(SysMLElementTypes.PART_PROPERTY, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropPort() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, getDiagramView());
		EObject droppedElement = createElement(UMLElementTypes.PORT, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropPrimitiveType() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.PRIMITIVE_TYPE, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropPrimitiveTypeContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.PRIMITIVE_TYPE, containerView, false);
	}

	@Test
	public void testDropProperty() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, getDiagramView());
		EObject droppedElement = createElement(UMLElementTypes.PROPERTY, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropReception() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, getDiagramView());
		EObject droppedElement = createElement(UMLElementTypes.RECEPTION, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropReference() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, getDiagramView());
		EObject droppedElement = createElement(SysMLElementTypes.REFERENCE_PROPERTY, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropSignal() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.SIGNAL, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropSignalContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.SIGNAL, containerView, false);
	}

	@Test
	public void testDropSlot() throws Exception {
		EObject intermediateContainer = createElement(UMLElementTypes.INSTANCE_SPECIFICATION, getDiagramView());
		EObject droppedElement = createElement(UMLElementTypes.SLOT, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropUnit() throws Exception {
		EObject droppedElement = createElement(SysMLElementTypes.UNIT, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropUnitContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.UNIT, containerView, false);
	}

	@Test
	public void testDropValue() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, getDiagramView());
		EObject droppedElement = createElement(SysMLElementTypes.VALUE_PROPERTY, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropValueType() throws Exception {
		EObject droppedElement = createElement(SysMLElementTypes.VALUE_TYPE, getDiagramView());
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropValueTypeContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.VALUE_TYPE, containerView, false);
	}
}
