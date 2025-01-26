package com.example.demo.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudentList(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Optional<Student>deleteStudent = studentRepository.findById(id);
        if(deleteStudent.isEmpty()){
            throw new IllegalStateException("Student not found");
        }
        studentRepository.deleteById(id);
    }
    @Transactional
    public void updateStudent(Long studentId,String name,String email) {
        Student student = studentRepository
                 .findById(studentId)
                .orElseThrow(()->new IllegalStateException("student with id"+studentId+"does not exisit"));
        if(name!=null&&name.length()>0&&!Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email!=null&&email.length()>0&&!Objects.equals(student.getEmail(),email)){
            Optional<Student>studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(email);
        }

    }
}
