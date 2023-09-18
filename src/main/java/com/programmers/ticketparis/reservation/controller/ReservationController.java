package com.programmers.ticketparis.reservation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.reservation.dto.request.ReservationCreateRequest;
import com.programmers.ticketparis.reservation.dto.response.ReservationIdResponse;
import com.programmers.ticketparis.reservation.dto.response.ReservationResponse;
import com.programmers.ticketparis.reservation.service.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationIdResponse createReservation(
        @Valid @RequestBody ReservationCreateRequest reservationCreateRequest) {
        return reservationService.createReservation(reservationCreateRequest);
    }

    @PatchMapping("/{reservationId}")
    public ReservationIdResponse cancelReservationById(@PathVariable Long reservationId) {
        return reservationService.cancelReservationById(reservationId);
    }

    @GetMapping("/{reservationId}")
    public ReservationResponse findReservationById(@PathVariable Long reservationId) {
        return reservationService.findReservationById(reservationId);
    }

    @GetMapping
    public List<ReservationResponse> findReservationsByPage(@RequestParam Integer pageNum, @RequestParam Integer size) {
        Pageable pageable = Pageable.builder()
            .pageNum(pageNum)
            .size(size)
            .build();

        return reservationService.findReservationsByPage(pageable);
    }
}