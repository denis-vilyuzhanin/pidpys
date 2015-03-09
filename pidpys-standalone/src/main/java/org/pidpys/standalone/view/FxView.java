/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.view;

import javafx.application.Platform;



/**
 *
 * @author dvily_000
 */
public class FxView {
    
    protected void execute(Runnable code) {
        Platform.runLater(code);
    }
}
