package com.student.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.Student;

public interface studentRepositry extends JpaRepository<Student, Long>{

}
