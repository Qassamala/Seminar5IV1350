/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.view;

import java.text.DecimalFormat;
import se.kth.iv1350.processSale.model.ReceiptObserver;

/**
 * The super class of all ReceiptObserver implementation. Contains code
 * that is shared between the inheriting classes.
 * @author abdig
 */
public abstract class RevenuePrinter implements ReceiptObserver {
    private double totalRevenue;
    DecimalFormat df = new DecimalFormat("###.###"); // used to round double
    
//    protected RevenuePrinter(){
//    }
    
        /**
     * Aggregates the totalRevenue property with the argument and calls the
     * logTotalRevenue for logging this information to a file found in the folder
     * @param revenue is final total of a sale
     */
    @Override
    public void newRevenue(double revenue) {
        calculateTotalRevenue(revenue); // Calculates the total revenue since
        // the start of the program
        printTotalRevenue();
    }
    
    private void calculateTotalRevenue(double revenue){
        this.totalRevenue += revenue;
    }
    
    private void printTotalRevenue(){
        try {
            doPrintTotalRevenue(this.totalRevenue);
        } catch(Exception e) {
            handleErrors(e);
        }
    }
    
    /**
     * Prints to console or logs to file the totalRevenue of sales processed
     * since the start of the program. Subclass must implement either the
     * writing to console or the logging to file.
     * @param totalRevenue the value to be logged to file or written to Console
     */
    protected abstract void doPrintTotalRevenue(double totalRevenue) throws Exception;
    
    protected abstract void handleErrors(Exception e);
}
