package com.programmers.ticketparis.controller.reservation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.dto.ApiResponse;
import com.programmers.ticketparis.dto.reservation.request.ReservationCreateRequest;
import com.programmers.ticketparis.dto.reservation.response.ReservationResponse;
import com.programmers.ticketparis.service.reservation.ReservationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReservation(@Valid @RequestBody ReservationCreateRequest reservationCreateRequest) {
        reservationService.createReservation(reservationCreateRequest);
    }

    @PatchMapping("/{reservationId}")
    public void cancelReservationById(@PathVariable Long reservationId) {
        reservationService.cancelReservationById(reservationId);
    }

    @GetMapping("/{reservationId}")
    public ApiResponse<ReservationResponse> findReservationById(@PathVariable Long reservationId,
        HttpServletRequest httpServletRequest) {
        return ApiResponse.of(httpServletRequest.getRequestURI(),
            reservationService.findReservationById(reservationId));
    }

    @GetMapping
    public ApiResponse<List<ReservationResponse>> findAllReservations(HttpServletRequest httpServletRequest) {
        return ApiResponse.of(httpServletRequest.getRequestURI(), reservationService.findAllReservations());
    }
}
