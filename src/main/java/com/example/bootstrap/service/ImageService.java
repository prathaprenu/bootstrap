package com.example.bootstrap.service;

import com.example.bootstrap.model.Image;
import com.example.bootstrap.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void saveImage(String imageName, byte[] imageData) {
        Image image = new Image();
        image.setImageName(imageName);
        image.setImageData(imageData);

        imageRepository.save(image);
    }

    public Optional<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }
}
