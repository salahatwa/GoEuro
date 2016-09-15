/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GoEuro.main;

import com.GoEuro.CsvWrite.WriteCsvFile;
import com.GoEuro.paseOperation.ParseObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 *
 * @author salah
 */
public class GoEuro implements CommonSetting {

    private String city_name;

    public GoEuro() {
    }

    public GoEuro(String city_name) {
        this.city_name = city_name;
    }

    /**
     * @return the city_name
     */
    public String getCity_name() {
        return city_name;
    }

    /**
     * @param aCity_name the city_name to set
     */
    public void setCity_name(String aCity_name) {
        city_name = aCity_name;
    }

    /**
     * Read Data From URL
     * @param cityName pass parameter city name to given URL
     * http://api.goeuro.com/api/v2/position/suggest/en/CITY_NAME
     * @return Json String Array and Pass It To parseArray(Json String)
     */
    public String readFromURL(String cityName) 
    {
        String cityInfo = null;
        try {
            //"Berlin"
            URL oracle = new URL(GO_EURO_URL + cityName);
            URLConnection yc = oracle.openConnection();
            BufferedReader line = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = line.readLine()) != null) {
                cityInfo += inputLine;
            }
            line.close();
        }
        catch (IOException ex) 
        {
            System.err.println("Error In URL Reading : "+ex.getMessage());
        }

        return cityInfo;
    }

    /**
     * service Operation method read data from URL and return String value
     * ensure string value begin with " [ " and End with " ] "
     * ParseObject used to parse jsonArray to subset of fields or documents
     * returns parsed document to list of cities
     * @param cityName passed to readFromURL method to return string value
     * @return list of cities 
     */
    public List<City> excuteService(String cityName) 
    {
        String jsonObject = readFromURL(cityName);
        jsonObject = jsonObject.substring(jsonObject.indexOf("["), jsonObject.lastIndexOf("]") + 1);
        List<City> cities = ParseObject.parseArray(jsonObject);
        return cities;
    }

    /**
     * We Using Super CSV jar file to mapping city object and write to CSV
     * Used to write list of objects (cities) to CSV file
     * check if list is empty or not, if list empty write to status file Not_Found else Found
     * CSV file in the following format "id,name,type,latitude,longitude"
     * @param fileName pass file name or file path
     * @param cities pass list of cities 
     */
    public void writeDataToCsv(String fileName, List<City> cities)
    {
        if (!cities.isEmpty()) 
        {
            WriteCsvFile.writeCSVFile(fileName, cities);
            writeStatusTOFile(city_name+"\t"+"Found_Data");
        }
        else 
        {
            System.err.println("Sorry we cannot found Apporiat Data For Your " + city_name + " city");
            writeStatusTOFile(city_name+"\t"+"Not_Found_Data");
        }
    }

    /**
     * Status file to check which city have data and which city not have data
     * it's important 
     * @param statusText 
     */
    public void writeStatusTOFile(String statusText) 
    {
        File file=new File(OUTPUT_FOLDER);
           if(!file.exists())
              {
                file.mkdirs();
              }
                
        try (FileWriter fw = new FileWriter(OUTPUT_FOLDER+"/"+"status.txt", true);

                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
                {
                 out.println(statusText);
                } 
        catch (IOException e) 
        {
            System.err.println("ERROR IN WRITING STATUS:"+e.getMessage());
        }
    }
}
