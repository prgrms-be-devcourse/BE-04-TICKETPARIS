package com.programmers.ticketparis.reservation.controller;

import static com.programmers.ticketparis.common.util.SessionConst.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.programmers.ticketparis.auth.dto.LoginRequest;
import com.programmers.ticketparis.reservation.dto.request.ReservationCreateRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReservationControllerTest {

    @LocalServerPort
    public int port;

    private String customerSessionId;
    private String sellerSessionId;

    @BeforeAll
    @DisplayName("로그인 및 세션 ID 획득")
    void beforeAll() {
        RestAssured.port = port;

        Response customerResponse = given()
            .contentType(ContentType.JSON)
            .port(port)
            .body(LoginRequest.of("testCustomer1", "dldasf1211!"))
            .when()
            .post("/api/customers/login")
            .then()
            .extract().response();
        customerSessionId = customerResponse.cookie(SESSION_COOKIE_NAME);

        Response sellerResponse = given()
            .contentType(ContentType.JSON)
            .port(port)
            .body(LoginRequest.of("testSeller1", "dldasf1211!"))
            .when()
            .post("/api/sellers/login")
            .then()
            .extract().response();
        sellerSessionId = sellerResponse.cookie(SESSION_COOKIE_NAME);
    }

    @Test
    @Order(1)
    void createReservationTest() {
        ReservationCreateRequest reservationCreateRequest = ReservationCreateRequest.builder()
            .customerId(1L)
            .scheduleId(3L)
            .build();

        given().log().all()
            .contentType(ContentType.JSON)
            .cookie(SESSION_COOKIE_NAME, customerSessionId)
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

        given().log().all()
            .cookie(SESSION_COOKIE_NAME, customerSessionId)
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

        given().log().all()
            .cookie(SESSION_COOKIE_NAME, customerSessionId)
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

        given().log().all()
            .cookie(SESSION_COOKIE_NAME, customerSessionId)
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
