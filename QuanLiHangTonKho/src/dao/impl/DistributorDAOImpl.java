package dao.impl;

import dao.DistributorDAO;
import model.Distributor;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DistributorDAOImpl implements DistributorDAO {

    @Override
    public List<Distributor> getAllDistributors() {
        List<Distributor> DistributorList = new ArrayList<>();
        String query = "SELECT * FROM Distributor";

        try (Connection conn = new JDBCUtils().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Distributor distributor = new Distributor();
                distributor.setId(rs.getInt("id"));
                distributor.setDistributorName(rs.getString("distributorName"));
                distributor.setAddress(rs.getString("address"));
                distributor.setPhoneNumber(rs.getString("phoneNumber"));
                distributor.setEmail(rs.getString("email"));
                distributor.setNote(rs.getString("note"));
                DistributorList.add(distributor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DistributorList;
    }

    @Override
    public Distributor getDistributorById(int id) {
        Distributor Distributor = null;
        String query = "SELECT * FROM Distributor WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Distributor distributor = new Distributor();
                distributor.setId(rs.getInt("id"));
                distributor.setDistributorName(rs.getString("distributorName"));
                distributor.setAddress(rs.getString("address"));
                distributor.setPhoneNumber(rs.getString("phoneNumber"));
                distributor.setEmail(rs.getString("email"));
                distributor.setNote(rs.getString("note"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Distributor;
    }

    @Override
    public boolean insertDistributor(Distributor distributor) {
        String query = "INSERT INTO Distributor (" +
                "distributorName, " +
                "address, " +
                "phoneNumber, " +
                "email, " +
                "note) VALUES (?, ?, ?, ? ,?)";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, distributor.getDistributorName());
            pstmt.setString(2, distributor.getAddress());
            pstmt.setString(3, distributor.getPhoneNumber());
            pstmt.setString(4, distributor.getEmail());
            pstmt.setString(5, distributor.getNote());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateDistributor(Distributor distributor) {
        String query = "UPDATE Distributor SET " +
                "distributorName = ?, " +
                "address= ?, " +
                "phoneNumber= ?, " +
                "email= ?, " +
                "note = ?, " +
                "WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, distributor.getDistributorName());
            pstmt.setString(2, distributor.getAddress());
            pstmt.setString(3, distributor.getPhoneNumber());
            pstmt.setString(4, distributor.getEmail());
            pstmt.setString(5, distributor.getNote());

            pstmt.setInt(6, distributor.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDistributor(int id) {
        String query = "DELETE FROM Distributor WHERE id = ?";

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
