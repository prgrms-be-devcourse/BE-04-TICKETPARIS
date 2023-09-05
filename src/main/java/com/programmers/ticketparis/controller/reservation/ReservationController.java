package com.programmers.ticketparis.controller.reservation;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.dto.reservation.ReservationCancelRequest;
import com.programmers.ticketparis.dto.reservation.ReservationCreateRequest;
import com.programmers.ticketparis.service.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public void create(@Valid @RequestBody ReservationCreateRequest reservationCreateRequest) {
        reservationService.create(reservationCreateRequest);
    }

    @PatchMapping("/{reservationId}")
    public void cancel(@PathVariable("reservationId") Long reservationId, @Valid @RequestBody ReservationCancelRequest reservationCancelRequest) {
        reservationService.cancel(reservationId, reservationCancelRequest);
    }
}
