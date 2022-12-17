package com.mycompany.oop_netbean;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/usermanagement";

    private static final String USER_NAME = "root";

    private static final String PASSWORD = "yunbrayyunh";
    /*USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTILPE SQL SERVER INSTANCE(s)*/
 /*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    public Connection getConnection() throws Exception {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (ClassNotFoundException ex) {
            System.out.println("connect failure!");
        }
        return conn;
    }

    
}
