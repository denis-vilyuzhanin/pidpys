package org.pidpys.standalone.controller;

import java.awt.event.ActionEvent;
import org.pidpys.standalone.ui.javafx.wizard.WizardWindow;

import org.pidpys.standalone.view.NewKeysWizardView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NewKeysWizardController {

    @Autowired
    NewKeysWizardView newKeysWizardView;

    public void createNewKeys(ActionEvent event) {
        WizardWindow wizard = newKeysWizardView.showWizard();
        newKeysWizardView.showFlowSelectionDialog(wizard);
    }

    public void createStandartKey(WizardWindow wizard) {
        newKeysWizardView.showConfirmatinDialog(wizard);
    }
}
