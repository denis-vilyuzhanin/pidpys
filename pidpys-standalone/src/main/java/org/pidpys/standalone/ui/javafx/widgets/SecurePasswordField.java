/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.standalone.ui.javafx.widgets;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.PasswordField;

/**
 *
 * @author dvily_000
 */
public class SecurePasswordField extends PasswordField {

    public char[] fetchPassword() {
        Content content = getContent();
        try {
            Field field = content.getClass().getDeclaredField("characters");
            field.setAccessible(true);
            StringBuilder builder = (StringBuilder) field.get(content);
            char[] chars = new char[builder.length()];
            builder.getChars(0, builder.length(), chars, 0);
            for(int i = 0; i < builder.length(); i++) {
                builder.setCharAt(i, ' ');
            }
            return chars;
        } catch (Exception ex) {
            return content.get().toCharArray();
        } finally {
            clear();
        }
        
        
    }
}
