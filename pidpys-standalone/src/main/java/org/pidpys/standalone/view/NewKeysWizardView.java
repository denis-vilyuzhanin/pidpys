package org.pidpys.standalone.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;

import javax.swing.JOptionPane;
import org.pidpys.model.NewKeysOptionsModel;

import org.pidpys.standalone.controller.NewKeysWizardController;
import org.pidpys.standalone.ui.javafx.wizard.WizardWindow;
import org.pidpys.standalone.ui.javafx.wizard.newkeys.NewKeysConfirmationDialog;
import org.pidpys.standalone.ui.javafx.wizard.newkeys.SelectNewKeysGeneratingFlowDialog;
import org.pidpys.standalone.ui.wizard.AbstractWizardWindow;
import org.pidpys.standalone.ui.wizard.newkeys.KeyInfoStepPanel;
import org.pidpys.standalone.ui.wizard.newkeys.KeyPasswordStepPanel;
import org.pidpys.standalone.ui.wizard.newkeys.KeyStorageStepPanel;
import org.pidpys.standalone.ui.wizard.newkeys.SelectAlgorithmStepPanel;
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

    public void show1() {
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
