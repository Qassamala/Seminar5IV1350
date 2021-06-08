package se.kth.iv1350.processSale.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.Accounting;
import se.kth.iv1350.processSale.integration.DiscountRegistry;
import se.kth.iv1350.processSale.integration.Inventory;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.model.Register;
import se.kth.iv1350.processSale.model.Store;

/**
 *
 * @author abdig
 */
public class ViewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    
    @BeforeEach
    public void setUp() {
        Inventory inventory = Inventory.getInventory();
        Accounting accounting = new Accounting();
        Printer printer = new Printer();
        Register register = new Register();
        Store store = new Store();
        DiscountRegistry discountRegistry = DiscountRegistry.getInstance();
        Controller controller = new Controller(inventory, accounting, printer, register, store, discountRegistry);
        instanceToTest = new View(controller);
        
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }
    
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testRunFakeExecution() {
        instanceToTest.runFakeExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "started";
        assertTrue(printout.contains(expectedOutput), "UI did not start correctly");
    }
    
}
