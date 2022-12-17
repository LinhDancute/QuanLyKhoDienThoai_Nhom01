package com.mycompany.oop_netbean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class LoadDAO {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<Instructor> getAllInstructors(){
		String query = "select * from user";
		try {
			List<Instructor> list = new ArrayList<>();
			con = new DBContext().getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Instructor(rs.getInt(1), 
					rs.getString(2), 
					rs.getString(3),
					rs.getString(4),
					rs.getString(5), 
					rs.getString(6),
					rs.getInt(7),
					rs.getDate(8),
                                        rs.getString(9)));
                                
			}
			return list;
		} catch (Exception e) {
			return null;
		}
                
	}
}
