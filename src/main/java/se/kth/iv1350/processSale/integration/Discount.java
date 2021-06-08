/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.integration;

import java.util.List;

/**
 * Created to enable for the system to keep a list of different types of 
 * Discount with different implementations
 * @author abdig
 */
public interface Discount {
    
    public int getCustomer();
    
    public double getDiscountRate();
    
    /**
     * The method that will have different implementations/algorithms
     * according to the strategy pattern
     * @param items is the list of items in a Sale, where certain items might
     * be applicable for a discount, so that a new price can be set for that 
     * item
     */
    public void applyDiscount(List<Item> items);
}

