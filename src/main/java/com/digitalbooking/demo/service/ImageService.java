package com.digitalbooking.demo.service;

import com.digitalbooking.demo.exceptions.BadRequestException;
import com.digitalbooking.demo.exceptions.ResourceNotFoundException;
import com.digitalbooking.demo.model.Image;
import com.digitalbooking.demo.model.dto.ImageDTO;
import com.digitalbooking.demo.repository.ImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    private ObjectMapper mapper;

    public ImageDTO addImage(ImageDTO image) throws BadRequestException {
        if(image == null)
            throw new BadRequestException("Didn't get a image to save");
        imageRepository.save(mapper.convertValue(image, Image.class));
        return image;
    }

    public List<ImageDTO> listCategories() {
        List<Image> images = imageRepository.findAll();
        List<ImageDTO> imagesDTOS = new ArrayList<>();
        for (Image c:
                images) {
            ImageDTO cdto = mapper.convertValue(c,ImageDTO.class);
            imagesDTOS.add(cdto);
        }
        return imagesDTOS;
    }

    public ImageDTO findImageById(Long id) throws BadRequestException, ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("Id can't be null");
        Optional<Image> image = imageRepository.findById(id);
        if(image.isEmpty())
            throw new ResourceNotFoundException("Can't find image with id: " + id);
        return mapper.convertValue(image,ImageDTO.class);
    }

    public String editImage(ImageDTO image) throws ResourceNotFoundException {
        Optional<List<Image>> cat = imageRepository.findByURL(image.getUrl());
        if(cat.isEmpty())
            throw new ResourceNotFoundException("Can't find any image named: " + image.getUrl());
        if(cat.get().size() > 1)
            throw new ResourceNotFoundException("There are too many images with that name");
        imageRepository.save(cat.get().get(0));
        return "Image: " + image.getUrl() + " has been updated.";
    }

    public String deleteImage(Long id) throws BadRequestException, ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("Id can't be null");
        if (imageRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("Can't find image with id: " + id);
        imageRepository.deleteById(id);
        return "Image with id: " + id + " has been updated.";
    }
}
