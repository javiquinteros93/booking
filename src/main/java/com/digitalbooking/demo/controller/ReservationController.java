package com.digitalbooking.demo.controller;


import com.digitalbooking.demo.exceptions.BadRequestException;
import com.digitalbooking.demo.exceptions.ResourceNotFoundException;
import com.digitalbooking.demo.model.dto.ReservationDTO;
import com.digitalbooking.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody ReservationDTO reservation) throws BadRequestException {
        return ResponseEntity.ok(reservationService.addReservation(reservation));
    }
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> reservationList(){
        return ResponseEntity.ok(reservationService.reservationList());}

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable(value = "id") Long id) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(reservationService.findReservationById(id));
    }
}
