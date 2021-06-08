/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.integration;

/**
 * Exception thrown when the provided identifier is not found in the Inventory.
 * @author abdig
 */
public class ItemNotFoundException extends Exception {
    private int identifier;
    
    /**
     * Creates a new ItemNotFoundException
     * @param identifier the provided identifier when searching the Inventory
     */
    public ItemNotFoundException(int identifier){
        super("The provided identifier " + identifier + ", did not match an existing item.");
        this.identifier = identifier;
    }
    
    public int getIdentifier(){
        return this.identifier;
    }
    
}

