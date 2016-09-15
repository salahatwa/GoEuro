/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.GoEuro.test;

import com.GoEuro.main.GoEuro;
import javax.swing.JOptionPane;

/**
 *
 * @author salah
 */
public class GoEuroTest {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       
       GoEuro goEuroTest=new GoEuro();
       
       // Write Header To Status File
       goEuroTest.writeStatusTOFile("City"+"\t"+"Data");
       
       /**
        * Check Number of arguments
        * if user input one argument then select to run operation on this argument only
        * if user input more than one argument then do for_each loop to run multiple arguments
        * if user not any arguments system will exist from program
        */
        if (args.length == 1)
        {
            goEuroTest= new GoEuro(args[0]);
            goEuroTest.writeDataToCsv(goEuroTest.getCity_name() + ".csv", goEuroTest.excuteService(goEuroTest.getCity_name()));
        } 
        else if (args.length > 1) 
        {
          for (String city : args)
           {
             goEuroTest = new GoEuro(city);
             goEuroTest.writeDataToCsv(goEuroTest.getCity_name() + ".csv", goEuroTest.excuteService(goEuroTest.getCity_name()));
           }
        } 
        else
        {
             System.err.println("You Must Enter At Least One Argument.");
             JOptionPane.showMessageDialog(null,"Not Found Any Data Entered In Argument","You Must Enter At Least One Argument.",JOptionPane.ERROR_MESSAGE);
             goEuroTest.writeStatusTOFile("[ERROR] ********** No Arguments Found **********");
             System.exit(1);
        }

    }
    
}
