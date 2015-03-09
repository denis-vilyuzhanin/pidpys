/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author dvily_000
 */
public class JavaFXComponent<T> {
    
    protected final T container;
    
    public JavaFXComponent() {
        try {
            URL fxml = createFXMLUrl();
            FXMLLoader loader = new FXMLLoader(fxml);
            loader.setController(this);
            container = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public T getContainer() {
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
        Platform.runLater(code);
    }
}
