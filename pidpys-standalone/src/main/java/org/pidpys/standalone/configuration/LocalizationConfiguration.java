/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.configuration;

import java.util.ResourceBundle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author dvily_000
 */
@Configuration
public class LocalizationConfiguration {
    
    @Bean
    public ResourceBundle localization() {
        return ResourceBundle.getBundle("org.pidpys.standalone.localization.application");
    }
}
