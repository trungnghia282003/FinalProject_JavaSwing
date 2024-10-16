/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import exception.ValidationException;
import model.Employee;
import model.Role;
import model.User;
import service.UserService;
import utils.JDBCUtils;
import utils.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ASUS
 */
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    // Constructor: Initialize with UsersDAO implementation
    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl(); // Inject DAO
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(String id) {
        return userDAO.getUserById(id);
    }

    @Override
    public boolean insertUserAndEmployee(User user, Employee employee) {
        try {
            // Validation logic before inserting the user
            validateUser(user); // Gọi phương thức validateUser để kiểm tra dữ liệu đầu vào

            // Kiểm tra nếu username đã tồn tại
            if (userDAO.existsByUsername(user.getUsername())) {
                System.out.println("Username already exists.");
                return false; // Không thêm nếu username đã tồn tại
            }

            // Nếu username chưa tồn tại, tiến hành thêm user vào database
            return userDAO.insertUserAndEmployee(user, employee);

        } catch (ValidationException ex) {
            // Ghi log và xử lý ngoại lệ Validation
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            // Bắt và xử lý bất kỳ ngoại lệ nào khác
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, "Error inserting user", e);
        }
        return false;
    }


    @Override
    public boolean updateUser(User user) {
        try {
            // Validation logic before updating the user
            validateUser(user);
            return userDAO.updateUser(user);

        } catch (ValidationException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        return userDAO.deleteUser(id);
    }

    @Override
    public boolean existsById(String id) {
        return userDAO.existsById(id);
    }

    @Override
    public User authenticate(String username, String password) {
        String query = "SELECT u.*, r.roleName as roleName " +
                "FROM user u " +
                "INNER JOIN role r ON u.roleId = r.id " +
                "WHERE username = ?";

        try (Connection conn = new JDBCUtils().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");

                    // So sánh mật khẩu đã băm
                    if (PasswordUtils.verifyPassword(password, storedPassword)) {
                        // Mật khẩu hợp lệ, tạo đối tượng Users từ dữ liệu lấy từ CSDL
                        User user = new User();
                        user.setId(resultSet.getString("id"));
                        user.setUsername(resultSet.getString("username"));
                        user.setPassword(storedPassword); // Có thể không cần trả về mật khẩu
                        user.setNote(resultSet.getString("note"));

                        Role role = new Role();
                        role.setId(resultSet.getInt("roleId"));
                        role.setRoleName(resultSet.getString("roleName"));
                        user.setRole(role);
                        return user;  // Trả về đối tượng Users nếu xác thực thành công
                    } else {
                        // Mật khẩu không hợp lệ
                        return null;
                    }
                } else {
                    // Không tìm thấy người dùng với tên đăng nhập
                    return null;
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi xác thực người dùng: " + e.getMessage());
            return null;
        }
    }


    // Phương thức validate tổng hợp
    private void validateUser(User user) throws ValidationException {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new ValidationException("Tài khoản không được để trống.");
        }
        if (user.getUsername().length() > 20) {
            throw new ValidationException("Tài khoản không được dài hơn 20 ký tự.");
        }
        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new ValidationException("Mật khẩu phải có ít nhất 8 ký tự.");
        }
    }
}
