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
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author dvily_000
 */
public class JavaFXComponent {
    private static final ResourceBundle EMPTY_LOCALIZATION = new EmptyResourceBundle();
    
    protected final Parent container;
    
    public JavaFXComponent() {
        this(EMPTY_LOCALIZATION);
    }
    
    public JavaFXComponent(ResourceBundle localization) {
        try {
            new JFXPanel();
            URL fxml = createFXMLUrl();
            FXMLLoader loader = new FXMLLoader(fxml);
            loader.setResources(localization);
            loader.setController(this);
            container = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Parent getContainer() {
        return container;
    }
    
    protected URL createFXMLUrl() throws MalformedURLException {
        Class<?> thisClass = this.getClass();
        StringBuilder pathBuiler = new StringBuilder();
        pathBuiler.append(thisClass.getPackage().getName().replaceAll("[.]", "/"));
        pathBuiler.append("/");
        pathBuiler.append(thisClass.getSimpleName());
        pathBuiler.append(".fxml");
        return thisClass.getClassLoader().getResource(pathBuiler.toString());
    }
        
    protected void execute(Runnable code) {
        if (Platform.isFxApplicationThread()) {
            code.run();
        } else {
            Platform.runLater(code);
        }
    }
}
