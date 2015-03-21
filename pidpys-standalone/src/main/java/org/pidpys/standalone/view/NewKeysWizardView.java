package org.pidpys.standalone.view;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.pidpys.model.NewKeysOptionsModel;
import org.pidpys.model.Password;
import org.pidpys.standalone.controller.NewKeysWizardController;
import org.pidpys.standalone.ui.javafx.wizard.WizardWindow;
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
            newKeysWizardController.createStandartKey(model);
            return true;
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
            Password password = new Password(dialog.fetchPassword());
            Password confirmationPassword = new Password((dialog.fetchConfirmationPassword()));
            return newKeysWizardController.checkPassword(model, password, confirmationPassword);
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
