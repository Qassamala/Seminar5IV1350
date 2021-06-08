/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.model;

/**
 *An interface that is used according to the Observer pattern. A class that is
 * dependent on changes in the observed class Receipt, needs to implement this
 * interface. When the Receipt is created, the revenue is deemed final and
 * newRevenue is invoked. 
 * @author abdig
 */
public interface ReceiptObserver {
    
    /**
     * Invoked when a receipt is created.
     * @param revenue The revenue of the sale
     */
    void newRevenue(double revenue);
    
}
