package com.studentcourseregistrationsystem.service;

import com.studentcourseregistrationsystem.entity.Registration;
import com.studentcourseregistrationsystem.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    RegistrationRepository registrationRepository;

    /*
    *
    * Add New Registration
    *
    * */
    public boolean addNewRegistration(Registration registration){
        registrationRepository.save(registration);
        return true;
    }

    /*
    *
    * Update Existing Registration
    *
    * */
    public boolean updateExistingRegistration(Registration browserRegistration){
        try{
            Registration dbRegistration = registrationRepository.findById(browserRegistration.getRegistrationId()).get();
            dbRegistration.setStudentId(
                    browserRegistration.getStudentId()
            );
            dbRegistration.setCourseId(
                    browserRegistration.getCourseId()
            );
            dbRegistration.setRegistrationDate(
                    browserRegistration.getRegistrationDate()
            );
            dbRegistration.setStatus(
                    browserRegistration.getStatus()
            );
            registrationRepository.save(dbRegistration);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    /*
    *
    * Delete Existing Registration
    *
    * */
    public boolean deleteExistingRegistration(Long registrationId){
        try {
            Registration dbRegistration = registrationRepository.findById(registrationId).get();
            registrationRepository.delete(dbRegistration);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /*
    *
    * Get All Registration
    *
    * */
    public List<Registration> getAllRegistration(){
        return registrationRepository.findAllDesc();
    }

    /*
    *
    * Get All Pending Status
    *
    * */
    public List<Registration> getAllPendingStatus(){
        return registrationRepository.findByStatus("Pending");
    }

    /*
    *
    * Get All Approved Status
    *
    * */
    public List<Registration> getAllApprovedStatus(){
        return registrationRepository.findByStatus("Approved");
    }

    /*
    *
    * Get All Registration Status by StudentId
    *
    * */
    public List<Registration> getAllStatusByStudentIdAndStatus(Long studentId, String status){
        return registrationRepository.findByStudentIdAndStatus(studentId, status);
    }

    /*
    *
    * Get All Registration Status by CourseId
    *
    * */
    public List<Registration> getAllStatusByCourseIdAndStatus(Long courseId, String status){
        return registrationRepository.findByCourseIdAndStatus(courseId, status);
    }

    /*
    *
    * Get Registration by ID
    *
    * */
    public Registration getRegistrationById(Long registrationId){
        Registration registration;
        try {
            registration = registrationRepository.findById(registrationId).get();
        }catch (Exception e){
            registration = null;
        }
        return registration;
    }

    /*
    *
    * Get Registrations by ID
    *
    * */
    public List<Registration> getAllRegistrationByStudentID(Long studentId){
        return registrationRepository.findByStudentId(studentId);
    }
}
