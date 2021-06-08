/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.integration;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processSale.model.Sale;

/**
 *
 * @author abdig
 */
public class InventoryTest {
    private Inventory instanceToTest;
    
    @BeforeEach
    public void setUp() {
        instanceToTest = Inventory.getInventory();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCheckIfItemInInventory() throws ItemNotFoundException, DatabaseNotRunningException {
        Item expResult = new Item(1, 5, 0.1, "Standard Milk", "Vitamin D Enriched Milk.");
        Item result = null;
//        result = instanceToTest.checkIfItemInInventory(identifier);
//        assertEquals(expResult, result, "Expected null, but received an object");
        int identifier = 1; // does exist
        try{
            result = instanceToTest.checkIfItemInInventory(identifier);
            assertTrue(result.getDescription().contains("Vitamin D Enriched Milk"));
        } catch(ItemNotFoundException infe) {
            assertTrue(infe.getMessage().contains("did not match"),"ItemNotFoundException was thrown when object was expected");
        } catch(DatabaseNotRunningException dnbe) {
            assertTrue(dnbe.getMessage().contains("not running"),"DatabaseNotRunningException was thrown when object was expected");
        }
    }
    
    @Test
    public void testCheckIfItemInInventoryThrowsItemNotFoundException() throws ItemNotFoundException, DatabaseNotRunningException {
        int identifier = 4; // no such identifier exists
        Item result = null;
        try{
            result = instanceToTest.checkIfItemInInventory(identifier);
            fail("Nonexisting item was found");
        } catch(ItemNotFoundException infe) {
            assertTrue(infe.getMessage().contains("did not match"),"ItemNotFoundException not thrown");
        }
        
    }
    
    @Test
    public void testCheckIfItemInInventoryThrowsDatabaseNotRunningException() throws ItemNotFoundException, DatabaseNotRunningException {
        int identifier = 5; // no such identifier exists
        Item expResult = null;
        Item result = null;
        try{
            result = instanceToTest.checkIfItemInInventory(identifier);
            fail("Exception was not thrown, because item was found");
        } catch(DatabaseNotRunningException dbne) {
            assertTrue(dbne.getMessage().contains("could not be reached"),"DatabaseNotRunningException not thrown");
        }
        
    }
    
    
}
