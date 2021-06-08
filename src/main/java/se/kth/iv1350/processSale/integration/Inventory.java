package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processSale.model.Sale;

/**
 *  Contains a list of all items that a customer can buy in a retails store.
 * Should only exist as singleton in the system.
 * @author abdig
 */
public class Inventory {
    private List<Item> items = new ArrayList<Item>();
    private static final Inventory INSTANCE = new Inventory();
    
    /**
     * Creates a new instance of the system's inventory. This is called from
     * startup. This is called once from startup and it creates an inventory 
     * of items.
     */
    private Inventory(){
        addItemsToInventory();
    }
    /**
     * Returns the only instance of the Inventory in the program
     * @return is the singleton instance of the Inventory
     */
    public static Inventory getInventory(){
        return INSTANCE;
    }
    
    public List<Item> getItems(){
        return this.items;
    }
    
    private void addItemsToInventory() {
        items.add(new Item(1, 5, 0.1, "Standard Milk", "Vitamin D Enriched Milk."));
        items.add(new Item(2, 7.2, 0.1, "Bread", "Whole Wheat Bread."));
        items.add(new Item(3, 3, 0.1, "Eggs", "12 Large Eggs."));
    }
    
    /**
     * Checks if an item with the same identifier already exists in the sale
     * and returns the Item if found, else null.
     * @param identifier The value used to find a match in the list of items.
     * @return Is a copy of the Item found or null if it doesn't exist in the
     * list of
     * items.
     * @throws if the identifier is not found, the ItemNotFoundException is
     * thrown
     * @throws if the given argument is 5, DatabaseNotRunningException is thrown 
     * to simulate not being able to reach the fake database
     */
    public Item checkIfItemInInventory(int identifier) throws ItemNotFoundException, DatabaseNotRunningException {
        
        if(identifier == 5)
            throw new DatabaseNotRunningException("Primary Database");
        
        for(Item item : this.items)
        {
            if(item.getIdentifier() == identifier)
                return new Item(item);
        }
        
        
//        for(int i = 0; i < items.size(); i++)
//        {
//            Item item = items.get(i);
//            if(item.getIdentifier() == identifier)
//                return new Item(item);
//        }
        throw new ItemNotFoundException(identifier);
    }

    public void updateInventory(Sale sale) {
        // Should update Inventory
    }
    
}
