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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Arrays.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Password other = (Password) obj;
        if (!Arrays.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }
        
    
    
    
}
