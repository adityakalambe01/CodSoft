package com.studentcourseregistrationsystem.repository;

import com.studentcourseregistrationsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByStudentFullNameContaining(String studentName);

    List<Student> findByStudentGender(String gender);

    @Query("select s from Student s order by s.studentFullName asc")
    List<Student> findByStudentFullNameAsc();

    @Query("select s from Student s order by s.studentFullName")
    List<Student> findByStudentIdAsc();



    Student findByStudentEmailId(String emailId);
}
