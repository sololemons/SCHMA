package com.staffservice.staffservice.repositories;

import com.staffservice.staffservice.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

   List<Class>findByClassNameIn(List<String> className);

   List<Class> findByClassIdIn(List<String> classes);
}
