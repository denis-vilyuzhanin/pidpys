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

    private Logger logger = Logger.getLogger(SecurePasswordField.class.getName());
    
    public char[] fetchPassword() {
        Content content = getContent();
        try {
            return fetchInSecureWay(content);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Can't fetch password in secure way. So unsecure way is used.", ex);
            return fetchInUnsecureWay(content);
        } finally {
            clear();
        }
    }

    private char[] fetchInSecureWay(Content content) 
            throws IllegalArgumentException, NoSuchFieldException, SecurityException, IllegalAccessException {
        Field field = content.getClass().getDeclaredField("characters");
        field.setAccessible(true);
        StringBuilder builder = (StringBuilder) field.get(content);
        char[] chars = new char[builder.length()];
        builder.getChars(0, builder.length(), chars, 0);
        hidePassword(builder);
        return chars;
    }

    private char[] fetchInUnsecureWay(Content content) {
        return content.get().toCharArray();
    }

    private void hidePassword(StringBuilder builder) {
        for(int i = 0; i < builder.length(); i++) {
            builder.setCharAt(i, ' ');
        }
    }
}
