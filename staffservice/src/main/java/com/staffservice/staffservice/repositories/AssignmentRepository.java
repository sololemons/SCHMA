package com.staffservice.staffservice.repositories;

import com.staffservice.staffservice.entities.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignments, Long> {
    List<Assignments> findByClasses_ClassName(String className);
}
