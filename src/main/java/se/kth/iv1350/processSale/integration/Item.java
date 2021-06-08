package se.kth.iv1350.processSale.integration;

/**
 *  Represents an item that can be bought in a retail store
 * @author abdig
 */
public class Item {
    private int identifier;
    private double price;
    private double VATRate;
    private String name;
    private String description;
    
    /**
     * Creates an item that represents an item in a
     * retail store.
     * @param identifier    A type of item has an int identifier, and it is unique to
     * the type of item, such as standard milk.
     * @param price The price of the item
     * @param VATRate The VATRate of the item
     * @param name  Name of an item
     * @param description   A short description of the item
     */
    protected Item(int identifier, double price, double VATRate, String name, String description){
        this.identifier = identifier;
        this.price = price;
        this.VATRate = VATRate;
        this.name = name;
        this.description = description;
    }
    
    /**
     * Creates a copy of the item, so price can be set
     * @param item is the item to copy
     */
    public Item(Item item)  
    {  
        identifier = item.identifier;
        price = item.price;
        VATRate = item.VATRate;
        name = item.name;
        description = item.description;
    }
    
    public int getIdentifier(){
        return this.identifier;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    /**
     * Should only be called when applying discounts
     * @param price the new price after discount.
     */
    protected void setPrice(double price){
        this.price = price;
    }

    public double getVATRate(){
        return this.VATRate;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getDescription(){
        return this.description;
    }
}
