/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.*;
import java.sql.DriverManager;

/**
 *
 * @author OS
 */
public class JDBCUtils {

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLHTK;user=sa;password=trungnghia282003");
//            if (conn != null) {
//                System.out.println("Connected successfully.");
//            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.toString());
        }
        return conn;
    }
}
