package com.studentcourseregistrationsystem.repository;

import com.studentcourseregistrationsystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseCode(String courseCode);

    List<Course> findByCourseTitle(String courseTitle);

    List<Course> findByCourseDescription(String courseDescription);

    List<Course> findByCourseCapacityBetween(Long minimumRange, Long maximumRange);

    List<Course> findByCourseTitleContaining(String courseTitle);
}
