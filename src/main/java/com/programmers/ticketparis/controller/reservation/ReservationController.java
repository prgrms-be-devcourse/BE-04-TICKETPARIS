package com.programmers.ticketparis.controller.reservation;

import com.programmers.ticketparis.dto.reservation.ReservationCreateRequest;
import com.programmers.ticketparis.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody ReservationCreateRequest reservationCreateRequest) {
        reservationService.create(reservationCreateRequest);
    }

    @PatchMapping("/{reservationId}")
    public void cancel(@PathVariable("reservationId") Long reservationId) {
        reservationService.cancel(reservationId);
    }
}
