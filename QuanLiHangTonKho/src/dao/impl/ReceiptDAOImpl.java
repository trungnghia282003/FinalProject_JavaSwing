package dao.impl;

import dao.ReceiptDAO;
import model.Distributor;
import model.Employee;
import model.Product;
import model.Receipt;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceiptDAOImpl implements ReceiptDAO {

    @Override
    public List<Receipt> getAllReceipts() {
        List<Receipt> receiptList = new ArrayList<>();
        String query = "SELECT r.*, e.name as employeeName, d.name as distributorName, p.name as productName, p.purchasePrice " +
                "FROM Receipt r " +
                "JOIN Employee e ON r.employeeId = e.id " +
                "JOIN Distributor d ON r.distributorId = d.id " +
                "JOIN Product p ON r.productId = p.id";

        try (Connection conn = new JDBCUtils().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Receipt receipt = new Receipt();
                receipt.setId(rs.getInt("id"));
                receipt.setReceiptDate(rs.getDate("receiptDate"));
                receipt.setQuantity(rs.getInt("quantity"));
                receipt.setTotalAmount(rs.getLong("totalAmount"));
                receipt.setNote(rs.getString("note"));

                // Set Employee
                Employee employee = new Employee();
                employee.setUserId(rs.getString("employeeId"));
                employee.setEmployeeName(rs.getString("employeeName"));
                receipt.setEmployee(employee);

                // Set Distributor
                Distributor distributor = new Distributor();
                distributor.setId(rs.getInt("distributorId"));
                distributor.setDistributorName(rs.getString("distributorName"));
                receipt.setDistributor(distributor);

                // Set Product
                Product product = new Product();
                product.setId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPurchasePrice(rs.getLong("purchasePrice"));
                receipt.setProduct(product);

                receiptList.add(receipt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receiptList;
    }

    @Override
    public boolean insertReceipt(Receipt receipt) {
        Connection conn = null;
        PreparedStatement pstmtReceipt = null;
        PreparedStatement pstmtUpdateProduct = null;

        try {
            conn = new JDBCUtils().getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Tính TotalAmount cho hóa đơn nhập
            double totalAmount = receipt.getQuantity() * receipt.getProduct().getPurchasePrice();
            receipt.setTotalAmount(totalAmount);

            // Thêm dữ liệu hóa đơn nhập vào bảng Receipt
            String insertReceiptQuery = "INSERT INTO Receipt (" +
                    "employeeId, " +
                    "distributorId, " +
                    "productId, " +
                    "receiptDate, " +
                    "quantity, " +
                    "totalAmount, " +
                    "note) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmtReceipt = conn.prepareStatement(insertReceiptQuery);
            pstmtReceipt.setString(1, receipt.getEmployee().getUserId());
            pstmtReceipt.setInt(2, receipt.getDistributor().getId());
            pstmtReceipt.setInt(3, receipt.getProduct().getId());
            pstmtReceipt.setDate(4, new java.sql.Date(receipt.getReceiptDate().getTime()));
            pstmtReceipt.setInt(5, receipt.getQuantity());
            pstmtReceipt.setDouble(6, receipt.getTotalAmount());
            pstmtReceipt.setString(7, receipt.getNote());
            pstmtReceipt.executeUpdate();

            // Cộng thêm số lượng sản phẩm trong kho
            String updateProductQuery = "UPDATE Product SET stockQuantity = stockQuantity + ? WHERE id = ?";
            pstmtUpdateProduct = conn.prepareStatement(updateProductQuery);
            pstmtUpdateProduct.setInt(1, receipt.getQuantity());
            pstmtUpdateProduct.setInt(2, receipt.getProduct().getId());
            pstmtUpdateProduct.executeUpdate();

            conn.commit(); // Commit transaction
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback nếu gặp lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;

        } finally {
            if (pstmtReceipt != null) try {
                pstmtReceipt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (pstmtUpdateProduct != null) try {
                pstmtUpdateProduct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Receipt getReceiptById(int id) {
        String query = "SELECT r.*, e.name AS employeeName, d.name AS distributorName, p.productName AS productName, p.purchasePrice AS purchasePrice " +
                "FROM Receipt r " +
                "JOIN Employee e ON r.employeeId = e.id " +
                "JOIN Distributor d ON r.distributorId = d.id " +
                "JOIN Product p ON r.productId = p.id " +
                "WHERE r.id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Receipt receipt = new Receipt();
                receipt.setId(rs.getInt("id"));
                receipt.setQuantity(rs.getInt("quantity"));
                receipt.setTotalAmount(rs.getLong("totalAmount"));
                receipt.setReceiptDate(rs.getDate("receiptDate"));
                receipt.setNote(rs.getString("note"));

                // Set Employee
                Employee employee = new Employee();
                employee.setUserId(rs.getString("employeeId"));
                employee.setEmployeeName(rs.getString("employeeName"));
                receipt.setEmployee(employee);

                // Set Distributor
                Distributor distributor = new Distributor();
                distributor.setId(rs.getInt("distributorId"));
                distributor.setDistributorName(rs.getString("distributorName"));
                receipt.setDistributor(distributor);

                // Set Product
                Product product = new Product();
                product.setId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPurchasePrice(rs.getDouble("purchasePrice"));
                receipt.setProduct(product);

                return receipt;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateReceipt(Receipt newReceipt, int oldProductId, int oldQuantity) {
        Connection conn = null;
        PreparedStatement pstmtUpdateReceipt = null;
        PreparedStatement pstmtUpdateOldProduct = null;
        PreparedStatement pstmtUpdateNewProduct = null;
        PreparedStatement pstmtUpdateSameProduct = null;

        try {
            conn = new JDBCUtils().getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Trường hợp Product Id thay đổi
            if (newReceipt.getProduct().getId() != oldProductId) {
                // 1. Trừ đi số lượng của sản phẩm cũ
                String updateOldProductQuery = "UPDATE Product SET stockQuantity = stockQuantity - ? WHERE id = ?";
                pstmtUpdateOldProduct = conn.prepareStatement(updateOldProductQuery);
                pstmtUpdateOldProduct.setInt(1, oldQuantity);
                pstmtUpdateOldProduct.setInt(2, oldProductId);
                pstmtUpdateOldProduct.executeUpdate();

                // 2. Trừ số lượng của sản phẩm mới
                String updateNewProductQuery = "UPDATE Product SET stockQuantity = stockQuantity - ? WHERE id = ?";
                pstmtUpdateNewProduct = conn.prepareStatement(updateNewProductQuery);
                pstmtUpdateNewProduct.setInt(1, newReceipt.getQuantity());
                pstmtUpdateNewProduct.setInt(2, newReceipt.getProduct().getId());
                pstmtUpdateNewProduct.executeUpdate();

            } else {
                // Trường hợp Product Id không thay đổi, chỉ thay đổi số lượng
                String updateSameProductQuery = "UPDATE Product SET stockQuantity = stockQuantity - ? + ? WHERE id = ?";
                pstmtUpdateSameProduct = conn.prepareStatement(updateSameProductQuery);
                pstmtUpdateSameProduct.setInt(1, oldQuantity); // Trừ số lượng cũ
                pstmtUpdateSameProduct.setInt(2, newReceipt.getQuantity()); // Cộng số lượng mới
                pstmtUpdateSameProduct.setInt(3, newReceipt.getProduct().getId());
                pstmtUpdateSameProduct.executeUpdate();
            }

            // Cập nhật lại tổng tiền
            double newTotalAmount = newReceipt.getQuantity() * newReceipt.getProduct().getPurchasePrice();
            newReceipt.setTotalAmount(newTotalAmount);

            // Cập nhật thông tin hóa đơn
            String updateReceiptQuery = "UPDATE Receipt SET " +
                    "employeeId = ?, " +
                    "distributorId = ?, " +
                    "productId = ?, " +
                    "receiptDate = ?, " +
                    "quantity = ?, " +
                    "totalAmount = ?, " +
                    "note = ? " +
                    "WHERE id = ?";
            pstmtUpdateReceipt = conn.prepareStatement(updateReceiptQuery);
            pstmtUpdateReceipt.setString(1, newReceipt.getEmployee().getUserId());
            pstmtUpdateReceipt.setInt(2, newReceipt.getDistributor().getId());
            pstmtUpdateReceipt.setInt(3, newReceipt.getProduct().getId());
            pstmtUpdateReceipt.setDate(4, new java.sql.Date(new Date().getTime())); // Cập nhật thời gian hiện tại
            pstmtUpdateReceipt.setInt(5, newReceipt.getQuantity());
            pstmtUpdateReceipt.setDouble(6, newTotalAmount);
            pstmtUpdateReceipt.setString(7, newReceipt.getNote());
            pstmtUpdateReceipt.setInt(8, newReceipt.getId());
            pstmtUpdateReceipt.executeUpdate();

            conn.commit(); // Commit transaction
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback nếu gặp lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;

        } finally {
            if (pstmtUpdateReceipt != null) try {
                pstmtUpdateReceipt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (pstmtUpdateOldProduct != null) try {
                pstmtUpdateOldProduct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (pstmtUpdateNewProduct != null) try {
                pstmtUpdateNewProduct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (pstmtUpdateSameProduct != null) try {
                pstmtUpdateSameProduct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean deleteReceipt(int id) {
        String query = "DELETE FROM Receipt WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
