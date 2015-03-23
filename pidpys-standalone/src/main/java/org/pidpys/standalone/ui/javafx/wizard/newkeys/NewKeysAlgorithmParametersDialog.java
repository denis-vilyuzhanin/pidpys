/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx.wizard.newkeys;

import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import org.pidpys.standalone.ui.javafx.JavaFXComponent;

/**
 *
 * @author dvily_000
 */
public class NewKeysAlgorithmParametersDialog extends JavaFXComponent {


    @FXML
    ChoiceBox<Item> algorithmChoiceBox;
    
    @FXML
    ChoiceBox<Integer> keyLengthChoiceBox;

    public NewKeysAlgorithmParametersDialog() {
    }

    public NewKeysAlgorithmParametersDialog(ResourceBundle localization) {
        super(localization);
    }

    @Override
    protected void init() {
        algorithmChoiceBox.getSelectionModel().selectedItemProperty().addListener((item) -> {
            keyLengthChoiceBox.getItems().clear();
            //keyLengthChoiceBox.getItems().addAll(item.keyLength);
        });
    }
       
    
    public int getSelectedIndex() {
        return algorithmChoiceBox.getSelectionModel().getSelectedIndex();
    }
    
    public void setSelected(int index) {
        algorithmChoiceBox.getSelectionModel().select(index);
    }
    
    public void addAlgorithm(String id, String name, List<Integer> keyLength) {
        algorithmChoiceBox.getItems().add(new Item(id, localize(name), keyLength));
    }
    
    

    private class Item {
        String id;
        String displayText;
        List<Integer> keyLength;

        public Item(String id, String displayText, List<Integer> keyLength) {
            this.id = id;
            this.displayText = displayText;
            this.keyLength = keyLength;
        }
        
        @Override
        public String toString() {
            return displayText;
        }
        
        
    }
}
