package com.example.springtutorial.springbootweb.repositories;

import com.example.springtutorial.springbootweb.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
