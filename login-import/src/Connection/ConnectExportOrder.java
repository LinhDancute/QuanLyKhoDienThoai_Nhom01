package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Main.exportOrder;

public class ConnectExportOrder {
	private Connection conn;
	ResultSet rs = null;
	int q;
	public ConnectExportOrder() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/qldt","root","admin");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.print(conn);
	}
	public boolean addProducts(exportOrder i) {
		String sql = "INSERT INTO tblexportOrder(ExportOrderID,ProductID,SupplierID, Name, Price, Amount,Date)"
				+ "VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, i.getIdE());
			ps.setInt(2, i.getProductID());
			ps.setInt(3, i.getSupplierID());
			ps.setString(4, i.getName());
			ps.setLong(5, i.getPrice());
			ps.setLong(6, i.getAmount());
			ps.setString(7, i.getDate());
			return  ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	} 
	
	
	public ArrayList<exportOrder>getListexportOrder(){
		ArrayList<exportOrder> list = new ArrayList <>();
		String sql = "SELECT * FROM tblexportOrder";
		list.clear();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	exportOrder i = new exportOrder();
            	i.setIdE(rs.getString("ExportOrderID"));
            	i.setProductID(rs.getInt("ProductID"));
            	i.setSupplierID(rs.getInt("SupplierID"));
            	i.setName(rs.getString("Name"));
            	i.setPrice(rs.getLong("Price"));
            	i.setAmount(rs.getLong("Amount"));
            	i.setDate(rs.getString("Date"));
            	list.add(i);
            }
            conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		new ConnectExportOrder();
	}
}
