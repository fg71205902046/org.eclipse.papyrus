package org.eclipse.papyrus.compare.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.diff.metamodel.ComparisonSnapshot;
import org.eclipse.emf.compare.ui.internal.wizard.SaveDeltaWizard;
import org.eclipse.papyrus.compare.report.ui.common.GenerateAll;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;


public class ExportAsHTMLWizard extends SaveDeltaWizard {

	private ComparisonSnapshot myInputSnapshot;

	private WizardNewFileCreationPage myNewReportFileCreationPage;

	public ExportAsHTMLWizard() {
		super(null);
	}


	public void init(IWorkbench workbench, ComparisonSnapshot inputSnapshot) {
		super.init(workbench, inputSnapshot);
		myInputSnapshot = inputSnapshot;
	}

	public void addPages() {
		super.addPages();

		myNewReportFileCreationPage = new WizardNewFileCreationPage("newFilePage1", getSelection());//$NON-NLS-1$
		myNewReportFileCreationPage.setFileName("result"); //$NON-NLS-1$
		myNewReportFileCreationPage.setAllowExistingResources(true);
		myNewReportFileCreationPage.setTitle("New HTML File");
		myNewReportFileCreationPage.setDescription("Create a new HTML file");
		myNewReportFileCreationPage.setFileExtension("html");
		addPage(myNewReportFileCreationPage);
	}

	@Override
	public boolean performFinish() {
		boolean result = super.performFinish();
		if (!result) {
			return false;
		}
		
		URI uri = getSavedDiffModelURI();
		
		GenerateAll generator = new GenerateAll(uri, myNewReportFileCreationPage.getContainerFullPath().toFile(), getArguments());
		try {
			generator.doGenerate(new NullProgressMonitor());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	protected List<? extends Object> getArguments() {
		return new ArrayList<String>();
	}
	
	private WizardNewFileCreationPage getSaveDiffPage() {
		final String page = "newFilePage1"; //$NON-NLS-1$
		return (WizardNewFileCreationPage)getPage(page);
	}
	
	private URI getSavedDiffModelURI() {
		WizardNewFileCreationPage saveDiffPage = getSaveDiffPage();
		IPath filePath = saveDiffPage.getContainerFullPath().append(saveDiffPage.getFileName());
		IFile fileHandle = IDEWorkbenchPlugin.getPluginWorkspace().getRoot().getFile(filePath);
		return URI.createFileURI(fileHandle.getLocation().toOSString());
	}

}
