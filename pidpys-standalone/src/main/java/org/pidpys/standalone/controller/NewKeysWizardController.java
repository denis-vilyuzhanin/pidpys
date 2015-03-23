package org.pidpys.standalone.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pidpys.model.NewKeysOptionsModel;
import org.pidpys.model.Password;
import org.pidpys.model.SignatureAlgorithm;
import org.pidpys.model.SignatureAlgorithmDescription;
import org.pidpys.standalone.ui.javafx.wizard.WizardWindow;

import org.pidpys.standalone.view.NewKeysWizardView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NewKeysWizardController {

    private static final File USER_HOME = new File(System.getProperty("user.home"));
    private static final File APPLICATION_USER_HOME = new File(USER_HOME, ".pidpys");
    
    private List<SignatureAlgorithmDescription> algorithm = Arrays.asList(
            new SignatureAlgorithmDescription("RSA", "RSA (PKCS #1: RSA Cryptography Standard)", 2048, 1024, 512, 256),
            new SignatureAlgorithmDescription("DSA", "DSA (FIPS PUB 186-4: Digital Signature Standard (DSS), July 2013)", 2048, 1024, 512, 256)
    );
    
    @Autowired
    NewKeysWizardView newKeysWizardView;

    public void createNewKeys(ActionEvent event) {
        NewKeysOptionsModel model = new NewKeysOptionsModel();
        newKeysWizardView.newWizard(model);
        newKeysWizardView.showFlowSelectionDialog(model);
        newKeysWizardView.show(model);
    }

    public boolean createStandartKey(NewKeysOptionsModel model) {
        fillStandartKeyParameters(model);
        newKeysWizardView.showNewKeysStoreFileDialog(model);
        return true;
    }
    
    public void specifyCustomKeyParamters(NewKeysOptionsModel model) {
        fillStandartKeyParameters(model);
        newKeysWizardView.showCustomKeyParametersDialog(model, algorithm);
    }
    
    public boolean createCustomKey(NewKeysOptionsModel model) {
        newKeysWizardView.showNewKeysStoreFileDialog(model);
        return true;
    }

    public boolean checkSelectedFileStore(NewKeysOptionsModel model) {
        newKeysWizardView.showNewKeysPasswordDialog(model);
        return true;
    }
    
    public boolean checkPassword(NewKeysOptionsModel model) {
        boolean result = false;
        if (model.getPassword().isEmpty()) {
            newKeysWizardView.showMessageThatPasswordIsBlank(model);
        } else if (!model.getPassword().equals(model.getPasswordConfirmation())) {
            newKeysWizardView.showMessageThatPasswordDoesNotMatchConfirmation(model);
        } else {
            newKeysWizardView.showConfirmatinDialog(model);
            result = true;
        }
        return result;
    }

   

    private void fillStandartKeyParameters(NewKeysOptionsModel model) {
        model.setAlgorithm(SignatureAlgorithm.RSA);
        model.setKeyLength(2048);
        model.setStore(new File(APPLICATION_USER_HOME, "key-" + System.currentTimeMillis() + ".pem"));
    }

    
    
}
