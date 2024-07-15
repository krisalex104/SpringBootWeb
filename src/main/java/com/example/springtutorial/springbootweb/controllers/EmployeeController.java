package com.example.springtutorial.springbootweb.controllers;

import com.example.springtutorial.springbootweb.dtos.EmployeeDto;
import com.example.springtutorial.springbootweb.entities.EmployeeEntity;
import com.example.springtutorial.springbootweb.exception.ResourceNotFoundException;
import com.example.springtutorial.springbootweb.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/getSecretMessage")
    public String getMySecretMessage() {
        return "Secret Message is : You are going good with this course";
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        Optional<EmployeeDto> employeeDtoOptional = employeeService.getEmployeeById(id);
        return employeeDtoOptional
                .map(employeeDto -> ResponseEntity.ok(employeeDto))
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found with id :"+id));
    }

    @RequestMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestParam(required = false) String name, @RequestParam(required = false, name = "inputAge") Integer age, @RequestParam(required = false) String sortBy) {
        List<EmployeeDto> allEmployee = employeeService.getAllEmployee();
        return ResponseEntity.ok(allEmployee);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody @Valid EmployeeDto inputRequest) {
        EmployeeDto employeeDto = employeeService.saveEmployeeDetails(inputRequest);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable(name = "employeeId") Long id, @RequestBody @Valid EmployeeDto employeeDto) {
        EmployeeDto employeeDto1 = employeeService.updateEmployeeById(id, employeeDto);
        return ResponseEntity.ok(employeeDto1);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name = "employeeId") Long id) {
        boolean gotDeleted = employeeService.deleteEmployeeById(id);
        if (gotDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updatePartialEmployeeById(@RequestBody Map<String, Object> updatedData, @PathVariable Long employeeId) {
        EmployeeDto employeeDto = employeeService.updatePartialEmployeeById(updatedData, employeeId);
        return ResponseEntity.ok(employeeDto);
    }
}
