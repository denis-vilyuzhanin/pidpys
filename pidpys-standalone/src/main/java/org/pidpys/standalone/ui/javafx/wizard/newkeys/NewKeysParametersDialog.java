/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx.wizard.newkeys;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import org.pidpys.standalone.ui.javafx.JavaFXComponent;

/**
 *
 * @author dvily_000
 */
public class NewKeysParametersDialog extends JavaFXComponent {


    @FXML
    ChoiceBox<Item> algorithmChoiceBox;
    
    @FXML
    ChoiceBox<Integer> keyLengthChoiceBox;

    private List<String> algorithmIds = new ArrayList<>();
    
    public NewKeysParametersDialog() {
    }

    public NewKeysParametersDialog(ResourceBundle localization) {
        super(localization);
    }

    @Override
    protected void init() {
        algorithmChoiceBox.getSelectionModel().selectedItemProperty().addListener((item) -> {
            Integer selectedLength = keyLengthChoiceBox.getSelectionModel().getSelectedItem();
            keyLengthChoiceBox.getItems().clear();
            Item selected = algorithmChoiceBox.getSelectionModel().getSelectedItem();
            keyLengthChoiceBox.getItems().addAll(selected.keyLength);
            
            int selectedIndex = selected.keyLength.indexOf(selectedLength);
            if (selectedIndex < 0) {
                selectedIndex = 0;
            }
            keyLengthChoiceBox.getSelectionModel().select(selectedIndex);
        });
        
    }
       
    
    public int getSelectedIndex() {
        return algorithmChoiceBox.getSelectionModel().getSelectedIndex();
    }
    
    public void setSelected(int index) {
        algorithmChoiceBox.getSelectionModel().select(index);
    }
    
    public void addAlgorithm(String id, String name, List<Integer> keyLength) {
        algorithmIds.add(id);
        algorithmChoiceBox.getItems().add(new Item(id, localize(name), keyLength));
    }

    public void setSelectedKeyLength(int keyLength) {
        keyLengthChoiceBox.getSelectionModel().select(keyLength);
    }

    public void setSelectedAlgorithm(String algorithm) {
        int index = algorithmIds.indexOf(algorithm);
        if (index < 0) {
            index = 0;
        }
        algorithmChoiceBox.getSelectionModel().select(index);
    }
    
    public String getSelectedAlgorithmId() {
        return algorithmChoiceBox.getSelectionModel().getSelectedItem().id;
    }
    
    public Integer getSelectedKeyLength() {
        return keyLengthChoiceBox.getSelectionModel().getSelectedItem();
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
