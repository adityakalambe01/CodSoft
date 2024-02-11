package com.numbergame.service;

import com.numbergame.entity.UsersEntity;
import com.numbergame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UserRepository userRepository;



    public Long userLogin(String userName){
        UsersEntity user = userRepository.findByUsername(userName);
        if (user==null){
            return userRepository.save(new UsersEntity(userName)).getUserId();
        }

        return user.getUserId();
    }
}
