package com.digitalbooking.demo.controller;

import com.digitalbooking.demo.exceptions.BadRequestException;
import com.digitalbooking.demo.exceptions.ResourceNotFoundException;
import com.digitalbooking.demo.model.dto.CaracteristicDTO;
import com.digitalbooking.demo.service.CaracteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caracteristics")
public class CaracteristicController {
    @Autowired
    private CaracteristicService caracteristicService;

    @PostMapping
    public ResponseEntity<CaracteristicDTO> addCaracteristic(@RequestBody CaracteristicDTO caracteristic) throws BadRequestException {
        return ResponseEntity.ok(caracteristicService.addCaracteristic(caracteristic));
    }

    @GetMapping
    public ResponseEntity<List<CaracteristicDTO>> listCategories() {
        return ResponseEntity.ok(caracteristicService.listCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editCaracteristic(@RequestBody CaracteristicDTO caracteristic) throws ResourceNotFoundException {
        return ResponseEntity.ok(caracteristicService.editCaracteristic(caracteristic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCaracteristic(@PathVariable(value = "id") Long id) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(caracteristicService.deleteCaracteristic(id));
    }
}
