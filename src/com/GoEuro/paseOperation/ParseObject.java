/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GoEuro.paseOperation;

import com.GoEuro.main.City;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author salah
 */
public class ParseObject {
    /*
     * Parse Json Array
     */
    public static List<City> parseArray(String jsonArray) {
        
        List<City> cities=new ArrayList<>();
        try {
            JSONArray jsonarray = new JSONArray(jsonArray);
            for (int i = 0; i < jsonarray.length(); i++)
            {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                City city=parseJson( jsonobject.toString() );
                cities.add(city);
                System.out.println(city.toString());
            }
        }
        catch (JSONException ex)
        {
            System.err.println("ERROR: "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error parsing Json Array" ,"Error parsing Json Array: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        return cities;
    }

    /*
     * Parse Json Object
     */
    public static City parseJson(String jsonData) {
        City city = new City();

        try {


            JSONObject obj = new JSONObject(jsonData);
            city.setId(obj.getLong("_id"));
            city.setName(obj.getString("name"));
            city.setType(obj.getString("type"));
            
            JSONObject obj2 = obj.getJSONObject("geo_position");
            city.setLatitude(obj2.getDouble("latitude"));
            city.setLongitude(obj2.getDouble("longitude"));


        }
        catch (JSONException ex) 
        {
            System.err.println("ERROR: "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error parsing Json Object" ,"Error parsing Json Object: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }

        return city;
    }

}
