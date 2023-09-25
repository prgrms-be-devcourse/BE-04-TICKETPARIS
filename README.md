# TICKETPARIS
![image](https://github.com/prgrms-be-devcourse/BE-04-TICKETPARIS/assets/49775540/6b76bea2-b65c-4391-b786-b753de760c8a)

>  프로그래머스 백엔드 데브코스 4기 2차 프로젝트(23.08.26 ~ 23.09.22) 타일러 X 한재원팀
> 
> [인터파크 티켓](https://tickets.interpark.com/) 서비스 클론코딩 진행



## 🎯 프로젝트 목표

> 핵심 키워드 **협업, 기본기**
> - 애자일 프로세스에 기반해 제대로 된 협업과 커뮤니케이션을 연습한다.
> - 기본적인 것에 초점을 맞춰 기본기를 다진다.



## 👨‍👨‍👦‍👦 타일러팀 소개

|                                  Scrum Master                                  |                                 Product Owner                                 |                                   Tech Lead                                   |                                   Developer                                   |
|:------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------:|:-----------------------------------------------------------------------------:|:-----------------------------------------------------------------------------:|
|                     [김영주](https://github.com/kylekim2123)                      |                     [황창현](https://github.com/Hchanghyeon)                     |                      [이현호](https://github.com/charlesuu)                      |                       [소재훈](https://github.com/jay-so)                        |
|  <img src="https://avatars.githubusercontent.com/u/49775540?v=4" width="150">  | <img src="https://avatars.githubusercontent.com/u/92444744?v=4" width="150">  | <img src="https://avatars.githubusercontent.com/u/76809524?v=4" width="150">  | <img src="https://avatars.githubusercontent.com/u/52352476?v=4" width="150">  |



## 📔 팀 문서

> 프로젝트에 대한 각종 정보는 아래 노션에서 확인 가능합니다.

[TICKETPARIS 프로젝트 노션](https://www.notion.so/backend-devcourse/2-46d92edebd7a406a99e1dcea32098bd8)



## 🏭 시스템 구성 및 아키텍처

### ERD

![image](https://github.com/prgrms-be-devcourse/BE-04-TICKETPARIS/assets/49775540/225e1170-7122-456b-8517-6e1778d39a3a)



### 기술 스택

![image](https://github.com/prgrms-be-devcourse/BE-04-TICKETPARIS/assets/49775540/ed3e7065-413a-4d84-9bdc-4c5029724c57)



### CI CD

![image](https://github.com/prgrms-be-devcourse/BE-04-TICKETPARIS/assets/49775540/464bff5a-11da-43bf-a351-f5010f350e87)



### 배포 환경

![image](https://github.com/prgrms-be-devcourse/BE-04-TICKETPARIS/assets/49775540/582969de-a1cc-4afa-8002-0408ec2cb4dd)



### 테스트 및 로컬 환경

![image](https://github.com/prgrms-be-devcourse/BE-04-TICKETPARIS/assets/49775540/0e8225b9-3703-48df-8082-e166de0b957f)



## 💾 패키지 구조

<details>
<summary>TICKETPARIS 패키지 구조</summary>
<div markdown="1">
```
BE-04-TICKETPARIS
├── Dockerfile
├── README.md
├── build.gradle
├── deploy
│   └── docker-compose.yml
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── programmers
    │   │           └── ticketparis
    │   │               ├── TicketparisApplication.java
    │   │               ├── auth
    │   │               │   ├── controller
    │   │               │   │   └── AuthController.java
    │   │               │   ├── dto
    │   │               │   │   ├── LoginRequest.java
    │   │               │   │   ├── LoginSuccessResponse.java
    │   │               │   │   ├── LogoutSuccessResponse.java
    │   │               │   │   └── SessionValueDto.java
    │   │               │   ├── exception
    │   │               │   │   └── AuthException.java
    │   │               │   ├── mvc
    │   │               │   │   └── filter
    │   │               │   │       ├── AuthenticationFilter.java
    │   │               │   │       └── ExceptionHandlerFilter.java
    │   │               │   ├── repository
    │   │               │   │   ├── SessionRepository.java
    │   │               │   │   └── localCashSessionRepository.java
    │   │               │   └── service
    │   │               │       └── AuthService.java
    │   │               ├── common
    │   │               │   ├── config
    │   │               │   │   ├── AuthConfig.java
    │   │               │   │   ├── RedisConfig.java
    │   │               │   │   └── SwaggerConfig.java
    │   │               │   ├── dto
    │   │               │   │   ├── ApiResponseType.java
    │   │               │   │   ├── ErrorData.java
    │   │               │   │   └── ResponseWrapper.java
    │   │               │   ├── exception
    │   │               │   │   ├── BusinessException.java
    │   │               │   │   ├── CommonException.java
    │   │               │   │   ├── ExceptionRule.java
    │   │               │   │   └── GlobalExceptionHandler.java
    │   │               │   ├── pageable
    │   │               │   │   └── Pageable.java
    │   │               │   └── util
    │   │               │       └── SessionConst.java
    │   │               ├── member
    │   │               │   ├── controller
    │   │               │   │   ├── CustomerController.java
    │   │               │   │   └── SellerController.java
    │   │               │   ├── domain
    │   │               │   │   ├── Customer.java
    │   │               │   │   └── Seller.java
    │   │               │   ├── dto
    │   │               │   │   ├── request
    │   │               │   │   │   ├── CustomerCreateRequest.java
    │   │               │   │   │   └── SellerCreateRequest.java
    │   │               │   │   ├── response
    │   │               │   │   │   ├── CustomerIdResponse.java
    │   │               │   │   │   ├── CustomerResponse.java
    │   │               │   │   │   ├── SellerIdResponse.java
    │   │               │   │   │   └── SellerResponse.java
    │   │               │   │   └── validator
    │   │               │   │       ├── EmailValid.java
    │   │               │   │       ├── EmailValidator.java
    │   │               │   │       ├── PasswordValid.java
    │   │               │   │       ├── PasswordValidator.java
    │   │               │   │       ├── PhoneValid.java
    │   │               │   │       ├── PhoneValidator.java
    │   │               │   │       ├── RegistrationNumberValid.java
    │   │               │   │       ├── RegistrationNumberValidator.java
    │   │               │   │       ├── UsernameValid.java
    │   │               │   │       └── UsernameValidator.java
    │   │               │   ├── enums
    │   │               │   │   └── MemberRule.java
    │   │               │   ├── exception
    │   │               │   │   ├── CustomerException.java
    │   │               │   │   └── SellerException.java
    │   │               │   ├── repository
    │   │               │   │   ├── CustomerRepository.java
    │   │               │   │   ├── SellerRepository.java
    │   │               │   │   ├── mapper
    │   │               │   │   │   ├── CustomerMapper.java
    │   │               │   │   │   └── SellerMapper.java
    │   │               │   │   └── mybatis
    │   │               │   │       ├── MybatisCustomerRepository.java
    │   │               │   │       └── MybatisSellerRepository.java
    │   │               │   └── service
    │   │               │       ├── CustomerService.java
    │   │               │       └── SellerService.java
    │   │               ├── performance
    │   │               │   ├── controller
    │   │               │   │   └── PerformanceController.java
    │   │               │   ├── domain
    │   │               │   │   ├── Category.java
    │   │               │   │   ├── Hall.java
    │   │               │   │   └── Performance.java
    │   │               │   ├── dto
    │   │               │   │   ├── request
    │   │               │   │   │   ├── PerformanceCreateRequest.java
    │   │               │   │   │   └── PerformanceUpdateRequest.java
    │   │               │   │   └── response
    │   │               │   │       ├── PerformanceIdResponse.java
    │   │               │   │       └── PerformanceResponse.java
    │   │               │   ├── exception
    │   │               │   │   └── PerformanceException.java
    │   │               │   ├── repository
    │   │               │   │   ├── CategoryEnumTypeHandler.java
    │   │               │   │   ├── PerformanceRepository.java
    │   │               │   │   ├── mapper
    │   │               │   │   │   └── PerformanceMapper.java
    │   │               │   │   └── mybatis
    │   │               │   │       └── MybatisPerformanceRepository.java
    │   │               │   └── service
    │   │               │       └── PerformanceService.java
    │   │               ├── ranking
    │   │               │   ├── controller
    │   │               │   │   └── RankingController.java
    │   │               │   ├── dto
    │   │               │   │   └── response
    │   │               │   │       └── RankingResponse.java
    │   │               │   ├── repository
    │   │               │   │   ├── RankingRepository.java
    │   │               │   │   ├── mapper
    │   │               │   │   │   └── RankingMapper.java
    │   │               │   │   └── mybatis
    │   │               │   │       └── MybatisRankingRepository.java
    │   │               │   └── service
    │   │               │       ├── RankingScheduler.java
    │   │               │       ├── RankingService.java
    │   │               │       └── RedisRankingService.java
    │   │               ├── reservation
    │   │               │   ├── controller
    │   │               │   │   └── ReservationController.java
    │   │               │   ├── domain
    │   │               │   │   ├── Reservation.java
    │   │               │   │   └── ReservationStatus.java
    │   │               │   ├── dto
    │   │               │   │   ├── request
    │   │               │   │   │   └── ReservationCreateRequest.java
    │   │               │   │   └── response
    │   │               │   │       ├── ReservationIdResponse.java
    │   │               │   │       └── ReservationResponse.java
    │   │               │   ├── exception
    │   │               │   │   └── ReservationException.java
    │   │               │   ├── repository
    │   │               │   │   ├── ReservationRepository.java
    │   │               │   │   ├── mapper
    │   │               │   │   │   └── ReservationMapper.java
    │   │               │   │   └── mybatis
    │   │               │   │       └── MybatisReservationRepository.java
    │   │               │   └── service
    │   │               │       ├── ReservationRedissonFacade.java
    │   │               │       └── ReservationService.java
    │   │               └── schedule
    │   │                   ├── controller
    │   │                   │   └── ScheduleController.java
    │   │                   ├── domain
    │   │                   │   └── Schedule.java
    │   │                   ├── dto
    │   │                   │   ├── request
    │   │                   │   │   └── ScheduleCreateRequest.java
    │   │                   │   └── response
    │   │                   │       ├── ScheduleIdResponse.java
    │   │                   │       └── ScheduleResponse.java
    │   │                   ├── exception
    │   │                   │   └── ScheduleException.java
    │   │                   ├── repository
    │   │                   │   ├── ScheduleRepository.java
    │   │                   │   ├── mapper
    │   │                   │   │   └── ScheduleMapper.java
    │   │                   │   └── mybatis
    │   │                   │       └── MybatisScheduleRepository.java
    │   │                   └── service
    │   │                       └── ScheduleService.java
    │   └── resources
    │       ├── application-dev.yml
    │       ├── application-local.yml
    │       ├── application.yml
    │       └── mapper
    │           ├── CustomerMapper.xml
    │           ├── PerformanceMapper.xml
    │           ├── RankingMapper.xml
    │           ├── ReservationMapper.xml
    │           ├── ScheduleMapper.xml
    │           └── SellerMapper.xml
    └── test
        ├── java
        │   └── com
        │       └── programmers
        │           └── ticketparis
        │               ├── member
        │               │   ├── domain
        │               │   │   └── CustomerTest.java
        │               │   ├── repository
        │               │   │   ├── MybatisCustomerRepositoryTest.java
        │               │   │   └── MybatisSellerRepositoryTest.java
        │               │   └── service
        │               │       ├── CustomerServiceTest.java
        │               │       └── SellerServiceTest.java
        │               ├── performance
        │               │   ├── controller
        │               │   │   └── PerformanceControllerTest.java
        │               │   ├── domain
        │               │   │   └── PerformanceTest.java
        │               │   ├── repository
        │               │   │   └── MybatisPerformanceRepositoryTest.java
        │               │   └── service
        │               │       └── PerformanceServiceTest.java
        │               ├── reservation
        │               │   ├── controller
        │               │   │   └── ReservationControllerTest.java
        │               │   ├── domain
        │               │   │   └── ReservationTest.java
        │               │   ├── repository
        │               │   │   └── MybatisReservationRepositoryTest.java
        │               │   └── service
        │               │       └── ReservationServiceTest.java
        │               └── schedule
        │                   ├── controller
        │                   │   └── ScheduleControllerTest.java
        │                   ├── repository
        │                   │   └── MybatisScheduleRepositoryTest.java
        │                   └── service
        │                       └── ScheduleServiceTest.java
        └── resources
            ├── application.yml
            ├── data.sql
            └── schema.sql
```
</div>
</details>