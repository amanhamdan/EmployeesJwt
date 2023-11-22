package com.example.JWTproject.service;

import com.example.JWTproject.dto.Employee;
import com.example.JWTproject.entity.UserInfo;
import com.example.JWTproject.repository.EmployeeRepository;
import com.example.JWTproject.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

   List<Employee> employeeList = new ArrayList<>();

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void loadProductsFromDB() {
        Employee employee1 = new Employee();
        employee1.setName("John Doe");
        employee1.setRole("Software Engineer");
        employeeList.add(employee1);

        Employee employee2 = new Employee();
        employee2.setName("Jane Smith");
        employee2.setRole("Data Scientist");
        employeeList.add(employee2);

        // Save the list of employees
      employeeRepository.saveAll(employeeList);
    }


    public List<Employee> getEmployees() {
        return employeeList;
    }

    public Employee getEmployee(Long id) {
        for (Employee employee : employeeList) {
            Long employeeId = employee.getId();
            if (employeeId != null && employeeId.equals(id)) {
                return employee;
            }
        }
        return null;
    }


    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }
}
