package com.employee.controller;
import com.employee.payload.EmployeeDto;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class employeesController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<?> addEmployee
            (@Valid @RequestBody EmployeeDto employeeDto,
            BindingResult result
            )
    {   if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
        EmployeeDto dto=employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(dto,org.springframework.http.HttpStatus.CREATED);

    }
    @PutMapping
    public ResponseEntity<?> updateEmployee(

            @Valid@RequestBody EmployeeDto employeeDto,
            @RequestParam Long id,
            BindingResult result
    ) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        EmployeeDto dto=employeeService.update(employeeDto,id);
        return new ResponseEntity<>(dto,org.springframework.http.HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployee(
        @RequestParam(name="pageSize",required = false,defaultValue="5") int pageSize,
        @RequestParam(name="pageNo",required = false,defaultValue="0") int pageNo,
        @RequestParam(name="sortBy",required = false,defaultValue="email") String sortBy,
        @RequestParam(name="sortDir",required = false,defaultValue="asc") String sortDir
    ) {

        List<EmployeeDto> dto=employeeService.getEmployee(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(dto,org.springframework.http.HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(
            @RequestParam Long id
    ) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee Deleted Successfully",HttpStatus.OK);

    }
    @GetMapping("/employeeId/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
            @PathVariable Long id
    ) {
        EmployeeDto dto=employeeService.getEmployeeById(id);
        return new ResponseEntity<>(dto,org.springframework.http.HttpStatus.OK);

    }



}
