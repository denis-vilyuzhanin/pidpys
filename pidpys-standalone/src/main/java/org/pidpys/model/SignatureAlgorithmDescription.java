/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pidpys.model;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dvily_000
 */
public class SignatureAlgorithmDescription {
    private String id;
    private String name;
    private List<Integer> availableLength;

    public SignatureAlgorithmDescription() {
        
    }

    public SignatureAlgorithmDescription(String id, String name, Integer... availableLength) {
        this(id, name, Arrays.asList(availableLength));
    }
    
    public SignatureAlgorithmDescription(String id, String name, List<Integer> availableLength) {
        this.id = id;
        this.name = name;
        this.availableLength = availableLength;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getAvailableLength() {
        return availableLength;
    }

    public void setAvailableLength(List<Integer> availableLength) {
        this.availableLength = availableLength;
    }
    
    
    
    
}
