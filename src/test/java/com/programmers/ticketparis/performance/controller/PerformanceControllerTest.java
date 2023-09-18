package com.programmers.ticketparis.performance.controller;

import com.programmers.ticketparis.performance.domain.Category;
import com.programmers.ticketparis.performance.dto.request.PerformanceCreateRequest;
import com.programmers.ticketparis.performance.dto.request.PerformanceUpdateRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PerformanceControllerTest {

    private static final int PAGE_NUM = 1;

    @LocalServerPort
    public int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    void createPeroformanceTest() {

        String title = "할루할루";
        String posterUrl = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF224446_230823_123946.jpg";
        LocalDate startDate = LocalDate.of(2023, 9, 14);
        LocalDate endDate = LocalDate.of(2023, 12, 12);
        String duration = "2시간";
        Integer ageRating = 15;
        Integer price = 25000;
        Category category = Category.MUSICAL;
        String description = "테스트 설명 변경";
        Long sellerId = 1L;
        Long hallId = 2L;
        PerformanceCreateRequest performanceCreateRequest = new PerformanceCreateRequest(title, posterUrl, startDate, endDate, duration, ageRating, price, category, description, sellerId, hallId);


        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body(performanceCreateRequest)
            .when()
            .post("/api/performances")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/performances"))
            .body("data.performanceId", notNullValue())
            .body("message", nullValue());
    }

    @Test
    @Order(2)
    void findPerformanceByIdTest() {
        Long performanceId = 6L;

        String findPerformanceByIdURI = "/api/performances/" + performanceId;

        given()
            .when()
            .get(findPerformanceByIdURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/performances/6"))
            .body("data.performanceId", equalTo(6))
            .body("data.title", notNullValue())
            .body("data.posterUrl", notNullValue())
            .body("data.startDate", notNullValue())
            .body("data.endDate", notNullValue())
            .body("data.duration", notNullValue())
            .body("data.ageRating", notNullValue())
            .body("data.price", notNullValue())
            .body("data.category", notNullValue())
            .body("data.description", notNullValue())
            .body("data.createdDateTime", notNullValue())
            .body("data.updatedDateTime", notNullValue())
            .body("data.sellerId", notNullValue())
            .body("data.hallId", notNullValue());
    }

    @Test
    @Order(3)
    void findPerformancesByPageTest() {
        int size = 6;

        String findPerformancesByPageURI = "/api/performances?pageNum=" + PAGE_NUM + "&size=" + size;

        given()
            .when()
            .get(findPerformancesByPageURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/performances"))
            .body("data.size()", is(size));
    }

    @Test
    @Order(4)
    void findReservationsByPerformanceIdWithPageTest() {
        int size = 2;

        String findReservationsByPerformanceIdWithPageURI = "/api/performances/1/reservations?pageNum=" + PAGE_NUM + "&size=" + size;


        given()
            .when()
            .get(findReservationsByPerformanceIdWithPageURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/performances/1/reservations"))
            .body("data.size()", equalTo(2));
    }

    @Test
    @Order(5)
    void updatePerformance() {
        Long performanceId = 6L;

        String title = "토스트토스트";
        String posterUrl = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF224446_230823_123946.jpg";
        LocalDate startDate = LocalDate.of(2023, 9, 30);
        LocalDate endDate = LocalDate.of(2023, 12, 12);
        String duration = "5시간";
        Integer ageRating = 18;
        Integer price = 3000;
        Category category = Category.MUSICAL;
        String description = "테스트 설명 변경";
        Long hallId = 2L;


        PerformanceUpdateRequest performanceUpdateRequest = new PerformanceUpdateRequest(title, posterUrl, startDate, endDate, duration, ageRating, price, category, description, hallId);

        given().
            contentType(ContentType.JSON).
            body(performanceUpdateRequest).
            when().
            patch("/api/performances/" + performanceId).
            then().
            statusCode(HttpStatus.OK.value());
    }

    @Test
    @Order(6)
    void deletePerformanceById() {
        Long performanceId = 6L;

        String deletePerformanceByIdURI = "/api/performances/" + performanceId;

        given()
            .when()
            .delete(deletePerformanceByIdURI)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
            .body(isEmptyOrNullString());
    }
}
