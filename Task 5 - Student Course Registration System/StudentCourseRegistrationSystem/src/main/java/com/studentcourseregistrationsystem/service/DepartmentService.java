package com.studentcourseregistrationsystem.service;

import com.studentcourseregistrationsystem.entity.Department;
import com.studentcourseregistrationsystem.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    /*
    *
    * Add New Department
    *
    * */
    public boolean addNewDepartment(Department department){
        departmentRepository.save(department);
        return true;
    }

    /*
    *
    * Update Existing Department
    *
    * */
    public boolean updateExistingDepartment(Department browserDepartment){
        try {
            Department dbDepartment = departmentRepository.findById(browserDepartment.getDepartmentId()).get();
            dbDepartment.setDepartmentName(browserDepartment.getDepartmentName());
            dbDepartment.setDepartmentHeadFullName(browserDepartment.getDepartmentHeadFullName());
            departmentRepository.save(dbDepartment);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /*
    *
    * Delete Existing Department
    *
    * */
    public boolean deleteExistingDepartment(Long departmentId){
        try {
            Department dbDepartment = departmentRepository.findById(departmentId).get();
            departmentRepository.delete(dbDepartment);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /*
    *
    * Get All Departments
    *
    * */
    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }

    /*
    *
    * Get Departments by Name
    *
    * */
    public List<Department> getDepartmentByName(String departmentName){
        return departmentRepository.findByDepartmentNameContaining(departmentName);
    }

    /*
     *
     * Get Department by Id
     *
     * */
    public Department getDepartmentById(Long departmentId){
        Department department;
        try {
            department = departmentRepository.findById(departmentId).get();
        }catch (Exception e){
            department = null;
        }
        return department;
    }


}
