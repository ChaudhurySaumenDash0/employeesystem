package com.employee.repository;

import com.employee.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface employeesRepo extends JpaRepository<Employees, Long> {
}
