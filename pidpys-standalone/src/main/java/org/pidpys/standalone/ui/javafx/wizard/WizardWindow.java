/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx.wizard;

import java.io.IOException;
import java.net.URL;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import org.pidpys.standalone.ui.javafx.JavaFXComponent;
import org.pidpys.standalone.ui.javafx.JavaFXWindow;

/**
 *
 * @author dvily_000
 */
public class WizardWindow extends JavaFXWindow {

    private Step currentStep = new Step();
    
    @FXML
    private BorderPane windowPane;
    
    @FXML 
    private StackPane body;
    
    @FXML 
    private Button nextButton;
    
    public void showDialog(JavaFXComponent<Parent> dialogComponent) {
        currentStep.dialog = dialogComponent;
        execute(() -> {
            windowPane.setCenter(dialogComponent.getContainer());
        });
    }
    
    public void doNextAction() {
        if (currentStep.nextAction.apply(this)) {
            System.out.println("Go to next step: " + nextButton);
        }
    }
    
    public void onNextAction(Function<WizardWindow, Boolean> nextAction) {
        currentStep.nextAction = nextAction;
    }
    
    private class Step {
        private Function<WizardWindow, Boolean> nextAction = (w) -> {return false;};
        private JavaFXComponent<Parent> dialog;
    }

    public StackPane getBody() {
        return body;
    }

    public void setBody(StackPane body) {
        this.body = body;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public void setNextButton(Button nextButton) {
        this.nextButton = nextButton;
    }
    
    
}
