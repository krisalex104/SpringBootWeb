package com.example.springtutorial.springbootweb.repositories;

import com.example.springtutorial.springbootweb.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends JpaRepository<EmployeeEntity,Long> {

}
