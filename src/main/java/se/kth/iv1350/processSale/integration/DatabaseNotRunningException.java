/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.integration;

/**
 *  This exception is thrown when you cannot connect to the Database
 * @author abdig
 */
public class DatabaseNotRunningException extends Exception {
    private String databaseName = "Primary Database";
 
    /**
     * Creates an exception with a a set value for the attribute
     */
    public DatabaseNotRunningException(String databaseName){
        super("The "+ databaseName + " could not be reached.");
        this.databaseName = databaseName;
    }
    
    public String getDatabaseName(){
        return this.databaseName;
    }
}

