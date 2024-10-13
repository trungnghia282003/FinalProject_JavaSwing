/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.UsersDAO;
import dao.impl.UsersDAOImpl;
import exception.ValidationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Users;
import service.UsersService;

/**
 *
 * @author ASUS
 */
public class UsersServiceImpl implements UsersService {

    private UsersDAO usersDAO;

    // Constructor: Initialize with UsersDAO implementation
    public UsersServiceImpl() {
        this.usersDAO = new UsersDAOImpl(); // Inject DAO
    }

    @Override
    public List<Users> getAllUsers() {
        return usersDAO.getAllUsers();
    }

    @Override
    public Users getUserById(int id) {
        return usersDAO.getUserById(id);
    }

    @Override
    public boolean insertUser(Users user) {
        try {
            // Validation logic before inserting the user
            validateUser(user);
            return usersDAO.insertUser(user);
        } catch (ValidationException ex) {
            Logger.getLogger(UsersServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateUser(Users user) {
        try {
            // Validation logic before updating the user
            validateUser(user);
            return usersDAO.updateUser(user);

        } catch (ValidationException ex) {
            Logger.getLogger(UsersServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return usersDAO.deleteUser(id);
    }

    // Phương thức validate tổng hợp
    private void validateUser(Users user) throws ValidationException {
        if (user.getEmployeeId() <= 0) {
            throw new ValidationException("Mã nhân viên phải là số dương.");
        }
        if (user.getRoleId() <= 0) {
            throw new ValidationException("Mã quyền phải là số dương.");
        }
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
