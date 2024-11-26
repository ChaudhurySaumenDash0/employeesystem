package com.employee.service;

import com.employee.entity.Employees;
import com.employee.exception.ResourceNotFound;
import com.employee.payload.EmployeeDto;
import com.employee.repository.employeesRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private employeesRepo employeeRepository;
    @Autowired
    private ModelMapper mapper;

    EmployeeDto mapToDto(Employees employee) {
        EmployeeDto dto=mapper.map(employee, EmployeeDto.class);
      /* EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhone(employee.getPhone());*/
        return dto;
    }
    Employees mapToEntity(EmployeeDto employeeDto) {
        Employees emp=mapper.map(employeeDto, Employees.class);
       /* Employees employees = new Employees();
        employees.setId(employeeDto.getId());
        employees.setName(employeeDto.getName());
        employees.setEmail(employeeDto.getEmail());
        employees.setPhone(employeeDto.getPhone());*/
        return emp;
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employees employees = mapToEntity(employeeDto);
        Employees emp = employeeRepository.save(employees);
        return mapToDto(emp);
    }

    public EmployeeDto update(EmployeeDto employeeDto,Long id) {
        employeeDto.setId(id);
        Employees emp=mapToEntity(employeeDto);
        Employees e=employeeRepository.findById(emp.getId()).orElse(null);
        if(e!=null){
            e.setName(emp.getName());
            e.setEmail(emp.getEmail());
            e.setPhone(emp.getPhone());
            employeeRepository.save(e);
        }
        return mapToDto(e);
    }


    public List<EmployeeDto> getEmployee (int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page= PageRequest.of(pageNo, pageSize,sort);
        Page<Employees> all=employeeRepository.findAll(page);
        List<Employees> e = all.getContent();
        List<EmployeeDto> ee=e.stream().map(t->mapToDto(t)).collect(java.util.stream.Collectors.toList());
        return ee;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto getEmployeeById(Long id) {
     /*Optional<Employees> e=employeeRepository.findById(id);
     Employees ee=e.get();
     return mapToDto(ee);*/
        Employees e=employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("Record not found for id: "+id)
        );
     return mapToDto(e);
    }
}
