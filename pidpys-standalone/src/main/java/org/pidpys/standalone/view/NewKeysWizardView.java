package org.pidpys.standalone.view;

import org.pidpys.standalone.ui.wizard.newkeys.NewKeysWizardWindow;
import org.springframework.stereotype.Component;

@Component
public class NewKeysWizardView {

	public void show() {
		NewKeysWizardWindow wizardWindow = new NewKeysWizardWindow();
		wizardWindow.setVisible(true);
	}

}
