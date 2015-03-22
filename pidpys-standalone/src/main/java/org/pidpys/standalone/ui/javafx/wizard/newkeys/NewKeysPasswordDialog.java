/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx.wizard.newkeys;

import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import org.pidpys.standalone.ui.javafx.JavaFXComponent;
import org.pidpys.standalone.ui.javafx.widgets.SecurePasswordField;

/**
 *
 * @author dvily_000
 */
public class NewKeysPasswordDialog extends JavaFXComponent {

    @FXML
    SecurePasswordField passwordField;
    
    @FXML
    SecurePasswordField passwordConfirmationField;
    
    @FXML
    Label emptyPasswordMessageLable;
    
    @FXML
    Label passwordDoesNotMatch;
    
    public NewKeysPasswordDialog() {
        hideMessages();
    }

    public NewKeysPasswordDialog(ResourceBundle localization) {
        super(localization);
        hideMessages();
    }

    @Override
    protected void init() {
        passwordField.focusedProperty().addListener(this::hideMessagesWhenUserBeginTyping);
        passwordConfirmationField.focusedProperty().addListener(this::hideMessagesWhenUserBeginTyping);
    }
    
    public char[] fetchPassword() {
        return passwordField.fetchPassword();
    }

    public char[] fetchConfirmationPassword() {
        return passwordConfirmationField.fetchPassword();
    }
    
    public void showMessageThatPasswordIsBlank() {
        hideMessages();
        emptyPasswordMessageLable.setVisible(true);
    }

    public void showMessageThatPasswordDoesNotMatchConfirmation() {
        hideMessages();
        passwordDoesNotMatch.setVisible(true);
    }
    
    private void hideMessagesWhenUserBeginTyping(
            ObservableValue<? extends Boolean> valie, Boolean oldValue, Boolean newValue) {
        hideMessages();
    }
    
    private void hideMessages() {
        emptyPasswordMessageLable.setVisible(false);
        passwordDoesNotMatch.setVisible(false);
    }
}
