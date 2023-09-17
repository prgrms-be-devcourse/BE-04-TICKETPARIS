package com.programmers.ticketparis.schedule.controller;

import com.programmers.ticketparis.schedule.dto.request.ScheduleCreateRequest;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ScheduleControllerTest {

    static String baseURI = "http://localhost:8080/api/schedules";

    @Test
    void createScheduleTest() {
        LocalDateTime startDatetime = LocalDateTime.of(2023, 12, 12, 10, 0);
        Integer sequence = 1;
        Long performanceId = 1L;
        ScheduleCreateRequest scheduleCreateRequest = new ScheduleCreateRequest(startDatetime, sequence, performanceId);

        given()
            .contentType(ContentType.JSON)
            .body(scheduleCreateRequest)
            .when()
            .post(baseURI)
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/schedules"))
            .body("data.scheduleId", notNullValue())
            .body("message", nullValue());
    }

    @Test
    void findSchedulesByPageTest() {
        int pageNum = 1;
        int size = 10;
        String findSchedulesByPageURI = baseURI + "?pageNum=" + pageNum + "&size=" + size;

        given()
            .when()
            .get(findSchedulesByPageURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/schedules"))
            .body("data.size()", is(size));
    }

    @Test
    void findReservationsByScheduleIdByPageTest() {
        int pageNum = 1;
        int size = 2;
        String findReservationsByScheduleIdByPageURI = baseURI + "?pageNum=" + pageNum + "&size=" + size;

        given()
            .when()
            .get(findReservationsByScheduleIdByPageURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/schedules"))
            .body("data.size()", equalTo(2));
    }

    @Test
    void deleteScheduleByIdTest() {
        Long scheduleId = 7L;

        String deleteScheduleByIdURI = baseURI + "/" + scheduleId;

        given()
            .when()
            .delete(deleteScheduleByIdURI)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
            .body(isEmptyOrNullString());
    }
}
