package dao.impl;

import dao.EmployeeDAO;
import model.Employee;
import model.Position;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    // Method to retrieve all Employee
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> EmployeeList = new ArrayList<>();
        String query = "SELECT e.*, p.positionName as positionName"
                + " FROM employee e "
                + " INNER JOIN position p ON e.positionId = p.id";

        try (Connection conn = new JDBCUtils().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setUserId(rs.getString("userId"));
                employee.setEmployeeName(rs.getString("employeeName"));
                employee.setDateOfBirth(rs.getDate("dateOfBirth"));
                employee.setGender(rs.getBoolean("gender"));
                employee.setHireDate(rs.getDate("hireDate"));
                employee.setAddress(rs.getString("address"));
                employee.setPhoneNumber(rs.getString("phoneNumber"));
                employee.setNote(rs.getString("note"));

                Position position = new Position();
                position.setId(rs.getInt("positionId"));
                position.setPositionName(rs.getString("positionName"));
                employee.setPosition(position);

                EmployeeList.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EmployeeList;
    }

    // Method to retrieve a Employee by ID
    @Override
    public Employee getEmployeeById(String id) {
        Employee employee = null;
        String query = "SELECT e.*, p.positionName as positionName"
                + " FROM employee e "
                + " INNER JOIN position p ON e.positionId = p.id";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                employee = new Employee();
                employee.setUserId(rs.getString("userId"));
                employee.setEmployeeName(rs.getString("employeeName"));
                employee.setDateOfBirth(rs.getDate("dateOfBirth"));
                employee.setGender(rs.getBoolean("gender"));
                employee.setHireDate(rs.getDate("hireDate"));
                employee.setAddress(rs.getString("address"));
                employee.setPhoneNumber(rs.getString("phoneNumber"));
                employee.setNote(rs.getString("note"));

                Position position = new Position();
                position.setId(rs.getInt("positionId"));
                position.setPositionName(rs.getString("positionName"));
                employee.setPosition(position);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
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

            pstmt.setString(2, employee.getEmployeeName());
            pstmt.setString(3, employee.getAddress());
            pstmt.setString(4, employee.getPhoneNumber());
            pstmt.setString(5, employee.getNote());
            pstmt.setDate(6, new java.sql.Date(employee.getDateOfBirth().getTime()));
            pstmt.setDate(7, new java.sql.Date(employee.getHireDate().getTime()));
            pstmt.setBoolean(8, employee.isGender());
            pstmt.setInt(9, employee.getPosition().getId());
            pstmt.setString(10, employee.getPosition().getPositionName());
            pstmt.setString(11, employee.getUserId());

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
