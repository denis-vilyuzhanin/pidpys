/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.model;

import java.util.Arrays;

/**
 *
 * @author dvily_000
 */
public class Password {
 
    private char[] value;
    
    
    public Password(char[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }
    
    public void clear() {
        Arrays.fill(value, (char)0);
    }
    
    public int getLength() {
        return this.value.length;
    }

    public boolean isEmpty() {
        return getLength() == 0;
    }
        
    
    
}
