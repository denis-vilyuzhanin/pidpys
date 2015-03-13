/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx.wizard.newkeys;

import java.util.ResourceBundle;
import javafx.scene.Parent;
import org.pidpys.standalone.ui.javafx.JavaFXComponent;

/**
 *
 * @author dvily_000
 */
public class SelectNewKeysGeneratingFlowDialog extends JavaFXComponent {

    public SelectNewKeysGeneratingFlowDialog() {
        
    }
    
    public SelectNewKeysGeneratingFlowDialog(ResourceBundle localization) {
        super(localization);
    }
    
    public enum Option {
        STANDART,
        CUSTOM
    }
    
    private Option selectedOption = Option.STANDART;
    
    public void handleStandartKeySelected() {
        selectedOption = Option.STANDART;
    }
    
    public void handleCustomKeySelected() {
        selectedOption = Option.CUSTOM;
    }
    
    public Option getSelectedOption() {
        return selectedOption;
    }
}
