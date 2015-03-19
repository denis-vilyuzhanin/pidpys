/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx.wizard.newkeys;

import java.util.ResourceBundle;
import javafx.fxml.FXML;

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
    
    public NewKeysPasswordDialog() {
    }

    public NewKeysPasswordDialog(ResourceBundle localization) {
        super(localization);
    }
    
    public char[] fetchPassword() {
        return passwordField.fetchPassword();
    }

    public char[] fetchConfirmationPassword() {
        return passwordConfirmationField.fetchPassword();
    }
    
    public void showMessageThatPasswordIsBlank() {
        
    }

    public void showMessageThatPasswordDoesNotMatchConfirmation() {
        
    }
}
