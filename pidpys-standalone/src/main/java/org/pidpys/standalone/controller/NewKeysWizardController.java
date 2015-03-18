package org.pidpys.standalone.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import org.pidpys.model.NewKeysOptionsModel;
import org.pidpys.model.Password;
import org.pidpys.model.SignatureAlgorithm;
import org.pidpys.standalone.ui.javafx.wizard.WizardWindow;

import org.pidpys.standalone.view.NewKeysWizardView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NewKeysWizardController {

    private static final File USER_HOME = new File(System.getProperty("user.home"));
    private static final File APPLICATION_USER_HOME = new File(USER_HOME, ".pidpys");
    
    @Autowired
    NewKeysWizardView newKeysWizardView;

    public void createNewKeys(ActionEvent event) {
        NewKeysOptionsModel model = new NewKeysOptionsModel();
        newKeysWizardView.newWizard(model);
        newKeysWizardView.showFlowSelectionDialog(model);
        newKeysWizardView.show(model);
    }

    public void createStandartKey(NewKeysOptionsModel model) {
        model.setAlgorithm(SignatureAlgorithm.RSA);
        model.setKeyLength(2048);
        model.setStore(new File(APPLICATION_USER_HOME, "key-" + System.currentTimeMillis() + ".pem"));
        newKeysWizardView.showNewKeysPasswordDialog(model);
    }

    public boolean checkPassword(NewKeysOptionsModel model, Password password, Password confirmation) {
        boolean result = false;
        if (password.isEmpty() && confirmation.isEmpty()) {
            newKeysWizardView.showMessageThatPasswordIsBlank(model);
        } else if (!password.equals(confirmation)) {
            newKeysWizardView.showMessageThatPasswordDoesNotMatchConfirmation(model);
        } else {
            newKeysWizardView.showConfirmatinDialog(model);
            result = true;
        }
        return result;
    }
    
}
