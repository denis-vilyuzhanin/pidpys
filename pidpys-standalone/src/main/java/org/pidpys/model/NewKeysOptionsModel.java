/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.model;

import java.io.File;

/**
 *
 * @author dvily_000
 */
public class NewKeysOptionsModel {
    private SignatureAlgorithm algorithm;
    private int keyLength;
    private String password;
    private File store;

    public SignatureAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(SignatureAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public File getStore() {
        return store;
    }

    public void setStore(File store) {
        this.store = store;
    }

    
    
}
