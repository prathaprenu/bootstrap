package com.example.bootstrap.service;

import com.example.bootstrap.exception.BadRequestException;
import com.example.bootstrap.model.Image;
import com.example.bootstrap.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public void deleteImageById(long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new BadRequestException("Image not found with ID: " + imageId));
        imageRepository.deleteById(image.getId());
    }

    public Image updateImageById(long imageId, MultipartFile file) throws IOException {
        Image updateImage = imageRepository.findById(imageId).orElseThrow(() -> new BadRequestException("Image not exist with id: " + imageId));

        updateImage.setImageName(file.getOriginalFilename());
        updateImage.setImageData(file.getBytes());

        return imageRepository.save(updateImage);
    }
}
