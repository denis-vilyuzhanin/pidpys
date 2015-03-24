package org.pidpys.standalone.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.pidpys.model.NewKeysOptionsModel;
import org.pidpys.model.Password;
import org.pidpys.model.SignatureAlgorithmDescription;
import org.pidpys.standalone.controller.NewKeysWizardController;
import org.pidpys.standalone.ui.javafx.wizard.WizardWindow;
import org.pidpys.standalone.ui.javafx.wizard.newkeys.NewKeysParametersDialog;
import org.pidpys.standalone.ui.javafx.wizard.newkeys.NewKeysConfirmationDialog;
import org.pidpys.standalone.ui.javafx.wizard.newkeys.NewKeysPasswordDialog;
import org.pidpys.standalone.ui.javafx.wizard.newkeys.NewKeysStoreFileDialog;
import org.pidpys.standalone.ui.javafx.wizard.newkeys.SelectNewKeysGeneratingFlowDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewKeysWizardView extends FxView {

    @Autowired
    ResourceBundle localization;
    
    @Autowired
    NewKeysWizardController newKeysWizardController;
    
    private Map<NewKeysOptionsModel, WizardWindow> modelToWizard = new HashMap<>();

    public void newWizard(NewKeysOptionsModel model) {
        modelToWizard.put(model, new WizardWindow(localization));
    }
    
    public void show(NewKeysOptionsModel model) {
        modelToWizard.get(model).show();
    }


    public void showFlowSelectionDialog(NewKeysOptionsModel model) {
        WizardWindow wizardWindow = modelToWizard.get(model);
        SelectNewKeysGeneratingFlowDialog dialog = new SelectNewKeysGeneratingFlowDialog(localization);
        wizardWindow.showDialog(dialog);
        wizardWindow.onNextAction((w) -> {
            switch(dialog.getSelectedOption()) {
                case STANDART:
                    newKeysWizardController.createStandartKey(model);    
                    break;
                case CUSTOM:
                    newKeysWizardController.specifyCustomKeyParamters(model);
                    break;
                default:
                    return false;
            }
            return true;
        });
    }
    
    public void showCustomKeyParametersDialog(
        NewKeysOptionsModel model, List<SignatureAlgorithmDescription> algorithmDescriptions) {
        NewKeysParametersDialog dialog = new NewKeysParametersDialog(localization);
        algorithmDescriptions.forEach((algorithm) -> {
            dialog.addAlgorithm(algorithm.getId(), algorithm.getName(), algorithm.getAvailableLength());
        });
        dialog.setSelected(0);
        WizardWindow wizardWindow = modelToWizard.get(model);
        wizardWindow.showDialog(dialog);
        wizardWindow.onNextAction((w) -> {
            return newKeysWizardController.createCustomKey(model);
        });
    }

    
    public void showNewKeysStoreFileDialog(NewKeysOptionsModel model) {
        WizardWindow wizardWindow = modelToWizard.get(model);
        NewKeysStoreFileDialog dialog = new NewKeysStoreFileDialog(localization);
        dialog.setSelectedFile(model.getStore());
        wizardWindow.showDialog(dialog);
        wizardWindow.onNextAction((w) -> {
            model.setStore(dialog.getSelectedFile());
            return newKeysWizardController.checkSelectedFileStore(model);
        });
    }

    public void showNewKeysPasswordDialog(NewKeysOptionsModel model) {
        WizardWindow wizardWindow = modelToWizard.get(model);
        NewKeysPasswordDialog dialog = new NewKeysPasswordDialog(localization);
        wizardWindow.showDialog(dialog);
        wizardWindow.onNextAction((w) -> {
            model.setPassword(new Password(dialog.fetchPassword()));
            model.setPasswordConfirmation(new Password((dialog.fetchConfirmationPassword())));
            return newKeysWizardController.checkPassword(model);
        });
    }
    
    public void showMessageThatPasswordIsBlank(NewKeysOptionsModel model) {
        WizardWindow wizardWindow = modelToWizard.get(model);
        NewKeysPasswordDialog dialog = (NewKeysPasswordDialog) wizardWindow.getCurrentDialog();
        dialog.showMessageThatPasswordIsBlank();
    }

    public void showMessageThatPasswordDoesNotMatchConfirmation(NewKeysOptionsModel model) {
        WizardWindow wizardWindow = modelToWizard.get(model);
        NewKeysPasswordDialog dialog = (NewKeysPasswordDialog) wizardWindow.getCurrentDialog();
        dialog.showMessageThatPasswordDoesNotMatchConfirmation();
    }
    
    public void showConfirmatinDialog(NewKeysOptionsModel model) {
        WizardWindow wizardWindow = modelToWizard.get(model);
        NewKeysConfirmationDialog dialog = new NewKeysConfirmationDialog(localization);
        dialog.showAlgorithmName(model.getAlgorithm().toString());
        dialog.showKeyLength(model.getKeyLength());
        dialog.showStore(model.getStore().getAbsolutePath());
        wizardWindow.showDialog(dialog);
        wizardWindow.onConfirmAction((w) -> {
            System.out.println("Confirmed");
        });
    }


    
   
}
