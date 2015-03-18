/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx.wizard.newkeys;

import java.util.ResourceBundle;
import org.pidpys.standalone.ui.javafx.JavaFXComponent;

/**
 *
 * @author dvily_000
 */
public class NewKeysPasswordDialog extends JavaFXComponent {

    public NewKeysPasswordDialog() {
    }

    public NewKeysPasswordDialog(ResourceBundle localization) {
        super(localization);
    }
    
    public char[] fetchPassword() {
        return new char[0];
    }

    public char[] fetchConfirmationPassword() {
        return new char[0];
    }
}
