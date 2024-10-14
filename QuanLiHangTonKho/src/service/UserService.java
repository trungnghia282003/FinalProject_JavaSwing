/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Employee;
import model.User;

import java.util.List;

/**
 * @author ASUS
 */
public interface UserService {

    List<User> getAllUsers();

    User getUserById(String id);

    boolean insertUserAndEmployee(User user, Employee employee);

    boolean updateUser(User user);

    boolean deleteUser(String id);

    boolean existsById(String id);

    User authenticate(String username, String password);
}
