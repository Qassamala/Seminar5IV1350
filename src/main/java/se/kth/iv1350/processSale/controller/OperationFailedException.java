/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.controller;

/**
 *
 * @author abdig
 */
public class OperationFailedException extends Exception {
    
    /**
     * Creates a more general exception with a message and root cause
     * @param message The exception message
     * @param rootCause The exception that caused this OperationFailedException
     */
    public OperationFailedException(String message, Exception rootCause) {
        super(message, rootCause);
    }
    
}
