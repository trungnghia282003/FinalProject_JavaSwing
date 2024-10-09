/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class QuyenDAO {

    Connection conn = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // Constructor method
    public QuyenDAO() {
        try {
            conn = new ConnectDB().getConnection();
            statement = conn.createStatement();
        } catch (SQLException ex) {
        }
    }

    public ResultSet getAll() {
        try {
            String query = "SELECT * FROM dbo.Quyen";
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
        }
        return resultSet;
    }
}
