package org.pidpys.standalone.controller;

import java.awt.event.ActionEvent;
import org.pidpys.model.NewKeysOptionsModel;
import org.pidpys.standalone.ui.javafx.wizard.WizardWindow;

import org.pidpys.standalone.view.NewKeysWizardView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NewKeysWizardController {

    @Autowired
    NewKeysWizardView newKeysWizardView;

    public void createNewKeys(ActionEvent event) {
        NewKeysOptionsModel model = new NewKeysOptionsModel();
        newKeysWizardView.newWizard(model);
        newKeysWizardView.showFlowSelectionDialog(model);
        newKeysWizardView.show(model);
    }

    public void createStandartKey(NewKeysOptionsModel model) {
        newKeysWizardView.showConfirmatinDialog(model);
    }
}
