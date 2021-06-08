/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processSale.integration.Accounting;
import se.kth.iv1350.processSale.integration.DatabaseNotRunningException;
import se.kth.iv1350.processSale.integration.DiscountRegistry;
import se.kth.iv1350.processSale.integration.Inventory;
import se.kth.iv1350.processSale.integration.Item;
import se.kth.iv1350.processSale.integration.ItemNotFoundException;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.model.ReceiptObserver;
import se.kth.iv1350.processSale.model.Register;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.Store;

/**
 *
 * @author abdig
 */
public class ControllerTest {
    private Controller controllerInstanceToTest;
    
    public ControllerTest() {
    }
    
    @BeforeEach
    public void setUp() {
        Inventory inventory = Inventory.getInventory();
        Accounting accounting = new Accounting();
        Printer printer = new Printer();
        Register register = new Register();
        Store store = new Store();
        DiscountRegistry discountRegistry = DiscountRegistry.getInstance();
        controllerInstanceToTest = new Controller(inventory, accounting, printer, register, store, discountRegistry);
        controllerInstanceToTest.startSale();
    }
    
    @AfterEach
    public void tearDown() {
        controllerInstanceToTest = null;
    }

//    @Test
//    public void testStartSale() {
//        System.out.println("startSale");
//        Controller instance = null;
//        instance.startSale();
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testAddItemToSale() throws ItemNotFoundException, DatabaseNotRunningException, OperationFailedException  {
        int identifier = 1; // should not throw exception
        Item result = null;
        try{
            result = controllerInstanceToTest.addItemToSale(identifier);
            assertTrue(result.getDescription().contains("Vitamin D Enriched Milk"));
        } catch(Exception e) {
            fail("Exception was thrown when" + result.getName() + "does exist");
        }
    }
    
    @Test
    public void testIfAddItemToSaleThrowsOperationFailedException() throws ItemNotFoundException, DatabaseNotRunningException, OperationFailedException  {
        int identifier = 5; // should casue DatabaseNotRunningException
        Item result = null;
        try{
            result = controllerInstanceToTest.addItemToSale(identifier);
            fail("Nonexisting item was found");
        } catch(OperationFailedException ofe) {
            assertTrue(ofe.getMessage().contains("Check network"),"OperationFailedException not thrown");
        }
        
    }

//    @Test
//    public void testGetRunningTotal() {
//        System.out.println("getRunningTotal");
//        Controller instance = null;
//        double expResult = 0.0;
//        double result = instance.getRunningTotal();
//        assertEquals(expResult, result, 0.0);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testEndSale() {
//        System.out.println("endSale");
//        Controller instance = null;
//        double expResult = 0.0;
//        double result = instance.endSale();
//        assertEquals(expResult, result, 0.0);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testEnterAmountPaid() {
//        System.out.println("enterAmountPaid");
//        double amount = 0.0;
//        Controller instance = null;
//        double expResult = 0.0;
//        double result = instance.enterAmountPaid(amount);
//        assertEquals(expResult, result, 0.0);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAddReceiptObserver() {
//        System.out.println("addReceiptObserver");
//        ReceiptObserver obs = null;
//        Controller instance = null;
//        instance.addReceiptObserver(obs);
//        fail("The test case is a prototype.");
//    }
    
}
