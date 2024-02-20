package com.studentcourseregistrationsystem.repository;

import com.studentcourseregistrationsystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByDepartmentName(String departmentName);

    List<Department> findByDepartmentNameContaining(String departmentName);
}
