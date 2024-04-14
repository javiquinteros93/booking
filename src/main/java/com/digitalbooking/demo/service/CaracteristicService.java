package com.digitalbooking.demo.service;

import com.digitalbooking.demo.exceptions.BadRequestException;
import com.digitalbooking.demo.exceptions.ResourceNotFoundException;
import com.digitalbooking.demo.model.Caracteristic;
import com.digitalbooking.demo.model.dto.CaracteristicDTO;
import com.digitalbooking.demo.repository.CaracteristicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CaracteristicService {
    @Autowired
    CaracteristicRepository caracteristicRepository;
    @Autowired
    private ObjectMapper mapper;

    public CaracteristicDTO addCaracteristic(CaracteristicDTO caracteristic) throws BadRequestException {
        if(caracteristic == null)
            throw new BadRequestException("Didn't get a caracteristic to save");
        caracteristicRepository.save(mapper.convertValue(caracteristic,Caracteristic.class));
        return caracteristic;
    }

    public List<CaracteristicDTO> listCategories() {
        List<Caracteristic> caracteristics = caracteristicRepository.findAll();
        List<CaracteristicDTO> caracteristicsDTOS = new ArrayList<>();
        for (Caracteristic c:
                caracteristics) {
            CaracteristicDTO cdto = mapper.convertValue(c,CaracteristicDTO.class);
            caracteristicsDTOS.add(cdto);
        }
        return caracteristicsDTOS;
    }

    public CaracteristicDTO findCaracteristicById(Long id) throws BadRequestException, ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("Id can't be null");
        Optional<Caracteristic> caracteristic = caracteristicRepository.findById(id);
        if(caracteristic.isEmpty())
            throw new ResourceNotFoundException("Can't find caracteristic with id: " + id);
        return mapper.convertValue(caracteristic,CaracteristicDTO.class);
    }

    public String editCaracteristic(CaracteristicDTO caracteristic) throws ResourceNotFoundException {
        Optional<List<Caracteristic>> cat = caracteristicRepository.findByCaracteristicName(caracteristic.getName());
        if(cat.isEmpty())
            throw new ResourceNotFoundException("Can't find any caracteristic named: " + caracteristic.getName());
        if(cat.get().size() > 1)
            throw new ResourceNotFoundException("There are too many caracteristics with that name");
        caracteristicRepository.save(cat.get().get(0));
        return "Caracteristic: " + caracteristic.getName() + " has been updated.";
    }

    public String deleteCaracteristic(Long id) throws BadRequestException, ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("Id can't be null");
        if (caracteristicRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("Can't find caracteristic with id: " + id);
        caracteristicRepository.deleteById(id);
        return "Caracteristic with id: " + id + " has been updated.";
    }
}
