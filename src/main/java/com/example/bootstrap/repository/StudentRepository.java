package com.example.bootstrap.repository;

import com.example.bootstrap.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

    Boolean existsByPhoneNumber(String phoneNumber);
}
