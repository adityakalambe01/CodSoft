package com.studentcourseregistrationsystem.repository;

import com.studentcourseregistrationsystem.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByStatus(String status);

    List<Registration> findByStudentIdAndStatus(Long studentId, String status);

    List<Registration> findByCourseIdAndStatus(Long courseId, String status);

    @Query("select n from Registration n order by n.status desc ")
    List<Registration> findAllDesc();

    List<Registration> findByStudentId(Long studentId);
}
