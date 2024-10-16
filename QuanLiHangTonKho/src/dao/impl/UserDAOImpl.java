/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.UserDAO;
import model.Employee;
import model.Role;
import model.User;
import utils.JDBCUtils;
import utils.PasswordUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @author ASUS
 */
public class UserDAOImpl implements UserDAO {

    // Method to retrieve all users
    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT u.*, r.RoleName as roleName " +
                "FROM Users u " +
                "JOIN Role r ON u.roleId = r.id";

        try (Connection conn = new JDBCUtils().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNote(rs.getString("note"));

                Role role = new Role();
                role.setId(rs.getInt("roleId"));
                role.setRoleName(rs.getString("roleName"));
                user.setRole(role);

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Method to retrieve a user by ID
    @Override
    public User getUserById(String id) {
        User user = null;
        String query = "SELECT u.*, r.roleName as roleName" +
                "FROM Users u " +
                "JOIN Role r ON u.roleId = id" +
                "WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNote(rs.getString("note"));

                Role role = new Role();
                role.setId(rs.getInt("roleId"));
                role.setRoleName(rs.getString("roleName"));
                user.setRole(role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean insertUserAndEmployee(User user, Employee employee) {
        Connection conn = null;
        try {
            conn = new JDBCUtils().getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction
            UUID uuid = UUID.randomUUID();  // Tạo UUID

            // Thêm User trước
            String insertUserQuery = "INSERT INTO Users (" +
                    "id, " +
                    "roleId, " +
                    "username, " +
                    "password, " +
                    "note) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertUserQuery)) {

                pstmt.setString(1, String.valueOf(uuid));
                pstmt.setInt(2, user.getRole().getId());
                pstmt.setString(3, user.getUsername());
                pstmt.setString(4, PasswordUtils.hashPassword(user.getPassword()));
                pstmt.setString(5, user.getNote());
                pstmt.executeUpdate();
            }

            // Thêm Employee với UserId vừa tạo
            String insertEmployeeQuery = "INSERT INTO Employee (" +
                    "userId, " +
                    "positionId, " +
                    "employeeName, " +
                    "address, " +
                    "phoneNumber, " +
                    "note, " +
                    "dateOfBirth, " +
                    "hireDate, " +
                    "gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertEmployeeQuery)) {
                pstmt.setString(1, String.valueOf(uuid));  // Sử dụng UserId làm khóa chính
                pstmt.setInt(2, employee.getPosition().getId());
                pstmt.setString(3, employee.getEmployeeName());
                pstmt.setString(4, employee.getAddress());
                pstmt.setString(5, employee.getPhoneNumber());
                pstmt.setString(6, employee.getNote());
                pstmt.setDate(7, new java.sql.Date(employee.getDateOfBirth().getTime()));
                pstmt.setDate(8, new java.sql.Date(employee.getHireDate().getTime()));
                pstmt.setBoolean(9, employee.isGender());
                pstmt.executeUpdate();
            }

            conn.commit(); // Commit transaction nếu không lỗi
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // Method to update an existing user
    @Override
    public boolean updateUser(User user) {

        String query = "UPDATE Users SET employeeId = ?, roleId = ?, username = ?, password = ?, note = ? WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, user.getRole().getId());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, PasswordUtils.hashPassword(user.getPassword()));
            pstmt.setString(4, user.getNote());
            pstmt.setString(5, user.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete a user by ID
    @Override
    public boolean deleteUser(String id) {
        String query = "DELETE FROM Users WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Triển khai phương thức existsById
    @Override
    public boolean existsById(String id) {
        String query = "SELECT 1 FROM Users WHERE id = ?";
        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Nếu có bản ghi, trả về true

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existsByUsername(String username) {
        String query = "SELECT 1 FROM Users WHERE username = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                return resultSet.next(); // Trả về true nếu có dòng tồn tại
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
