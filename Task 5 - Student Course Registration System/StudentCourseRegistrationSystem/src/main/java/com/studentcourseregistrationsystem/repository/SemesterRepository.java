package com.studentcourseregistrationsystem.repository;

import com.studentcourseregistrationsystem.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    List<Semester> findBySemesterNameContaining(String semesterName);
}
