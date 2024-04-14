package com.digitalbooking.demo.service;


import com.digitalbooking.demo.exceptions.BadRequestException;
import com.digitalbooking.demo.exceptions.ResourceNotFoundException;
import com.digitalbooking.demo.model.Reservation;
import com.digitalbooking.demo.model.dto.ReservationDTO;
import com.digitalbooking.demo.repository.ReservationRepository;
import com.digitalbooking.demo.security.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ObjectMapper mapper;

    public ReservationDTO addReservation(ReservationDTO reservation) throws BadRequestException {
        if(reservation == null)
            throw new BadRequestException("Didn't get a reservation to save");
        System.out.println(reservation.getCity());
        userRepository.updateCity(reservation.getCity(), reservation.getUser());
        reservationRepository.save(mapper.convertValue(reservation, Reservation.class));
        return reservation;
    }

    public List<ReservationDTO> reservationList() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> reservationsDTOS = new ArrayList<>();
        for (Reservation c:
                reservations) {
            ReservationDTO cdto = mapper.convertValue(c,ReservationDTO.class);
            reservationsDTOS.add(cdto);
        }
        return reservationsDTOS;
    }

    public ReservationDTO findReservationById(Long id) throws BadRequestException, ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("Id can't be null");
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if(reservation.isEmpty())
            throw new ResourceNotFoundException("Can't find reservation with id: " + id);
        return mapper.convertValue(reservation,ReservationDTO.class);
    }
}
