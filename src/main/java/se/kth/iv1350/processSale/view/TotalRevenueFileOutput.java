/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.model.ReceiptObserver;
import java.text.DecimalFormat;

/**
 * Prints the total revenue to a file
 * @author abdig
 */
public class TotalRevenueFileOutput extends RevenuePrinter {
    private FileLogger fileLogger = new FileLogger("revenueLog.txt");

    protected void doPrintTotalRevenue(double totalRevenue) {
        fileLogger.log("Total revenue is now: " + df.format(totalRevenue));
    }
    
    protected void handleErrors(Exception e){};
    
}
