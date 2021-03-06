/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dvily_000
 */
public class JavaFXWindow extends JavaFXComponent {
    
    private Stage stage;
    
    public JavaFXWindow() {
        showStage();
    }
    
    public JavaFXWindow(ResourceBundle localization) {
        super(localization);
        showStage();
    }
    
    private void showStage() {
        execute(()->{
            stage = new Stage();
            stage.setScene(new Scene(container));
        });
    }
    
    public void show() {
        execute(() -> {
            stage.show();
        });
    }
    
}
