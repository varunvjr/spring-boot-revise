package com.example.demo.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentServiceTest {
    @Autowired
    StudentService testStudentService;
    @Autowired
    StudentRepository testStudentRepository;
    @Test
    void studentDataExistInDB() {
        List<Student> studentList = testStudentService.getStudentList();
        assertTrue(studentList.size()>0);
    }

    @Test
    void addNewStudent() {
        Student newStudent = new Student(
                "kiran",
                LocalDate.of(1998, Month.FEBRUARY,11),
                "kiran.raj@gmail.com"
        );
        testStudentService.addNewStudent(newStudent);
        Optional<Student> student = testStudentRepository.findStudentByEmail("kiran.raj@gmail.com");
        Student s = student.get();
        assertEquals(s.getEmail(),"kiran.raj@gmail.com");

    }

    @Test
    void deleteStudent() {
        Student newStudent = new Student(
                "kiran",
                LocalDate.of(1998, Month.FEBRUARY,11),
                "kiran.raj@gmail.com"
        );
        testStudentService.addNewStudent(newStudent);
        Optional<Student> student = testStudentRepository.findStudentByEmail("kiran.raj@gmail.com");
        Student s = student.get();
        long id = s.getId();
        testStudentService.deleteStudent(id);
        student = testStudentRepository.findStudentByEmail("kiran.raj@gmail.com");
        assertTrue(student.isEmpty(),"Student has been deleted");
    }

    @Test
    void updateStudent() {
        Optional<Student> student = testStudentRepository.findStudentByEmail("varun.vjr@gmail.com");
        long id = student.get().getId();
        testStudentService.updateStudent(id,"vegeta",null);
        student = testStudentRepository.findStudentByEmail("varun.vjr@gmail.com");
        assertEquals(student.get().getName(),"vegeta");


    }
}