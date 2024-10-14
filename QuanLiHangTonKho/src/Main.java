/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import model.Role;
import service.RoleService;
import service.impl.RoleServiceImpl;

import java.util.List;

/**
 * @author ASUS
 */
public class Main {

    public static void main(String[] args) {
        RoleService roleService = new RoleServiceImpl();

        // Test getAllPosition
        List<Role> roleList = roleService.getAllRoles();
        if (roleList != null) {
            for (Role role : roleList) {
                System.out.println("ID: " + role.getId());
                System.out.println("Role Name: " + role.getRoleName());
                System.out.println("Note: " + role.getNote());
            }
        }

        // Test getUserById
        Role role = roleService.getRoleById(1);
        if (role != null) {
            System.out.println("ID: " + role.getId());
            System.out.println("Role Name: " + role.getRoleName());
            System.out.println("Note: " + role.getNote());
        }

    }
}
