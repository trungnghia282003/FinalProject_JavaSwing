/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import model.Employee;

import java.util.List;

/**
 * @author ASUS
 */
public interface EmployeeDAO {

    List<Employee> getAllEmployees(); // Retrieve all Employees

    Employee getEmployeeById(String id); // Retrieve a single Employee by ID

    boolean updateEmployee(Employee employee); // Update an existing Employee

    boolean deleteEmployee(int id); // Delete a Employee by ID

}

