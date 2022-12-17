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
public class productDAO {
    private int id;
    private String name;
    private String sku;
    private int supplierID;
    private String supplierName;
    private double price;
    private int quantity;
    
    public productDAO() {
    }

    public productDAO(int id, String name, String sku, int supplierID, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.supplierID = supplierID;
        this.price = price;
        this.quantity = quantity;
    }
    
    
    public productDAO(String name, String sku, int supplierID, int quantity ,double price) {
        this.name = name;
        this.sku = sku;
        this.supplierID = supplierID;
        this.price = price;
        this.quantity = quantity;
    }
    
    public productDAO(int quantity ) {
        
        this.quantity = quantity;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void parse(ResultSet rs) {
        try {
            this.setId(rs.getInt(1));
            this.setName(rs.getString(2));
            this.setSku(rs.getString(3));
            this.setSupplierID(rs.getInt(4));
            this.setSupplierName(rs.getString(5));
            this.setPrice(rs.getDouble(6));
            this.setQuantity(rs.getInt(7));
        } catch (SQLException ex) {
            Logger.getLogger(productDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
