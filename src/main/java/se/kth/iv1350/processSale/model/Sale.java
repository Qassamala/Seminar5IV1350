package se.kth.iv1350.processSale.model;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processSale.integration.Discount;
import se.kth.iv1350.processSale.integration.Item;

/**
 * One single sale made by one single customer and payed with one payment.
 * @author abdig
 */
public class Sale {
    private double runningTotal;
    private List<Item> items = new ArrayList<Item>();
    
    /**
     * Creates a new instance and...finish this comment!!!
     */
    public Sale() {
    
    }
    
    /**
     * Checks if an item with the same identifier already exists in the sale
     * and returns the Item if found, else null.
     * @param identifier The value used to find a match in the list of items.
     * @return Is a copy of the Item found or null if it doesn't exist in the list of
     * items.
     */
    public Item checkIfItemInSale(int identifier){
        
        for(int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            if(item.getIdentifier() == identifier)
                return new Item(item);
        }
        return null;
    }
    
    /**
     * Adds an Item to the list of items of the current Sale.
     * @param item The item that should be added to the list of items.
     */
    public void addItemToSale(Item item)
    {
        this.items.add(item);
    }
    
    /**
     * Gets the runningTotal of the Sale, by first calling private
     * methods that calculate it and set it.
     * @return is the new runningTotal of the Sale
     */
    public double getRunningTotal(){
        setRunningTotal(calculateRunningTotal());
        return this.runningTotal;
    }
    
    public List<Item> getItems(){
        return this.items;
    }
    
    private double calculateRunningTotal(){
        double priceIncludingVAT = 0;
        
        
        for(Item item : this.items)
        {
            priceIncludingVAT += (item.getPrice() * (1 + (item.getVATRate())));
        }
        
        return priceIncludingVAT;
    }
    
    /**
     * Sets the runningTotal of the sale by iterating over the sale's entire list of items
     * , calculating the net price of each item and adding it to the runningTotal
     * and adding this to the runningTotal
     */
    private void setRunningTotal(double runningTotal){
        
        this.runningTotal = runningTotal;
    }

    /**
     * Returns the difference of the amount paid and the sale's total amount. 
     * @param amount The amount that the Cashier entered as customer payment.
     * @return The difference between amount paid and the sale's final total
     * amount.
     */
    public double calculateChange(double amount) {
        return amount - this.getRunningTotal();
    }
    
    /**
     * This method applies polymorphism by dynamically calling different 
     * implementations of Discount. These implementations utilizes different
     * algorithms of applying discounts to a sale.
     * @param discounts the list of discounts, that can contain different shapes
     * of Discount
     */
    public void applyDiscounts(List<Discount> discounts){
        
        for(Discount discount: discounts)
        {
            discount.applyDiscount(this.items);
        }
        
    }
}
