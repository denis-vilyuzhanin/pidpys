/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx.wizard.newkeys;

import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import org.pidpys.standalone.ui.javafx.JavaFXComponent;

/**
 *
 * @author dvily_000
 */
public class NewKeysPasswordDialog extends JavaFXComponent {

    @FXML
    PasswordField passwordField;
    
    @FXML
    PasswordField passwordConfirmationField;
    
    public NewKeysPasswordDialog() {
    }

    public NewKeysPasswordDialog(ResourceBundle localization) {
        super(localization);
    }
    
    public char[] fetchPassword() {
        char[] value = passwordField.getText().toCharArray();
        passwordField.clear();
        return value;
    }

    public char[] fetchConfirmationPassword() {
        char[] value = passwordConfirmationField.getText().toCharArray();
        passwordConfirmationField.clear();
        return value;
    }
    
    public void showMessageThatPasswordIsBlank() {
        
    }

    public void showMessageThatPasswordDoesNotMatchConfirmation() {
        
    }
}
