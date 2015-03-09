/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.configuration;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author dvily_000
 */
@Configuration
public class JavaFxApplicationConfiguration {
    
    @PostConstruct
    public void init() {
        new JFXPanel(); 
    }
    
    
}
