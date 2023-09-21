package com.programmers.ticketparis.performance.controller;

import static com.programmers.ticketparis.common.util.SessionConst.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;

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
import com.programmers.ticketparis.performance.domain.Category;
import com.programmers.ticketparis.performance.dto.request.PerformanceCreateRequest;
import com.programmers.ticketparis.performance.dto.request.PerformanceUpdateRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PerformanceControllerTest {

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
    void createPeroformanceTest() {

        PerformanceCreateRequest performanceCreateRequest = PerformanceCreateRequest.builder()
            .title("할루할루")
            .posterUrl("http://www.kopis.or.kr/upload/pfmPoster/PF_PF224446_230823_123946.jpg")
            .startDate(LocalDate.of(2023, 9, 14))
            .endDate(LocalDate.of(2023, 12, 12))
            .duration("2시간")
            .ageRating(15)
            .price(125000)
            .category(Category.MUSICAL)
            .description("테스트 공연 입니다.")
            .sellerId(1L)
            .hallId(2L).build();

        given().log().all()
            .contentType(ContentType.JSON)
            .cookie(SESSION_COOKIE_NAME, sellerSessionId)
            .body(performanceCreateRequest)
            .when()
            .post("/api/performances")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/performances"))
            .body("data.performanceId", notNullValue())
            .body("message", nullValue())
            .log().all().extract();
    }

    @Test
    @Order(2)
    void findPerformanceByIdTest() {
        Long performanceId = 6L;

        String findPerformanceByIdURI = "/api/performances/" + performanceId;

        given().log().all()
            .cookie(SESSION_COOKIE_NAME, customerSessionId)
            .when()
            .get(findPerformanceByIdURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/performances/6"))
            .body("data.performanceId", equalTo(6))
            .body("data.title", equalTo("제14회 금호주니어콘서트, 강예서 플루트 독주회"))
            .body("data.posterUrl", equalTo("http://www.kopis.or.kr/upload/pfmPoster/PF_PF224644_230825_145427.jpg"))
            .body("data.startDate", equalTo("2023-08-24"))
            .body("data.endDate", equalTo("2023-08-24"))
            .body("data.duration", equalTo("1시간"))
            .body("data.ageRating", equalTo(7))
            .body("data.price", equalTo(10000))
            .body("data.category", equalTo("클래식/무용"))
            .body("data.description", equalTo("테스트 상세 설명6"))
            .body("data.createdDateTime", notNullValue())
            .body("data.updatedDateTime", notNullValue())
            .body("data.sellerId", equalTo(1))
            .body("data.hallId", equalTo(3))
            .log().all();
    }

    @Test
    @Order(3)
    void findPerformancesByPageTest() {
        int size = 6;

        String findPerformancesByPageURI = "/api/performances?pageNum=" + PAGE_NUM + "&size=" + size;

        given().log().all()
            .cookie(SESSION_COOKIE_NAME, customerSessionId)
            .when()
            .get(findPerformancesByPageURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/performances"))
            .body("data.size()", is(size))
            .log().all();
    }

    @Test
    @Order(4)
    void findReservationsByPerformanceIdWithPageTest() {
        int size = 2;

        String findReservationsByPerformanceIdWithPageURI =
            "/api/performances/1/reservations?pageNum=" + PAGE_NUM + "&size=" + size;

        given().log().all()
            .cookie(SESSION_COOKIE_NAME, sellerSessionId)
            .when()
            .get(findReservationsByPerformanceIdWithPageURI)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("localDateTime", notNullValue())
            .body("path", equalTo("/api/performances/1/reservations"))
            .body("data.size()", equalTo(2))
            .log().all();
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

        PerformanceUpdateRequest performanceUpdateRequest = new PerformanceUpdateRequest(title, posterUrl, startDate,
            endDate, duration, ageRating, price, category, description, hallId);

        given().log().all()
            .contentType(ContentType.JSON)
            .cookie(SESSION_COOKIE_NAME, sellerSessionId)
            .body(performanceUpdateRequest)
            .when()
            .patch("/api/performances/" + performanceId)
            .then()
            .statusCode(HttpStatus.OK.value())
            .log().all();
    }

    @Test
    @Order(6)
    void deletePerformanceById() {
        Long performanceId = 6L;

        String deletePerformanceByIdURI = "/api/performances/" + performanceId;

        given().log().all()
            .cookie(SESSION_COOKIE_NAME, sellerSessionId)
            .when()
            .delete(deletePerformanceByIdURI)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
            .body(isEmptyOrNullString())
            .log().all();
    }
}
