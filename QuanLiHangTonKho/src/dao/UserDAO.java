/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;

import model.Employee;
import model.User;

/**
 *
 * @author ASUS
 */
public interface UserDAO {

    List<User> getAllUsers(); // Retrieve all users

    User getUserById(String id); // Retrieve a single user by ID

    boolean insertUserAndEmployee(User user, Employee employee); // Insert a new user

    boolean updateUser(User user); // Update an existing user

    boolean deleteUser(String id); // Delete a user by ID

    boolean existsById(String id); // Check if a user exists by ID

    boolean existsByUsername(String username);

}
