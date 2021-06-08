/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

/**
 *
 * @author abdig
 */
public class FileLogger {
    private PrintWriter logStream;
    
    /**
     * Creates a logger that can write to a file
     * @param fileName the name of the log file
     */
    public FileLogger(String fileName){
        try {
            logStream = new PrintWriter(new FileWriter(fileName), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }
    
    /**
     * Writes to the log file
     * @param message to be written to the log file
     */
    public void log(String message) {
        logStream.println(LocalTime.now()+ ": " + message);
    }
    
}
