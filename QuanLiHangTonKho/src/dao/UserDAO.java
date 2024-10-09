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
public class UserDAO {

    Connection conn = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public UserDAO() {
        try {
            conn = new ConnectDB().getConnection();
            statement = conn.createStatement();
        } catch (SQLException ex) {
        }
    }

    public boolean checkLogin(String TaiKhoan, String MatKhau, int MaQuyen) {
        String query = "SELECT * FROM users WHERE TaiKhoan='"
                + TaiKhoan
                + "' AND MatKhau='"
                + MatKhau
                + "' AND MaQuyen="
                + MaQuyen;

        try {
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
        }
        return false;
    }

}
