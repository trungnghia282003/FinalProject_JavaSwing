/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.UsersDAO;
import database.JDBCUtils;
import model.Users;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class UsersDAOImpl implements UsersDAO {

    // Method to retrieve all users
    @Override
    public List<Users> getAllUsers() {
        List<Users> userList = new ArrayList<>();
        String query = "SELECT * FROM Users";

        try (Connection conn = new JDBCUtils().getConnection(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setEmployeeId(rs.getInt("employeeId"));
                user.setRoleId(rs.getInt("roleId"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNote(rs.getString("note"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Method to retrieve a user by ID
    @Override
    public Users getUserById(int id) {
        Users user = null;
        String query = "SELECT * FROM Users WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new Users();
                user.setId(rs.getInt("id"));
                user.setEmployeeId(rs.getInt("employeeId"));
                user.setRoleId(rs.getInt("roleId"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNote(rs.getString("note"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Method to insert a new user
    @Override
    public boolean insertUser(Users user) {
        String query = "INSERT INTO Users (employeeId, roleId, username, password, note) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = new JDBCUtils().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, user.getEmployeeId());
            pstmt.setInt(2, user.getRoleId());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getNote());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to update an existing user
    @Override
    public boolean updateUser(Users user) {

        String query = "UPDATE Users SET employeeId = ?, roleId = ?, username = ?, password = ?, note = ? WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, user.getEmployeeId());
            pstmt.setInt(2, user.getRoleId());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getNote());
            pstmt.setInt(6, user.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete a user by ID
    @Override
    public boolean deleteUser(int id) {
        String query = "DELETE FROM Users WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
