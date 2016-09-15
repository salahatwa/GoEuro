/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GoEuro.CsvWrite;

import com.GoEuro.main.City;
import com.GoEuro.main.CommonSetting;
import static com.GoEuro.main.CommonSetting.OUTPUT_FOLDER;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import javax.swing.JOptionPane;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

/**
 *
 * @author salah
 */
public class WriteCsvFile implements CommonSetting{
    
  /**
   * Using Super CSV jar lib
   * @param csvFileName file name or file path
   * @param citis pass cities list to write every city into one line in CSV file 
   */
  public static void writeCSVFile(String csvFileName, List<City> citis)
  {
      File file=new File(OUTPUT_FOLDER);
      if(!file.exists())
        {
         file.mkdirs();
        }
           
    ICsvBeanWriter beanWriter = null;
    
    // Generate header for the CSV
    Field fields[] = new City().getClass().getDeclaredFields();
    String[] header = new String[fields.length];
    for(int i = 0; i < fields.length; i++) 
    {
        header[i] = fields[i].getName();
    }
 
    try {
        beanWriter = new CsvBeanWriter(new FileWriter(OUTPUT_FOLDER+"/"+csvFileName),
                CsvPreference.STANDARD_PREFERENCE);
        
        beanWriter.writeHeader(header);
 
        for (City city : citis) 
        {
            beanWriter.write(city, header);
        }
 
    }
    catch (IOException ex) 
    {
        System.err.println("Error writing the CSV file: " + ex.getMessage());
        JOptionPane.showMessageDialog(null, "Error writing the CSV file" ,"Error writing the CSV file: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
    } 
    finally
    {
        if (beanWriter != null)
        {
            try
            {
                beanWriter.close();
            }
            catch (IOException ex) 
            {
                System.err.println("Error closing the writer: " + ex);
                JOptionPane.showMessageDialog(null, "Error closing the writer" ,"Error closing the writer: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
}
