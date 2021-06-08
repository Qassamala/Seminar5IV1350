/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Only one instance of DiscountRegistry should exist and is thus created as 
 * singleton.
 * @author abdig
 */
public class DiscountRegistry {
    private static final DiscountRegistry INSTANCE = new DiscountRegistry();
    private List<Discount> discounts = new ArrayList<Discount>();
    
    
    private DiscountRegistry() {
           discounts.add(new SaleDiscount(2, 0.01));
           discounts.add(new ItemDiscount(2, new Item(1, 5, 0.1, "Standard Milk", "Vitamin D Enriched Milk.") ,0.1));
           discounts.add(new SaleDiscount(3, 0.1));
    }
    
    /**
     * Returns a list of discounts for a specific customer
     * @param id is the id of the customer
     * @return is the list of discounts for a specific customerId
     */
    public List<Discount> getCustomerDiscounts(int id){
        List<Discount> customerDiscounts = new ArrayList<Discount>();
        for(Discount d : this.discounts)
        {
            if (d.getCustomer()== id) {
                customerDiscounts.add(d);
            }
        }
        
        return customerDiscounts;
    }
    
    /**
     * Returns the singleton instance of discountRegistry
     * @return is the single instance of discountRegistry
     */
    public static DiscountRegistry getInstance(){
        return INSTANCE;
    }
}
