/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.integration;

import java.util.List;

/**
 * This class is a type of Discount that has a different implementation on how
 * to calculate the new item price after discount
 * @author abdig
 */
public class SaleDiscount implements Discount {
    private int customerId;
    private double discountRate;
    
    SaleDiscount(int customerId, double discountRate ){
    this.customerId = customerId;
    this.discountRate = discountRate;
    }
    
    public int getCustomer()
    {
        return this.customerId;
    }
    
    public double getDiscountRate()
    {
        return this.discountRate;
    }
    
    /**
     * Should set a new price for each item in a sale,
     * according to the discountRate of this
     * instance
     * @param items is the list of items that a new price after
     * discount should be applied to
     */
    public void applyDiscount(List<Item> items){
        for(Item item: items)
        {
                item.setPrice((1-this.discountRate)* item.getPrice());
        }
    }
}
