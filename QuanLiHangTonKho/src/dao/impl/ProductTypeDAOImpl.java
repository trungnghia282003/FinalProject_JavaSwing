package dao.impl;

import dao.ProductTypeDAO;
import dao.ProductTypeDAO;
import model.ProductType;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDAOImpl implements ProductTypeDAO {

    @Override
    public List<ProductType> getAllProductTypes() {
        List<ProductType> productTypeList = new ArrayList<>();
        String query = "SELECT * FROM ProductType";

        try (Connection conn = new JDBCUtils().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ProductType productType = new ProductType();
                productType.setId(rs.getInt("id"));
                productType.setProductTypeName(rs.getString("productTypeName"));
                productTypeList.add(productType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productTypeList;
    }

    @Override
    public ProductType getProductTypeById(int id) {
        ProductType productType = null;
        String query = "SELECT * FROM ProductType WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                productType = new ProductType();
                productType.setId(rs.getInt("id"));
                productType.setProductTypeName(rs.getString("productTypeName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productType;
    }

    @Override
    public boolean insertProductType(ProductType productType) {
        String query = "INSERT INTO ProductType (productTypeName) VALUES (?)";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, productType.getProductTypeName());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProductType(ProductType productType) {
        String query = "UPDATE ProductType SET productTypeName = ? WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, productType.getProductTypeName());
            pstmt.setInt(2, productType.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProductType(int id) {
        String query = "DELETE FROM ProductType WHERE id = ?";

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
