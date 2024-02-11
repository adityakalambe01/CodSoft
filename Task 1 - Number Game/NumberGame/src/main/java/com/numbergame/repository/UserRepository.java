package com.numbergame.repository;

import com.numbergame.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByUsername(String username);
}
