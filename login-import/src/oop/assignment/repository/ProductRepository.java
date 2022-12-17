/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.assignment.repository;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

import oop.assignment.dao.productDAO;


public class ProductRepository extends Repository{
    public List<productDAO> listProduct(){
        String sql;
        sql = "SELECT p.ID, p.Name, p.Sku, p.SupplierID, s.Name, p.Price, p.Quantity FROM products p JOIN suppliers s on s.ID = p.SupplierID WHERE p.IsActive = 1;";
        
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            List<productDAO> proList = new ArrayList<>();
            while (rs.next()){
                productDAO product = new productDAO();
                product.parse(rs);
                proList.add(product);
            }
            stmt.close();
            conn.close();
            return proList;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<productDAO> searchProduct(String searchContent ){
        //String sqlsearch = """ abc """;
        //sqlsearch = "SELECT p.Id, p.Name, p.Sku, p.SupplierID, s.Name, p.Price, p.Quantity FROM Products p JOIN Suppliers s on s.ID = p.SupplierID WHERE p.IsActive = 1 AND (p.Id LIKE ? Or p.Name LIKE ? Or p.Sku LIKE ? Or p.SupplierID LIKE ? Or s.Name LIKE ?);";
        String sql = "SELECT p.ID, p.Name, p.Sku, p.SupplierID, s.Name, p.Price, p.Quantity FROM products p JOIN suppliers s on s.ID = p.SupplierID WHERE p.IsActive = true AND (p.Id LIKE ? OR p.Name LIKE ? OR p.Sku LIKE ? OR p.SupplierID LIKE ? OR s.Name LIKE ?);";
        try {
            Connection conn = getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            searchContent = "%" + searchContent + "%";
            stmt.setString(1, searchContent);
            stmt.setString(2, searchContent);
            stmt.setString(3, searchContent);
            stmt.setString(4, searchContent);
            stmt.setString(5, searchContent);
            
            ResultSet rs = stmt.executeQuery();
            
            List<productDAO> proList = new ArrayList<>();
            while (rs.next()){
                productDAO product = new productDAO();
                product.parse(rs);
                proList.add(product);
            }
            
            stmt.close();
            conn.close();
            return proList;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void createProduct(productDAO pDAO){
       String sql = "INSERT INTO products(Sku, Name, SupplierID, Quantity, Price) VALUES (?, ?, ?, ?, ?)";
       
        try {
            Connection conn = getConnection();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pDAO.getSku());
            stmt.setString(2, pDAO.getName());
            stmt.setInt(3, pDAO.getSupplierID());
            stmt.setInt(4, pDAO.getQuantity());
            stmt.setDouble(5, pDAO.getPrice());
            stmt.execute();
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void updateProduct(productDAO pDAO){
        String sql = "UPDATE products SET SKU = ?, Name = ?, SupplierID = ?, Quantity = ?, Price = ? WHERE ID = ?;";
        try {
            Connection conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pDAO.getSku());
            pstmt.setString(2, pDAO.getName());
            pstmt.setInt(3, pDAO.getSupplierID());
            pstmt.setInt(4, pDAO.getQuantity());
            pstmt.setDouble(5, pDAO.getPrice());
            pstmt.setInt(6, pDAO.getId());
            
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void removeProduct(int id){
        //String sql = "UPDATE products SET IsActive = false WHERE ID = ?;";
        String sql = "DELETE FROM products WHERE ID = ?;";
        try {
            Connection conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
