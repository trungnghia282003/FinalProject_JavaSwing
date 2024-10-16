package dao.impl;

import dao.ManufacturerDAO;
import model.Manufacturer;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDAOImpl implements ManufacturerDAO {

    @Override
    public List<Manufacturer> getAllManufacturers() {
        List<Manufacturer> manufacturerList = new ArrayList<>();
        String query = "SELECT * FROM Manufacturer";

        try (Connection conn = new JDBCUtils().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setId(rs.getInt("id"));
                manufacturer.setManufacturerName(rs.getString("manufacturerName"));
                manufacturerList.add(manufacturer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturerList;
    }

    @Override
    public Manufacturer getManufacturerById(int id) {
        Manufacturer manufacturer = null;
        String query = "SELECT * FROM Manufacturer WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                manufacturer = new Manufacturer();
                manufacturer.setId(rs.getInt("id"));
                manufacturer.setManufacturerName(rs.getString("ManufacturerName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return manufacturer;
    }

    @Override
    public boolean insertManufacturer(Manufacturer manufacturer) {
        String query = "INSERT INTO Manufacturer (manufacturerName) VALUES (?)";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, manufacturer.getManufacturerName());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateManufacturer(Manufacturer manufacturer) {
        String query = "UPDATE Manufacturer SET manufacturerName = ? WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, manufacturer.getManufacturerName());
            pstmt.setInt(2, manufacturer.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteManufacturer(int id) {
        String query = "DELETE FROM Manufacturer WHERE id = ?";

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
