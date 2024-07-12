package com.example.springtutorial.springbootweb.services;

import com.example.springtutorial.springbootweb.dtos.EmployeeDto;
import com.example.springtutorial.springbootweb.entities.EmployeeEntity;
import com.example.springtutorial.springbootweb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDto getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDto.class);
    }

    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        return employeeEntityList.stream().map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class)).toList();
    }

    public EmployeeDto saveEmployeeDetails(EmployeeDto employeeEntity) {
        EmployeeEntity toSaveEntity = modelMapper.map(employeeEntity,EmployeeEntity.class);
        EmployeeEntity employee = employeeRepository.save(toSaveEntity);
        return modelMapper.map(employee, EmployeeDto.class);

    }

}
