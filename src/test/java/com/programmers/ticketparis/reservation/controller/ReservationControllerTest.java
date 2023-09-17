package com.programmers.ticketparis.reservation.controller;

import com.programmers.ticketparis.reservation.dto.request.ReservationCreateRequest;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReservationControllerTest {

    static String baseURI = "http://localhost:8080/api/reservations";

    @Test
    void createReservationTest() {

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

    @Test
    void findReservationByIdTest() {
        Long reservationId = 429L;
        String findReservationByIdURI = baseURI + "/" + reservationId;


        given()
            .when()
            .get(findReservationByIdURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("data.reservationId", equalTo(429))
            .body("data.reservationStatus", equalTo("COMPLETED"))
            .body("data.customerId", equalTo(83))
            .body("data.scheduleId", equalTo(3562))
            .body("message", nullValue());
    }

    @Test
    void findReservationsByPageTest() {
        int pageNum = 1;
        int size = 10;
        String findReservationsByPageURI = baseURI + "?pageNum=" + pageNum + "&size=" + size;


        given()
            .when()
            .get(findReservationsByPageURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("data.size()", is(size));
    }

    @Test
    void cancelReservationByIdTest() {
        Long reservationId = 434L;
        String cancelReservationByIdURI = baseURI + "/" + reservationId;

        Map<String, Object> ReservationRequestBody = new HashMap<>();
        ReservationRequestBody.put("reservationId", reservationId);

        given()
            .when()
            .patch(cancelReservationByIdURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("data.reservationId", equalTo(434))
            .body("message", nullValue());
    }

}
