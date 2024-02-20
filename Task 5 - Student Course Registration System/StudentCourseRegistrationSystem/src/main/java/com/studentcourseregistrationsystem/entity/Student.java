package com.studentcourseregistrationsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentSerialNumber;

    private String studentFullName;

    private String studentEmailId;

    private Long studentMobileNumber;

    private String studentAddress;

    private LocalDate studentDateOfBirth;

    private String studentGender;
}
