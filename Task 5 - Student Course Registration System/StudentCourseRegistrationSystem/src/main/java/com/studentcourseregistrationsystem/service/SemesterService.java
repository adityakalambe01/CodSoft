package com.studentcourseregistrationsystem.service;

import com.studentcourseregistrationsystem.entity.Semester;
import com.studentcourseregistrationsystem.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {
    @Autowired
    SemesterRepository semesterRepository;

    /*
    *
    * Add New Semester
    *
    * */
    public boolean addNewSemester(Semester semester){
        semesterRepository.save(semester);
        return true;
    }

    /*
    *
    * Update Existing Semester
    *
    * */
    public boolean updateExistingSemester(Semester updatedSemester){
        try {
            Semester dbSemester = semesterRepository.findById(updatedSemester.getSemesterSerialNumber()).get();
            dbSemester.setSemester(
                    updatedSemester.getSemester()
            );
            dbSemester.setSemesterName(
                    updatedSemester.getSemesterName()
            );
            dbSemester.setSemesterStartDate(
                    updatedSemester.getSemesterStartDate()
            );
            dbSemester.setSemesterEndDate(
                    updatedSemester.getSemesterEndDate()
            );
            semesterRepository.save(updatedSemester);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    /*
    *
    * Delete Existing Semester
    *
    * */
    public boolean deleteExistingSemester(Long semesterSrNo){
        try {

            semesterRepository.delete(
                    semesterRepository.findById(semesterSrNo).get()
            );

        }catch (Exception e){
            return false;
        }

        return true;
    }

    /*
    *
    * Get All Semester
    *
    * */
    public List<Semester> getAllSemester(){
        return semesterRepository.findAll();
    }

    /*
    *
    * Get All Semester by Name
    *
    * */
    public List<Semester> getAllSemesterByName(String semesterName){
        return semesterRepository.findBySemesterNameContaining(semesterName);
    }
}
