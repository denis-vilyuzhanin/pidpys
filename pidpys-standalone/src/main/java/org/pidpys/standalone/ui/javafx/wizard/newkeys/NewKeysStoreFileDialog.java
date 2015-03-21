/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx.wizard.newkeys;

import java.io.File;
import java.util.ResourceBundle;
import org.pidpys.standalone.ui.javafx.JavaFXComponent;

/**
 *
 * @author dvily_000
 */
public class NewKeysStoreFileDialog extends JavaFXComponent {

    private File selectedFile;
    
    public NewKeysStoreFileDialog() {
    }

    public NewKeysStoreFileDialog(ResourceBundle localization) {
        super(localization);
    }

    public File getSelectedFile() {
        return this.selectedFile;
    }
 
    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }
    
}
