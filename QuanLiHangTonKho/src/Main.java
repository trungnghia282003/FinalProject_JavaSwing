
import java.util.List;
import model.Users;
import service.UsersService;
import service.impl.UsersServiceImpl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ASUS
 */
public class Main {

    public static void main(String[] args) {
        UsersService usersService = new UsersServiceImpl();

        // Test insertUser
        Users newUser = new Users();
        newUser.setEmployeeId(-1);
        newUser.setRoleId(1);
        newUser.setUsername("john_doe");
        newUser.setPassword("password123");
        newUser.setNote("New employee");

        try {
            if (usersService.insertUser(newUser)) {
                System.out.println("User inserted successfully.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }

//        // Test getAllUsers
//        List<Users> userList = usersService.getAllUsers();
//        if (userList != null) {
//            for (Users user : userList) {
//                System.out.println("ID: " + user.getId());
//                System.out.println("Username: " + user.getUsername());
//            }
//        }
//
//        // Test getUserById
//        Users user = usersService.getUserById(1);
//        if (user != null) {
//            System.out.println("User with ID 1: " + user.getUsername());
//        }
//
//        // Test updateUser
//        if (user != null) {
//            user.setPassword("newPassword123");
//            try {
//                if (usersService.updateUser(user)) {
//                    System.out.println("User updated successfully.");
//                }
//            } catch (IllegalArgumentException e) {
//                System.out.println("Validation error: " + e.getMessage());
//            }
//        }
//
//        // Test deleteUser
//        if (usersService.deleteUser(1)) {
//            System.out.println("User deleted successfully.");
//        }
    }
}
