package dao.impl;

import dao.EmployeeDAO;
import model.Employee;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    // Method to retrieve all Employee
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> EmployeeList = new ArrayList<>();
        String query = "SELECT * FROM Employee";

        try (Connection conn = new JDBCUtils().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Employee Employee = new Employee();
                Employee.setUserId(rs.getString("userId"));
                Employee.setEmployeeName(rs.getString("employeeName"));
                Employee.setDateOfBirth(rs.getDate("dateOfBirth"));
                Employee.setGender(rs.getBoolean("gender"));
                Employee.setHireDate(rs.getDate("hireDate"));
                Employee.setPositionId(rs.getInt("positionId"));
                Employee.setAddress(rs.getString("address"));
                Employee.setPhoneNumber(rs.getString("phoneNumber"));
                Employee.setNote(rs.getString("note"));
                EmployeeList.add(Employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EmployeeList;
    }

    // Method to retrieve a Employee by ID
    @Override
    public Employee getEmployeeById(String id) {
        Employee Employee = null;
        String query = "SELECT * FROM Employee WHERE userId = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Employee = new Employee();
                Employee.setUserId(rs.getString("userId"));
                Employee.setEmployeeName(rs.getString("employeeName"));
                Employee.setDateOfBirth(rs.getDate("dateOfBirth"));
                Employee.setGender(rs.getBoolean("gender"));
                Employee.setHireDate(rs.getDate("hireDate"));
                Employee.setPositionId(rs.getInt("positionId"));
                Employee.setAddress(rs.getString("address"));
                Employee.setPhoneNumber(rs.getString("phoneNumber"));
                Employee.setNote(rs.getString("note"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Employee;
    }

    // Method to update an existing Employee
    @Override
    public boolean updateEmployee(Employee employee) {

        String query = "UPDATE Employee SET " +
                "positionId = ?, " +
                "employeeName = ?, " +
                "address = ?, " +
                "phoneNumber = ?, " +
                "note = ?, " +
                "dateOfBirth = ?, " +
                "hireDate = ?, " +
                "gender = ? " +
                "WHERE userId = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, employee.getPositionId());
            pstmt.setString(2, employee.getEmployeeName());
            pstmt.setString(3, employee.getAddress());
            pstmt.setString(4, employee.getPhoneNumber());
            pstmt.setString(5, employee.getNote());
            pstmt.setDate(6, new java.sql.Date(employee.getDateOfBirth().getTime()));
            pstmt.setDate(7, new java.sql.Date(employee.getHireDate().getTime()));
            pstmt.setBoolean(8, employee.isGender());
            pstmt.setString(9, employee.getId());

            // Thực thi câu lệnh SQL
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete a Employee by ID
    @Override
    public boolean deleteEmployee(int id) {
        String query = "DELETE FROM Employee WHERE id = ?";

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
