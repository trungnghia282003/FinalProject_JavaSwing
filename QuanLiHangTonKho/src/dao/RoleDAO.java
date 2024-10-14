/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Position;
import model.Role;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface RoleDAO {

    List<Role> getAllRoles();

    Role getRoleById(int id);
}
