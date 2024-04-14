package com.digitalbooking.demo.controller;

import com.digitalbooking.demo.exceptions.BadRequestException;
import com.digitalbooking.demo.exceptions.ResourceNotFoundException;
import com.digitalbooking.demo.model.dto.ImageDTO;
import com.digitalbooking.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")

public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageDTO> addImage(@RequestBody ImageDTO image) throws BadRequestException {
        return ResponseEntity.ok(imageService.addImage(image));
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> listCategories() {
        return ResponseEntity.ok(imageService.listCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editImage(@RequestBody ImageDTO image) throws ResourceNotFoundException {
        return ResponseEntity.ok(imageService.editImage(image));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable(value = "id") Long id) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(imageService.deleteImage(id));
    }
}
