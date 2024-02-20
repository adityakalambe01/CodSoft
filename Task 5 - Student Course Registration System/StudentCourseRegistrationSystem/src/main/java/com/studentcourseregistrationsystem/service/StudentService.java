package com.studentcourseregistrationsystem.service;

import com.studentcourseregistrationsystem.entity.Student;
import com.studentcourseregistrationsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    /*
    *
    * Add New Student
    *
    * */
    public boolean addNewStudent(Student student){
        studentRepository.save(student);
        return true;
    }

    /*
    *
    * Update Existing Student
    *
    * */
    public boolean updateExistingStudent(Student updateStudent){
        try {
            Student dbStudent = studentRepository.findById(updateStudent.getStudentSerialNumber()).get();

            dbStudent.setStudentFullName(
                    updateStudent.getStudentFullName()
            );
            dbStudent.setStudentEmailId(
                    updateStudent.getStudentEmailId()
            );
            dbStudent.setStudentMobileNumber(
                    updateStudent.getStudentMobileNumber()
            );
            dbStudent.setStudentAddress(
                    updateStudent.getStudentAddress()
            );
            dbStudent.setStudentDateOfBirth(
                    updateStudent.getStudentDateOfBirth()
            );
            dbStudent.setStudentGender(
                    updateStudent.getStudentGender()
            );
            studentRepository.save(dbStudent);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    /*
    *
    * Delete Existing Student
    *
    * */
    public boolean deleteExistingStudent(Long studentSrNo){
        try {
            studentRepository.delete(
                    studentRepository.findById(studentSrNo).get()
            );
        }catch (Exception e){
            return false;
        }

        return true;
    }

    /*
    *
    * Get All Student
    *
    * */
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    /*
    *
    * Get All Student By Name
    *
    * */
    public List<Student> getAllStudentByName(String studentName){
        return studentRepository.findByStudentFullNameContaining(studentName);
    }

    /*
    *
    * Get All Student By Gender
    *
    * */
    public List<Student> getAllStudentByGender(String gender){
        return studentRepository.findByStudentGender(gender);
    }

    /*
    *
    * Get All Student by Ascending Order of Name
    *
    * */
    public List<Student> getAllStudentsNameAsc(){
        return studentRepository.findByStudentFullNameAsc();
    }

    /*
    *
    * Get All Student By Ascending Order of StudentId
    *
    * */
    public List<Student> getAllStudentByStudentIdAsc(){
        return studentRepository.findByStudentIdAsc();
    }

    /*
    *
    * Get Student by Id
    *
    * */
    public Student viewCurrentStudent(Long studentId){
        return studentRepository.findById(studentId).get();
    }

    /*
    *
    * Get Student by Email Id
    *
    * */
    public Student getStudentByEmailID(String emailId){
        return studentRepository.findByStudentEmailId(emailId);
    }
}
