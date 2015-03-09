package org.pidpys.standalone.ui.javafx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SampleWindow {

    public static void main(String[] args) {
        
        Platform.runLater(() -> {
            createStage(1);
        });
        Platform.runLater(() -> {
            createStage(2);
        });

    }

    private static void createStage(int i) {
        Stage stage = new Stage();
        Scene scene = new Scene(new Group(new Text(25, 25, "Stage: " + i)));
        
        stage.setTitle("Welcome to JavaFX!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
