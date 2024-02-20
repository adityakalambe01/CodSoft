package com.studentcourseregistrationsystem.service;

import com.studentcourseregistrationsystem.entity.Users;
import com.studentcourseregistrationsystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    /*
    *
    * Add New User
    *
    * */
    public boolean addNewUser(Users user){
        try {
            Users dbUser = usersRepository.findByUserEmailId(user.getUserEmailId());
            if (dbUser!=null) throw new Exception();
            usersRepository.save(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /*
    *
    * Update Existing User
    *
    * */
    public boolean updateExistingUser(Users updatedUser){
        try {
            Users dbUser = usersRepository.findById(updatedUser.getUserid()).get();
            dbUser.setUserFullName(
                    updatedUser.getUserFullName()
            );
            dbUser.setUserEmailId(
                    updatedUser.getUserEmailId()
            );
            dbUser.setUserPassword(
                    updatedUser.getUserPassword()
            );
            dbUser.setUserRole(
                    updatedUser.getUserRole()
            );
            usersRepository.save(dbUser);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /*
    *
    * Delete Existing User
    *
    * */
    public boolean deleteExistingUser(Long userId){
        try {
            Users dbUser = usersRepository.findById(userId).get();
            usersRepository.delete(dbUser);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /*
    *
    * Login Users
    *
    * */
    public Boolean loginUser(String emailId, String password){
        try {
            Users dbUser = usersRepository.findByUserEmailId(emailId);
            if (dbUser==null) throw new Exception();
            else if (dbUser.getUserPassword().equals(password)) return true;
        }catch (Exception e){
            return null;
        }
        return false;
    }

    /*
    *
    * Get All Users
    *
    * */
    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    /*
    *
    * Get All Users by Category
    *
    * */

    public List<Users> getAllUsersByCategory(String userRole){
        return usersRepository.findByUserRole(userRole);
    }

    /*
    *
    * Search User
    *
    * */
    public List<Users> searchUserByName(String userName){
        return usersRepository.findByUserFullNameContaining(userName);
    }

}
