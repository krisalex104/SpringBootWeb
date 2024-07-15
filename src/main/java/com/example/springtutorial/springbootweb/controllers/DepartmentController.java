package com.example.springtutorial.springbootweb.controllers;

import com.example.springtutorial.springbootweb.dtos.DepartmentDto;
import com.example.springtutorial.springbootweb.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        DepartmentDto newDepartment = departmentService.createNewDepartment(departmentDto);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        DepartmentDto department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartment() {
        List<DepartmentDto> allDepartment = departmentService.getAllDepartment();
        return ResponseEntity.ok(allDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@PathVariable Long id, @RequestBody @Valid DepartmentDto departmentDto) {
        DepartmentDto updatedDepartment = departmentService.updateDepartmentById(id, departmentDto);
        return ResponseEntity.ok(updatedDepartment);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentDto> updatePartialDepartmentById(@PathVariable Long id, @RequestBody Map<String, Object> updateData) {
        DepartmentDto updatedPartialDepartment = departmentService.updatePartialDepartmentById(id, updateData);
        return ResponseEntity.ok(updatedPartialDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long id) {
        Boolean deleted = departmentService.deleteDepartmentById(id);
        return ResponseEntity.ok(deleted);
    }
}
