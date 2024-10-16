package dao.impl;

import dao.ProductDAO;
import model.Manufacturer;
import model.Product;
import model.ProductType;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.*, pt.ProductTypeName as productTypeName, m.ManufacturerName as manufacturerName " +
                "FROM Product p " +
                "JOIN ProductType pt ON p.productTypeId = pt.id " +
                "JOIN Manufacturer m ON p.manufacturerId = m.id";

        try (Connection conn = new JDBCUtils().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductName(rs.getString("productName"));
                product.setStockQuantity(rs.getInt("stockQuantity"));
                product.setPurchasePrice(rs.getDouble("purchasePrice"));
                product.setSellingPrice(rs.getDouble("sellingPrice"));
                product.setNote(rs.getString("note"));
                product.setStatus(rs.getBoolean("status"));

                // Lấy thông tin ProductType
                ProductType productType = new ProductType();
                productType.setId(rs.getInt("productTypeId"));
                productType.setProductTypeName(rs.getString("productTypeName"));
                product.setProductType(productType);

                // Lấy thông tin Manufacturer
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setId(rs.getInt("manufacturerId"));
                manufacturer.setManufacturerName(rs.getString("manufacturerName"));
                product.setManufacturer(manufacturer);

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    public Product getProductById(int id) {
        Product product = null;
        String query = "SELECT p.*, pt.productTypeName as productTypeName, m.manufacturerName as manufacturerName " +
                "FROM Product p " +
                "JOIN ProductType pt ON p.productTypeId = pt.id " +
                "JOIN Manufacturer m ON p.manufacturerId = m.id " +
                "WHERE p.id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setProductName(rs.getString("productName"));
                    product.setStockQuantity(rs.getInt("stockQuantity"));
                    product.setPurchasePrice(rs.getDouble("purchasePrice"));
                    product.setSellingPrice(rs.getDouble("sellingPrice"));
                    product.setNote(rs.getString("note"));
                    product.setStatus(rs.getBoolean("status"));

                    // Lấy thông tin ProductType
                    ProductType productType = new ProductType();
                    productType.setId(rs.getInt("productTypeId"));
                    productType.setProductTypeName(rs.getString("productTypeName"));
                    product.setProductType(productType);

                    // Lấy thông tin Manufacturer
                    Manufacturer manufacturer = new Manufacturer();
                    manufacturer.setId(rs.getInt("manufacturerId"));
                    manufacturer.setManufacturerName(rs.getString("manufacturerName"));
                    product.setManufacturer(manufacturer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }


    public boolean insertProduct(Product product) {
        String query = "INSERT INTO Product (" +
                "productName, " +
                "stockQuantity, " +
                "purchasePrice, " +
                "sellingPrice, " +
                "note, " +
                "status, " +
                "productTypeId, " +
                "manufacturerId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, product.getProductName());
            pstmt.setInt(2, product.getStockQuantity());
            pstmt.setDouble(3, product.getPurchasePrice());
            pstmt.setDouble(4, product.getSellingPrice());
            pstmt.setString(5, product.getNote());
            pstmt.setBoolean(6, product.isStatus());
            pstmt.setInt(7, product.getProductType().getId());  // Lấy productTypeId từ đối tượng ProductType
            pstmt.setInt(8, product.getManufacturer().getId()); // Lấy manufacturerId từ đối tượng Manufacturer

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateProduct(Product product) {
        String query = "UPDATE Product SET " +
                "productName = ?, " +
                "stockQuantity = ?, " +
                "purchasePrice = ?, " +
                "sellingPrice = ?, " +
                "note = ?, " +
                "status = ?, " +
                "productTypeId = ?, " +
                "manufacturerId = ? " +
                "WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, product.getProductName());
            pstmt.setInt(2, product.getStockQuantity());
            pstmt.setDouble(3, product.getPurchasePrice());
            pstmt.setDouble(4, product.getSellingPrice());
            pstmt.setString(5, product.getNote());
            pstmt.setBoolean(6, product.isStatus());
            pstmt.setInt(7, product.getProductType().getId());  // Lấy productTypeId
            pstmt.setInt(8, product.getManufacturer().getId()); // Lấy manufacturerId
            pstmt.setInt(9, product.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteProduct(int id) {
        String query = "DELETE FROM Product WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
