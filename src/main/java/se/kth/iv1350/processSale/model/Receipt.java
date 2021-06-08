package se.kth.iv1350.processSale.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processSale.integration.Item;

/**
 * Creates a receipt with information about the sale that will be sent
 * to the printer for printing.
 * @author abdig
 */
public class Receipt {
    private LocalTime timeOfSale;
    private Store store;
    private List<PrintItem> printItems = new ArrayList<PrintItem>();
    private Sale sale;
    private double amountPaid;
    private double change;
    private double VATSale;
    private List<ReceiptObserver> receiptObservers = new ArrayList<>();
    
    /**
     * Creates a receipt with required info when the sale has been completed. Sets
     * values with given parameters but also calculates and sets values.
     * @param sale The sale that is processed at a retail store.
     * @param store The store processing the sale.
     * @param amountPaid The payment provided to complete the sale.
     * @param change The change to be given to the customer.
     */
    public Receipt(Sale sale, Store store, double amountPaid, double change){
        timeOfSale = LocalTime.now();
        this.store = store;
        this.sale = sale;
        this.amountPaid = amountPaid;
        this.change = change;
        calculateVATOfEntireSale();
        createPrintItems();
    }
    
    public LocalTime getTimeOfSale()
    {
        return this.timeOfSale;
    }
    
    public Store getStore()
    {
        return this.store;
    }
    
    public Sale getSale(){
        return this.sale;
    }

    public double getAmountPaid(){
        return this.amountPaid;
    }
    
    public double getChange(){
        return this.change;
    }
    
    public List<PrintItem> getPrintItems(){
        return this.printItems;
    }
    
    public double getVATOfEntireSale(){
        return this.VATSale;
    }
    
    private void calculateVATOfEntireSale()
    {
        for(Item item : this.sale.getItems())
        {
            this.VATSale += (item.getPrice() * item.getVATRate());
        }
    }
    
    private void createPrintItems()
    {
        List<Item> items = sale.getItems();
        
        for(int i = 0; i < items.size(); i++)
        {
            int identifier = items.get(i).getIdentifier();
            // Search if printItem exists
            PrintItem printItem = null;
            
            for (PrintItem p : printItems) {
                if (identifier == p.getIdentifier()) {
                    printItem = p;
                }
            }
            
            if(printItem != null)
            {
                for(int j = 0; j < printItems.size(); j++)
                {
                    if(printItems.get(j).getIdentifier() == identifier)
                        printItems.get(j).setQuantity();
                }
            }
            else
                printItems.add(new PrintItem(items.get(i)));
        }
    }

    private void notifyObservers() {
        
        for(ReceiptObserver observer : this.receiptObservers) {
            double rev = this.sale.getRunningTotal();
            observer.newRevenue(rev);
        }
    }
    /**
     * Provides references of the observing classes to Receipt for implementing
     * the observer pattern
     * @param receiptObservers is the list of observing classes
     */
    public void addReceiptObservers(List<ReceiptObserver> receiptObservers) {
        this.receiptObservers.addAll(receiptObservers);
        notifyObservers();
    }
    
}
