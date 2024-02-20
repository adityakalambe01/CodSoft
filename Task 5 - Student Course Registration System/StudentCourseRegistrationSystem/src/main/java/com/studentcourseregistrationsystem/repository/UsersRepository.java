package com.studentcourseregistrationsystem.repository;

import com.studentcourseregistrationsystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUserEmailId(String userEmailId);

    @Query("select u from Users u where u.userRole not like 'admin' order by u.userid asc")
    List<Users> findAll();

    @Query("select u from Users u where u.userRole like ?1 order by u.userid asc")
    List<Users> findByUserRole(String userRole);

    List<Users> findByUserFullNameContaining(String userName);
}
