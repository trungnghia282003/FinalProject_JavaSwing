/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.Users;

/**
 *
 * @author ASUS
 */
public interface UsersService {

    List<Users> getAllUsers();

    Users getUserById(int id);

    boolean insertUser(Users user);

    boolean updateUser(Users user);

    boolean deleteUser(int id);
}
