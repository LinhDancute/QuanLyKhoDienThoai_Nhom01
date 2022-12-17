/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.assignment.repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import oop.assignment.dao.supplierDAO;
public class SupplierRepository extends Repository{
    public List<supplierDAO> getListSupplier() {
        String sql = "SELECT * FROM suppliers;";
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<supplierDAO> suppliers = new ArrayList<>();
            while (rs.next()) {
                supplierDAO s = new supplierDAO();
                s.parse(rs);
                suppliers.add(s);
            }
            stmt.close();
            conn.close();
            return suppliers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<supplierDAO> listSupplier(){
        String sql ="SELECT * FROM suppliers WHERE IsActive = 1";
        
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(sql);
            
            List<supplierDAO> suppList = new ArrayList<>();
            while (rs.next()) {
                supplierDAO supplier = new supplierDAO();
                supplier.parse(rs);
                suppList.add(supplier);
                
            }
            
            stmt.close();
            conn.close();
            return suppList;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<supplierDAO> searchSupplier(String searchContent){
        String sql;
        sql = "SELECT * FROM suppliers WHERE IsActive = 1 AND (ID LIKE ? OR Name LIKE ? OR Phone LIKE ? OR Address LIKE ?);";
        
        try {
            Connection conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            searchContent = "%" + searchContent + "%";
            pstmt.setString(1, searchContent);
            pstmt.setString(2, searchContent);
            pstmt.setString(3, searchContent);
            pstmt.setString(4, searchContent);
            
            ResultSet rs = pstmt.executeQuery();
            
            List<supplierDAO> suppList = new ArrayList<>();
            while (rs.next()){
                supplierDAO supplier = new supplierDAO();
                supplier.parse(rs);
                suppList.add(supplier);
                
            }
            
            pstmt.close();
            conn.close();
            return suppList;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void createSupplier(supplierDAO sDAO){
        String sql;
        sql = "INSERT INTO suppliers(ID, Name, Phone, Address) VALUES (?, ?, ?, ?);";
        
        try {
            Connection conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sDAO.getId());
            pstmt.setString(2, sDAO.getName());
            pstmt.setString(3, sDAO.getPhone());
            pstmt.setString(4, sDAO.getAddress());
            
            pstmt.execute();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
             
    }
    
    public void updateSupplier(supplierDAO sDAO){
        String sql;
        sql = "UPDATE suppliers SET  Name = ?, Phone = ?, Address = ? WHERE ID = ?;";
        
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, sDAO.getName());
            pstmt.setString(2, sDAO.getPhone());
            pstmt.setString(3, sDAO.getAddress());
            pstmt.setInt(4, sDAO.getId());
            
            pstmt.execute();
            pstmt.close();
            conn.close();
             
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    public void removeSupplier(int id){
        
        String sql = "DELETE FROM suppliers WHERE ID = ?;";
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
