package dao.impl;

import dao.RoleDAO;
import model.Role;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {

    @Override
    public List<Role> getAllRoles() {
        List<Role> roleList = new ArrayList<>();
        String query = "SELECT * FROM Role";

        try (Connection conn = new JDBCUtils().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleName(rs.getString("roleName"));
                role.setNote(rs.getString("note"));
                roleList.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }

    public Role getRoleById(int id) {
        Role role = null;
        String query = "SELECT * FROM Role WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleName(rs.getString("roleName"));
                role.setNote(rs.getString("note"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }
}
