/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.Users;

/**
 *
 * @author ASUS
 */
public interface UsersDAO {

    List<Users> getAllUsers(); // Retrieve all users

    Users getUserById(int id); // Retrieve a single user by ID

    boolean insertUser(Users user); // Insert a new user

    boolean updateUser(Users user); // Update an existing user

    boolean deleteUser(int id); // Delete a user by ID
}
