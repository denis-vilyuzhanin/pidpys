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
    private boolean customParameters;
    private String algorithm;
    private int keyLength;
    private Password password;
    private Password passwordConfirmirmation;
    private File store;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public File getStore() {
        return store;
    }

    public void setStore(File store) {
        this.store = store;
    }

    public Password getPasswordConfirmation() {
        return passwordConfirmirmation;
    }

    public void setPasswordConfirmation(Password passwordConfirm) {
        this.passwordConfirmirmation = passwordConfirm;
    }

    public boolean isCustomParameters() {
        return customParameters;
    }

    public void setCustomParameters(boolean customParameters) {
        this.customParameters = customParameters;
    }
    
    
    
}
