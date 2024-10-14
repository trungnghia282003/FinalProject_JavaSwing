package dao.impl;

import dao.PositionDAO;
import model.Position;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDAOImpl implements PositionDAO {

    @Override
    public List<Position> getAllPositions() {
        List<Position> positionList = new ArrayList<>();
        String query = "SELECT * FROM Position";

        try (Connection conn = new JDBCUtils().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Position position = new Position();
                position.setId(rs.getInt("id"));
                position.setPositionName(rs.getString("positionName"));
                position.setNote(rs.getString("note"));
                positionList.add(position);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positionList;
    }

    @Override
    public Position getPositionById(int id) {
        Position position = null;
        String query = "SELECT * FROM Position WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                position = new Position();
                position.setId(rs.getInt("id"));
                position.setPositionName(rs.getString("positionName"));
                position.setNote(rs.getString("note"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return position;
    }

    @Override
    public boolean insertPosition(Position position) {
        String query = "INSERT INTO Position (positionName, note) VALUES (?, ?)";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, position.getPositionName());
            pstmt.setString(2, position.getNote());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePosition(Position position) {
        String query = "UPDATE Position SET positionName = ?, note = ? WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, position.getPositionName());
            pstmt.setString(2, position.getNote());
            pstmt.setInt(3, position.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePosition(int id) {
        String query = "DELETE FROM Position WHERE id = ?";

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
