/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.model.ReceiptObserver;
import java.text.DecimalFormat;

/**
 *  This class should display the totalRevenue generated by the Sale.
 * @author abdig
 */
public class TotalRevenueView implements ReceiptObserver {
    private double totalRevenue;
    
    public TotalRevenueView(){
    }
    DecimalFormat df = new DecimalFormat("###.###"); // used to round double
    
    /**
     * Aggregates the totalRevenue property with the argument and calls the
     * printTotalRevenuemethod for printing this information
     * @param revenue is final total of a sale
     */
    @Override
    public void newRevenue(double revenue){
        this.totalRevenue += revenue;
        printTotalRevenue();
    }
    
    private void printTotalRevenue()
    {
        System.out.println("TotalRevenueView: Chiching! Total revenue is now: " + df.format(this.totalRevenue));
    }
    
}