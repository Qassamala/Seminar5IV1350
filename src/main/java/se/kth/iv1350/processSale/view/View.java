package se.kth.iv1350.processSale.view;

import java.text.DecimalFormat;
import java.util.List;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.Discount;
import se.kth.iv1350.processSale.integration.Item;
import se.kth.iv1350.processSale.integration.ItemNotFoundException;
import se.kth.iv1350.processSale.model.Sale;

/**
 * This is a placeholder for the real view. It contains a hard-coded execution with calls
 * to all system operations in the controller.
 * @author abdig
 */
public class View {
    private Controller controller;
    private FileLogger fileLogger = new FileLogger("log.txt");
    private ErrorMessageHandler errorMessageHandler = new ErrorMessageHandler();
    
    /**
     * Creates a new instance that uses the specified controller for all calls
     * to other layers.
     * @param controller The controller to use for all calls to other layers.
     */
    public View (Controller controller) {
        this.controller = controller;
        controller.addReceiptObserver(new TotalRevenueView());
        controller.addReceiptObserver(new TotalRevenueFileOutput());
    }
    DecimalFormat df = new DecimalFormat("###.###"); // used to round double
    
    /**
     * Performs a fake sale, by calling all system operations in the controller.
     */
    public void runFakeExecution() {
        controller.startSale();
        System.out.println("A new sale has been started.");
        
        addItemToSale(1);
        addItemToSale(1);
        addItemToSale(2);
        addItemToSale(4);
        addItemToSale(5);
        addItemToSale(3);
        
        System.out.println("Discount requested.");
        discountRequest(3);
      
        System.out.println("Ending sale...");
        System.out.println("Total sum for payment: " + df.format(controller.endSale()));
        
        System.out.println("Entering amount 30 as payment...");
        double change = controller.enterAmountPaid(30);
        System.out.println("Change to give to customer: " + df.format(change));
        
        // Testing that total revenue is shown correctly
        controller.startSale();
        System.out.println("A new sale has been started.");
        addItemToSale(1);
        
        System.out.println("Discount requested.");
        discountRequest(3);
        System.out.println("Ending sale...");
        System.out.println("Total sum for payment: " + df.format(controller.endSale()));
        System.out.println("Entering amount 10 as payment...");
        change = controller.enterAmountPaid(10);
        System.out.println("Change to give to customer: " + df.format(change));
        

    }
    
    private void addItemToSale(int identifier){
        System.out.println("Adding item with identifier " + identifier + " to sale...");
        Item item = null;
        try {
              item = controller.addItemToSale(identifier);
        } catch(ItemNotFoundException itemNotFoundException) {
            errorMessageHandler.showErrorMessage(itemNotFoundException.getMessage());
        } catch(Exception exc) {
            errorMessageHandler.showErrorMessage(exc.getMessage());
            fileLogger.log("Log for devs: " + exc.getCause().getMessage());
        }
      
        
        if(item != null)
        {
            System.out.println("Item description: " + item.getDescription());
            System.out.println("Item price (incl VAT): " + df.format(item.getPrice()* (1 + item.getVATRate())));
            System.out.println("Running Total is: " + df.format(controller.getRunningTotal()));
        }
    }
    
    private void discountRequest(int customerId)
    {
        List<Discount> discounts = controller.getCustomerDiscounts(customerId);
        
        if (discounts.isEmpty()) {
            System.out.println("No discounts found");
        } else {
            System.out.println("Applying found discounts.");
            controller.applyCustomerDiscounts(discounts);
        }
    }
}
