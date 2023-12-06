package com.example.bootstrap.repository;

import com.example.bootstrap.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
