package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import Main.importOrder;
import javax.swing.JOptionPane;

//import GiaoDien.ImportOrder;

public class ConnectImportOrder {
	private Connection conn;
	ResultSet rs = null;
	int q;
	public ConnectImportOrder() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/qldt","root","admin");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.print(conn);
	}
	public boolean addProducts(importOrder i) {
		String sql = "INSERT INTO tblimportorder(ImportOrderID,ProductID,SupplierID, Name, Price, Amount,Date)"
				+ "VALUES(?,?,?,?,?,?,?)";
                //String sqlString ="UPDATE products SET Quantity = Quantity + ? WHERE ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, i.getIdI());
			ps.setInt(2, i.getProductID());
			ps.setInt(3, i.getSupplierID());
			ps.setString(4, i.getName());
			ps.setLong(5, i.getPrice());
			ps.setLong(6, i.getAmount());
			ps.setString(7, i.getDate());
    
                        
//			ps.setLong(6, getDate);
			return  ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
                
		return false;
		
	} 
        
        public void addProduct(importOrder i){
            
             
            String sqlString ="UPDATE products SET Quantity = Quantity + ? WHERE ID = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sqlString);
                ps.setInt(2, i.getProductID());
                ps.setLong(1, i.getAmount());
                System.out.print(sqlString);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Dont add");
            }
            
        }
	
	
	public ArrayList<importOrder>getListimportOrder(){
		ArrayList<importOrder> list = new ArrayList <>();
		String sql = "SELECT * FROM tblimportOrder";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	importOrder i = new importOrder();
            	i.setIdI(rs.getString("ImportOrderID"));
            	i.setProductID(rs.getInt("ProductID"));
            	i.setSupplierID(rs.getInt("SupplierID"));
            	i.setName(rs.getString("Name"));
            	i.setPrice(rs.getLong("Price"));
            	i.setAmount(rs.getLong("Amount"));
            	i.setDate(rs.getString("Date"));
            	list.add(i);
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		new ConnectImportOrder();
	}
}
