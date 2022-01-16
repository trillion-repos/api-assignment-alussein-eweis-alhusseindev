package com.ipaddressmanagement.ipaddressmanagementapi.Entity;


import javax.persistence.*;
import java.util.List;
import java.util.Arrays;

@Table
@Entity
public class IPManagement {

    @Id
    private String ipAddress;
    private String ipStatus = "available";

    //default constructor
    public IPManagement(){

    }

    public IPManagement(String ipAddress, String ipStatus){
        this.ipAddress = ipAddress;
        this.ipStatus = ipStatus;
    }

    //Getters
    public String getIpAddress(){
        return this.ipAddress;
    }

    public String getIpStatus(){
        return this.ipStatus;
    }

    //Setters
    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }

    public void setIpStatus(String ipStatus){
        this.ipStatus = ipStatus;
    }

    //toString method
    public String toString(){
        //"ID: " + this.id + " " +
        return "IP: " + this.ipAddress + " " + "Status: " + this.ipAddress + "\n";
    }

}
