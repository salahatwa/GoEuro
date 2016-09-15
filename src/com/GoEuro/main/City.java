/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.GoEuro.main;

/**
 * City POJO Class Used to Simulate parsing
 * @author salah
 */
public class City {
    
    private long id;
    private String name;
    private String type;
    private double latitude;
    private double longitude;

    public City() {
    }

    public City(long id, String name, String type, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

   
    
    
    
    
    
    

    /**
     * @return the _id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the _id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

   
    
    @Override
    public String toString()
    {
        return "City ID       : " + id  + "\n"+
               "City Name     : " + name + "\n"+
               "City Type     : " + type + "\n"+
               "City Position : " + "latitude : [" + getLatitude() +"]" + " , " + "longitude [" + getLongitude() +"]" +"\n";
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
}
