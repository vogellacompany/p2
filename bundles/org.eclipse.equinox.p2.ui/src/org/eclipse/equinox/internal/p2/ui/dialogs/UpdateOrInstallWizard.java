/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.internal.p2.ui.dialogs;

import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.ui.LicenseManager;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

/**
 * @since 3.4
 */
public abstract class UpdateOrInstallWizard extends Wizard {

	UpdateOrInstallWizardPage mainPage;
	AcceptLicensesWizardPage licensePage;
	String profileId;
	IInstallableUnit[] ius;
	LicenseManager licenseManager;

	public UpdateOrInstallWizard(String profileId, IInstallableUnit[] ius, LicenseManager licenseManager) {
		super();
		setForcePreviousAndNextButtons(true);
		setNeedsProgressMonitor(true);
		this.profileId = profileId;
		this.ius = ius;
		this.licenseManager = licenseManager;
	}

	public void addPages() {
		mainPage = createMainPage(profileId, ius);
		addPage(mainPage);
		addPage(licensePage = new AcceptLicensesWizardPage(ius, licenseManager));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == mainPage && licensePage.hasLicensesToAccept())
			return licensePage;
		return null;
	}

	public boolean performFinish() {
		return mainPage.performFinish();
	}

	public void iusChanged(IInstallableUnit[] theIUs) {
		this.ius = theIUs;
		licensePage.update(ius);
	}

	protected abstract UpdateOrInstallWizardPage createMainPage(String theProfileId, IInstallableUnit[] theIUs);

}
