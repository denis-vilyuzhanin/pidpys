package org.pidpys.standalone.view;

import java.util.Arrays;

import javax.swing.JOptionPane;

import org.pidpys.standalone.controller.NewKeysWizardController;
import org.pidpys.standalone.ui.wizard.AbstractWizardWindow;
import org.pidpys.standalone.ui.wizard.newkeys.KeyInfoStepPanel;
import org.pidpys.standalone.ui.wizard.newkeys.KeyPasswordStepPanel;
import org.pidpys.standalone.ui.wizard.newkeys.KeyStorageStepPanel;
import org.pidpys.standalone.ui.wizard.newkeys.SelectAlgorithmStepPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewKeysWizardView {

	@Autowired
	NewKeysWizardController newKeysWizardController;

	public void show() {
		AbstractWizardWindow wizardWindow = new AbstractWizardWindow();
		wizardWindow.beginStep(new KeyInfoStepPanel(), this::keyInfoStepRouter);
		wizardWindow.setVisible(true);
	}

	private boolean keyInfoStepRouter(AbstractWizardWindow wizard,
			KeyInfoStepPanel step) {
		wizard.gotoNextStep(new SelectAlgorithmStepPanel(),
				this::selectAlgorithmStepRouter);
		return true;
	}

	private boolean selectAlgorithmStepRouter(AbstractWizardWindow wizard,
			SelectAlgorithmStepPanel step) {
		wizard.gotoNextStep(new KeyPasswordStepPanel(),
				this::keysPasswordStepRouter);
		return true;
	}

	private boolean keysPasswordStepRouter(AbstractWizardWindow wizard,
			KeyPasswordStepPanel step) {
		char[] value = step.getPassword();
		boolean result = true;
		try {
			if (value.length == 0) {
				int reply = JOptionPane
						.showConfirmDialog(
								wizard,
								"This is unsecure. Are you sure that you want to continue?",
								"You don't specify password.",
								JOptionPane.YES_NO_OPTION);
				result = reply == JOptionPane.YES_OPTION;
			}
			if (result) {
				wizard.gotoNextStep(new KeyStorageStepPanel(), this::keyStorageStepRouter);
			}
			return result;
		} finally {
			Arrays.fill(value, (char) 0);
		}
	}
	
	private boolean keyStorageStepRouter(AbstractWizardWindow wizard, KeyStorageStepPanel step) {
		return true;
	}
}
