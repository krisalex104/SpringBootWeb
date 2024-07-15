package com.example.springtutorial.springbootweb.services;

import com.example.springtutorial.springbootweb.dtos.DepartmentDto;
import com.example.springtutorial.springbootweb.entities.DepartmentEntity;
import com.example.springtutorial.springbootweb.exception.ResourceNotFoundException;
import com.example.springtutorial.springbootweb.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;


    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public DepartmentDto createNewDepartment(DepartmentDto departmentDto) {
        DepartmentEntity departmentEntity = modelMapper.map(departmentDto, DepartmentEntity.class);
        DepartmentEntity department = departmentRepository.save(departmentEntity);
        return modelMapper.map(department, DepartmentDto.class);
    }

    public DepartmentDto getDepartmentById(Long departmentId) {
        Optional<DepartmentEntity> departmentEntityOptional = departmentRepository.findById(departmentId);

        isExistsByDepartmentId(departmentId);

        return modelMapper.map(departmentEntityOptional.get(), DepartmentDto.class);
    }

    public List<DepartmentDto> getAllDepartment() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities.stream().map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDto.class)).toList();
    }

    public DepartmentDto updateDepartmentById(Long departmentId, DepartmentDto departmentDto) {
        isExistsByDepartmentId(departmentId);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDto, DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity updatedDepartment = departmentRepository.save(departmentEntity);
        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

    public Boolean deleteDepartmentById(Long departmentId) {
        isExistsByDepartmentId(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    public DepartmentDto updatePartialDepartmentById(Long departmentId, Map<String, Object> updatedData) {
        isExistsByDepartmentId(departmentId);
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();
        updatedData.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(DepartmentEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, departmentEntity, value);
        });

        return modelMapper.map(departmentEntity, DepartmentDto.class);
    }

    public void isExistsByDepartmentId(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if (!exists) {
            throw new ResourceNotFoundException("Department not found with id :" + departmentId);
        }
    }
}
