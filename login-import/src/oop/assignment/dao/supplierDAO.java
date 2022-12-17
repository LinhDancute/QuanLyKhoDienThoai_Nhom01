/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.assignment.dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguyendung
 */
public class supplierDAO {
    private int id;
    private String name;
    private String phone;
    private String address;
    private boolean isActive;

    

    public supplierDAO() {
    }
    
    public supplierDAO(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    public supplierDAO(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    public supplierDAO(String name, String phone, String address,boolean isActive) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
    }
    
    public supplierDAO(int id, String name, String phone, String address, boolean isActive) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public void parse(ResultSet rs) {
        try {
            this.setId(rs.getInt(1));
            this.setName(rs.getString(2));
            this.setIsActive(rs.getBoolean(3));
            this.setPhone(rs.getString(4));
            this.setAddress(rs.getString(5));
        } catch (SQLException ex) {
            Logger.getLogger(supplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
