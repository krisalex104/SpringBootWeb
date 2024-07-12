package com.example.springtutorial.springbootweb.controllers;

import com.example.springtutorial.springbootweb.dtos.EmployeeDto;
import com.example.springtutorial.springbootweb.entities.EmployeeEntity;
import com.example.springtutorial.springbootweb.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/getSecretMessage")
    public String getMySecretMessage(){
        return "Secret Message is : You are going good with this course";
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return employeeService.getEmployeeById(id);
    }

    @RequestMapping
    public List<EmployeeDto> getAllEmployees(@RequestParam(required = false) String name, @RequestParam(required = false,name = "inputAge") Integer age , @RequestParam(required = false) String sortBy){
    return employeeService.getAllEmployee();
    }

    @PostMapping
    public EmployeeDto createNewEmployee(@RequestBody  EmployeeDto inputRequest){
        return employeeService.saveEmployeeDetails(inputRequest);
    }
}
