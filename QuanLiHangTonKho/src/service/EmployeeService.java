/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;


import model.Employee;

import java.util.List;

/**
 * @author ASUS
 */
public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(String id);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(int id);
}
