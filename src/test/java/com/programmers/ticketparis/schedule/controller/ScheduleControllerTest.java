package com.programmers.ticketparis.schedule.controller;

import static com.programmers.ticketparis.common.util.SessionConst.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;

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
import com.programmers.ticketparis.schedule.dto.request.ScheduleCreateRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScheduleControllerTest {

    private static final int PAGE_NUM = 1;
    private String customerSessionId;
    private String sellerSessionId;

    @LocalServerPort
    public int port;

    @BeforeAll
    @DisplayName("로그인 및 세션 ID 획득")
    void beforeAll() {
        RestAssured.port = port;

        Response customerResponse = given()
            .contentType(ContentType.JSON)
            .body(LoginRequest.of("testCustomer1", "dldasf1211!"))
            .when()
            .post("/api/customers/login")
            .then()
            .extract().response();
        customerSessionId = customerResponse.cookie(SESSION_COOKIE_NAME);

        Response sellerResponse = given()
            .contentType(ContentType.JSON)
            .body(LoginRequest.of("testSeller1", "dldasf1211!"))
            .when()
            .post("/api/sellers/login")
            .then()
            .extract().response();
        sellerSessionId = sellerResponse.cookie(SESSION_COOKIE_NAME);
    }

    @Test
    @Order(1)
    void createScheduleTest() {
        LocalDateTime startDatetime = LocalDateTime.of(2023, 12, 12, 10, 0);
        Integer sequence = 1;
        Long performanceId = 1L;
        ScheduleCreateRequest scheduleCreateRequest = new ScheduleCreateRequest(startDatetime, sequence, performanceId);

        given().log().all()
            .contentType(ContentType.JSON)
            .cookie(SESSION_COOKIE_NAME, sellerSessionId)
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

        given().log().all()
            .cookie(SESSION_COOKIE_NAME, customerSessionId)
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

        String findReservationsByScheduleIdByPageURI =
            "/api/schedules/1/reservations?pageNum=" + PAGE_NUM + "&size=" + size;

        given().log().all()
            .cookie(SESSION_COOKIE_NAME, sellerSessionId)
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

        given().log().all()
            .cookie(SESSION_COOKIE_NAME, sellerSessionId)
            .when()
            .delete(deleteScheduleByIdURI)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
            .body(isEmptyOrNullString())
            .log().all();
    }
}
