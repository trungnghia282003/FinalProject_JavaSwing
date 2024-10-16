package dao.impl;

import dao.InvoiceDAO;
import model.Customer;
import model.Employee;
import model.Invoice;
import model.Product;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImpl implements InvoiceDAO {

    @Override
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoiceList = new ArrayList<>();
        String query = "SELECT i.*, c.name as customerName, e.name as employeeName, p.name as productName, p.sellingPrice " +
                "FROM Invoice i " +
                "JOIN Customer c ON i.customerId = c.id " +
                "JOIN Employee e ON i.employeeId = e.id " +
                "JOIN Product p ON i.productId = p.id";

        try (Connection conn = new JDBCUtils().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setInvoiceDate(rs.getDate("invoiceDate"));
                invoice.setQuantity(rs.getInt("quantity"));
                invoice.setTotalAmount(rs.getLong("totalAmount"));
                invoice.setNote(rs.getString("note"));

                // Set Customer
                Customer customer = new Customer();
                customer.setId(rs.getInt("customerId"));
                customer.setCustomerName(rs.getString("customerName"));
                invoice.setCustomer(customer);

                // Set Employee
                Employee employee = new Employee();
                employee.setUserId(rs.getString("userId"));
                employee.setEmployeeName(rs.getString("employeeName"));
                invoice.setEmployee(employee);

                // Set Product
                Product product = new Product();
                product.setId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setSellingPrice(rs.getLong("sellingPrice"));
                invoice.setProduct(product);

                invoiceList.add(invoice);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    @Override
    public boolean insertInvoice(Invoice invoice) {
        Connection conn = null;
        PreparedStatement pstmtInvoice = null;
        PreparedStatement pstmtUpdateProduct = null;

        try {
            conn = new JDBCUtils().getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Tính TotalAmount cho hóa đơn
            double totalAmount = invoice.getQuantity() * invoice.getProduct().getSellingPrice();
            invoice.setTotalAmount(totalAmount);

            // Thêm dữ liệu hóa đơn vào bảng Invoice
            String insertInvoiceQuery = "INSERT INTO Invoice (" +
                    "customerId, " +
                    "employeeId, " +
                    "productId, " +
                    "quantity, " +
                    "totalAmount, " +
                    "invoiceDate, " +
                    "note) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmtInvoice = conn.prepareStatement(insertInvoiceQuery);
            pstmtInvoice.setInt(1, invoice.getCustomer().getId());
            pstmtInvoice.setString(2, invoice.getEmployee().getUserId());
            pstmtInvoice.setInt(3, invoice.getProduct().getId());
            pstmtInvoice.setInt(4, invoice.getQuantity());
            pstmtInvoice.setDouble(5, invoice.getTotalAmount());
            pstmtInvoice.setDate(6, new java.sql.Date(invoice.getInvoiceDate().getTime()));
            pstmtInvoice.setString(7, invoice.getNote());
            pstmtInvoice.executeUpdate();

            // Trừ số lượng sản phẩm trong kho
            String updateProductQuery = "UPDATE Product SET stockQuantity = stockQuantity - ? WHERE id = ?";
            pstmtUpdateProduct = conn.prepareStatement(updateProductQuery);
            pstmtUpdateProduct.setInt(1, invoice.getQuantity());
            pstmtUpdateProduct.setInt(2, invoice.getProduct().getId());
            pstmtUpdateProduct.executeUpdate();

            // Kiểm tra nếu số lượng sản phẩm trong kho < 0
            String checkStockQuery = "SELECT stockQuantity FROM Product WHERE id = ?";
            try (PreparedStatement pstmtCheckStock = conn.prepareStatement(checkStockQuery)) {
                pstmtCheckStock.setInt(1, invoice.getProduct().getId());
                ResultSet rs = pstmtCheckStock.executeQuery();
                if (rs.next()) {
                    int stock = rs.getInt("stockQuantity");
                    if (stock < 0) {
                        conn.rollback(); // Rollback nếu số lượng âm
                        return false;
                    }
                }
            }

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
            if (pstmtInvoice != null) try {
                pstmtInvoice.close();
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

    public Invoice getInvoiceById(int id) {
        String query = "SELECT i.*, c.name AS customerName, e.name AS employeeName, p.productName AS productName, p.sellingPrice AS sellingPrice " +
                "FROM Invoice i " +
                "JOIN Customer c ON i.customerId = c.id " +
                "JOIN Employee e ON i.employeeId = e.id " +
                "JOIN Product p ON i.productId = p.id " +
                "WHERE i.id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setQuantity(rs.getInt("quantity"));
                invoice.setTotalAmount(rs.getLong("totalAmount"));
                invoice.setInvoiceDate(rs.getDate("invoiceDate"));
                invoice.setNote(rs.getString("note"));

                // Set Customer
                Customer customer = new Customer();
                customer.setId(rs.getInt("customerId"));
                customer.setCustomerName(rs.getString("customerName"));
                invoice.setCustomer(customer);

                // Set Employee
                Employee employee = new Employee();
                employee.setUserId(rs.getString("employeeId"));
                employee.setEmployeeName(rs.getString("employeeName"));
                invoice.setEmployee(employee);

                // Set Product
                Product product = new Product();
                product.setId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setSellingPrice(rs.getDouble("sellingPrice"));
                invoice.setProduct(product);

                return invoice;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateInvoice(Invoice invoice) {
        String query = "UPDATE Invoice SET " +
                "customerId = ?, " +
                "employeeId = ?, " +
                "productId = ?, " +
                "quantity = ?, " +
                "totalAmount = ?, " +
                "invoiceDate = ?, " +
                "note = ? " +
                "WHERE id = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, invoice.getCustomer().getId());
            pstmt.setString(2, invoice.getEmployee().getUserId());
            pstmt.setInt(3, invoice.getProduct().getId());
            pstmt.setInt(4, invoice.getQuantity());
            pstmt.setDouble(5, invoice.getTotalAmount());
            pstmt.setDate(6, new java.sql.Date(invoice.getInvoiceDate().getTime()));
            pstmt.setString(7, invoice.getNote());
            pstmt.setInt(8, invoice.getId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteInvoice(int id) {
        String query = "DELETE FROM Invoice WHERE id = ?";

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
