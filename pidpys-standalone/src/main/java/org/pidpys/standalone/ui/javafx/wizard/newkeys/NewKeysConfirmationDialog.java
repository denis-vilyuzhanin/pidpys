/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx.wizard.newkeys;

import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import org.pidpys.model.NewKeysOptionsModel;
import org.pidpys.standalone.ui.javafx.JavaFXComponent;

/**
 *
 * @author dvily_000
 */
public class NewKeysConfirmationDialog extends JavaFXComponent {

    @FXML
    TextField algorithmField;
    
    @FXML
    TextField keyLengthField;
    
    @FXML
    TextField filePathField;

    public NewKeysConfirmationDialog() {
    }
    
    public NewKeysConfirmationDialog(ResourceBundle localization) {
        super(localization);
    }
    
    
    public void showAlgorithmName(String algorithm) {
        algorithmField.setText(algorithm);
    }

    public void showKeyLength(int keyLength) {
        keyLengthField.setText(Integer.toString(keyLength));
    }

    public void showStore(String filePath) {
        filePathField.setText(filePath);
        //filePathField.setPrefColumnCount(filePath.length() / 2 + 5);
    }

    

    
}
