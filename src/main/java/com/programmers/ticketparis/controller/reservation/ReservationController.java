package com.programmers.ticketparis.controller.reservation;

import com.programmers.ticketparis.dto.ApiResponse;
import com.programmers.ticketparis.dto.reservation.ReservationCreateRequest;
import com.programmers.ticketparis.dto.reservation.ReservationResponse;
import com.programmers.ticketparis.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void cancelReservationById(@PathVariable("reservationId") Long reservationId) {
        reservationService.cancelReservationById(reservationId);
    }

    @GetMapping("/{reservationId}")
    public ApiResponse<ReservationResponse> findReservationById(@PathVariable("reservationId") Long reservationId,
                                                                HttpServletRequest httpServletRequest) {
        return ApiResponse.<ReservationResponse>builder()
                .path(httpServletRequest.getRequestURI())
                .data(reservationService.findReservationById(reservationId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ReservationResponse>> findAllReservations(HttpServletRequest httpServletRequest) {
        return ApiResponse.<List<ReservationResponse>>builder()
                .path(httpServletRequest.getRequestURI())
                .data(reservationService.findAllReservations())
                .build();
    }
}
