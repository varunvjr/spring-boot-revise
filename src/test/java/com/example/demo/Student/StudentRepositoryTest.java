package com.example.demo.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    @Test
    void checkIfEmailExist(){
        Optional<Student> student = studentRepository.findStudentByEmail("varun.vjr@gmail.com");
        assertTrue(student.isPresent(),"Email Id exist");

    }
}