/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.EmployeeDAO;
import dao.impl.EmployeeDAOImpl;
import exception.ValidationException;
import model.Employee;
import service.EmployeeService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ASUS
 */
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    // Constructor: Initialize with EmployeeDAO implementation
    public EmployeeServiceImpl() {
        this.employeeDAO = new EmployeeDAOImpl(); // Inject DAO
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employeeDAO.getEmployeeById(id);
    }

    @Override
    public boolean updateEmployee(Employee Employee) {
        try {
            // Validation logic before updating the Employee
            validateEmployee(Employee);
            return employeeDAO.updateEmployee(Employee);

        } catch (ValidationException ex) {
            Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) {
        return employeeDAO.deleteEmployee(id);
    }

    // Phương thức validate tổng hợp
    private void validateEmployee(Employee Employee) throws ValidationException {
        if (Employee.getEmployeeName() == null || Employee.getEmployeeName().trim().isEmpty()) {
            throw new ValidationException("Tên nhân viên không được để trống.");
        }
    }
}
