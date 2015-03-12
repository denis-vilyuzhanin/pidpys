package org.pidpys.standalone.view;

import java.util.HashMap;
import java.util.Map;
import org.pidpys.model.NewKeysOptionsModel;
import org.pidpys.standalone.controller.NewKeysWizardController;
import org.pidpys.standalone.ui.javafx.wizard.WizardWindow;
import org.pidpys.standalone.ui.javafx.wizard.newkeys.NewKeysConfirmationDialog;
import org.pidpys.standalone.ui.javafx.wizard.newkeys.SelectNewKeysGeneratingFlowDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewKeysWizardView extends FxView {

    @Autowired
    NewKeysWizardController newKeysWizardController;
    
    private Map<NewKeysOptionsModel, WizardWindow> modelToWizard = new HashMap<>();

    public void newWizard(NewKeysOptionsModel model) {
        WizardWindow wizardWindow = new WizardWindow();
        modelToWizard.putIfAbsent(model, new WizardWindow());
    }
    
    public void show(NewKeysOptionsModel model) {
        modelToWizard.get(model).show();
    }


    public void showFlowSelectionDialog(NewKeysOptionsModel model) {
        WizardWindow wizardWindow = modelToWizard.get(model);
        SelectNewKeysGeneratingFlowDialog dialog = new SelectNewKeysGeneratingFlowDialog();
        wizardWindow.showDialog(dialog);
        wizardWindow.onNextAction((w) -> {
            newKeysWizardController.createStandartKey(model);
            return true;
        });
    }

    public void showConfirmatinDialog(NewKeysOptionsModel model) {
        WizardWindow wizardWindow = modelToWizard.get(model);
        NewKeysConfirmationDialog dialog = new NewKeysConfirmationDialog();
        dialog.showAlgorithmName(model.getAlgorithm().toString());
        dialog.showKeyLength(model.getKeyLength());
        dialog.showStore(model.getStore().getAbsolutePath());
        wizardWindow.showDialog(dialog);
        wizardWindow.onConfirmAction((w) -> {
            System.out.println("Confirmed");
        });
    }

   
}
