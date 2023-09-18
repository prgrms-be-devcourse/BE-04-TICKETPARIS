package com.programmers.ticketparis.reservation.controller;

import com.programmers.ticketparis.reservation.dto.request.ReservationCreateRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationControllerTest {

    @LocalServerPort
    public int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    void createReservationTest() {

        ReservationCreateRequest reservationCreateRequest = ReservationCreateRequest.builder()
            .customerId(1L)
            .scheduleId(3L)
            .build();

        given()
            .contentType(ContentType.JSON)
            .body(reservationCreateRequest)
            .when()
            .post("/api/reservations")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("localDateTime", notNullValue())
            .body("data.reservationId", notNullValue())
            .body("message", nullValue())
            .log().all();
    }

    @Test
    @Order(2)
    void findReservationByIdTest() {
        Long reservationId = 5L;
        String findReservationByIdURI = "/api/reservations/" + reservationId;


        given()
            .when()
            .get(findReservationByIdURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("data.reservationId", equalTo(5))
            .body("data.reservationStatus", equalTo("COMPLETED"))
            .body("data.customerId", equalTo(1))
            .body("data.scheduleId", equalTo(4))
            .body("message", nullValue())
            .log().all();
    }

    @Test
    @Order(3)
    void findReservationsByPageTest() {
        int pageNum = 1;
        int size = 10;
        String findReservationsByPageURI = "/api/reservations?pageNum=" + pageNum + "&size=" + size;


        given()
            .when()
            .get(findReservationsByPageURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("data.size()", is(size))
            .log().all();
    }

    @Test
    @Order(4)
    void cancelReservationByIdTest() {
        Long reservationId = 1L;
        String cancelReservationByIdURI = "/api/reservations/" + reservationId;

        Map<String, Object> ReservationRequestBody = new HashMap<>();
        ReservationRequestBody.put("reservationId", reservationId);

        given()
            .when()
            .patch(cancelReservationByIdURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("data.reservationId", equalTo(1))
            .body("message", nullValue())
            .log().all();
    }
}
