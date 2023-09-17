package com.programmers.ticketparis.controller.reservation;

import com.programmers.ticketparis.dto.reservation.request.ReservationCreateRequest;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class ReservationControllerTest {

    @Test
    void createReservationTest() {

        String baseURI = "http://localhost:8080/api/reservations";
        ReservationCreateRequest reservationCreateRequest = ReservationCreateRequest.builder()
            .customerId(1L)
            .scheduleId(3L)
            .build();

        given()
            .contentType(ContentType.JSON)
            .body(reservationCreateRequest)
            .when()
            .post(baseURI)
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("localDateTime", notNullValue())
            .body("data.reservationId", notNullValue())
            .body("message", nullValue());
    }
}
