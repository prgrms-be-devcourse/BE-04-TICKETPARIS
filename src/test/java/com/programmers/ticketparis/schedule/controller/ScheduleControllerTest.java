package com.programmers.ticketparis.schedule.controller;

import com.programmers.ticketparis.schedule.dto.request.ScheduleCreateRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScheduleControllerTest {

    private static final int PAGE_NUM = 1;

    @LocalServerPort
    public int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    void createScheduleTest() {
        LocalDateTime startDatetime = LocalDateTime.of(2023, 12, 12, 10, 0);
        Integer sequence = 1;
        Long performanceId = 1L;
        ScheduleCreateRequest scheduleCreateRequest = new ScheduleCreateRequest(startDatetime, sequence, performanceId);

        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body(scheduleCreateRequest)
            .when()
            .post("/api/schedules")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/schedules"))
            .body("data.scheduleId", notNullValue())
            .body("message", nullValue())
            .log().all();
    }

    @Test
    @Order(2)
    void findSchedulesByPageTest() {
        int size = 8;

        String findSchedulesByPageURI = "/api/schedules?pageNum=" + PAGE_NUM + "&size=" + size;

        given()
            .when()
            .get(findSchedulesByPageURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/schedules"))
            .body("data.size()", is(size))
            .log().all();
    }

    @Test
    @Order(3)
    void findReservationsByScheduleIdByPageTest() {
        int size = 2;

        String findReservationsByScheduleIdByPageURI = "/api/schedules/1/reservations?pageNum=" + PAGE_NUM + "&size=" + size;

        given()
            .when()
            .get(findReservationsByScheduleIdByPageURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/schedules/1/reservations"))
            .body("data.size()", equalTo(2))
            .log().all();
    }

    @Test
    @Order(4)
    void deleteScheduleByIdTest() {
        Long scheduleId = 7L;

        String deleteScheduleByIdURI = "/api/schedules/" + scheduleId;

        given()
            .when()
            .delete(deleteScheduleByIdURI)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
            .body(isEmptyOrNullString())
            .log().all();
    }
}
