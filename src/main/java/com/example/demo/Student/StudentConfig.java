package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository
    ){
        return args->{
            Student studentObj1 = new Student(
                    "Varun",
                    LocalDate.of(1998, Month.FEBRUARY,11),
                    "varun.vjr@gmail.com"
            );
            Student studentObj2 = new Student(
                    "raju",
                    LocalDate.of(1996, Month.FEBRUARY,11),
                    "raju.vegeta@gmail.com"
            );
            repository.saveAll(
                    List.of(studentObj1,studentObj2)
            );
        };
    }
}
