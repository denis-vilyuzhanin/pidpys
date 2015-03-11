/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx.wizard;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Function;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.pidpys.standalone.ui.javafx.JavaFXComponent;
import org.pidpys.standalone.ui.javafx.JavaFXWindow;

/**
 *
 * @author dvily_000
 */
public class WizardWindow extends JavaFXWindow {

    @FXML
    BorderPane windowPane;

    @FXML 
    StackPane body;
    
    @FXML 
    Button nextButton;
    
    @FXML 
    Button previousButton;
    
    private Step currentStep;
    private Step nextStep = new Step();
    private Deque<Step> passedSteps = new LinkedList<>();
    
    public void showDialog(JavaFXComponent dialogComponent) {
        nextStep.dialog = dialogComponent;
    }
    
    public void onNextAction(Function<WizardWindow, Boolean> nextAction) {
        nextStep.nextAction = nextAction;
    }
    
    @Override
    public void show() {
        showNextStep();
        super.show();
    }

    @FXML
    void handleNextButtonClicked() {
        if (currentStep.nextAction.apply(this)) {
            passedSteps.push(currentStep);
            showNextStep();
            previousButton.setDisable(false);
        }
    }
    
    @FXML
    void handlePreviousButtonClicked() {
        if (!passedSteps.isEmpty()) {
            currentStep = passedSteps.pop();
            showCurrentStep();
        }
        previousButton.setDisable(passedSteps.isEmpty());
    }
    
    private void showNextStep() {
        currentStep = nextStep;
        nextStep = new Step();
        showCurrentStep();
    }
    
    private void showCurrentStep() {
        showInCenter(currentStep.dialog.getContainer());
    }
    
    private void showInCenter(Parent panel) {
        execute(() -> {
            windowPane.setCenter(panel);
        });
    }
    
    
    private class Step {
        private Function<WizardWindow, Boolean> nextAction = (w) -> {return false;};
        private JavaFXComponent dialog;
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
