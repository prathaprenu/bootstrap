package com.example.bootstrap.repository;

import com.example.bootstrap.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long> {
    List<Marks> findByStudentId(long studentId);

    Optional<Marks> findById(long id);
}
