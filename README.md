# TICKETPARIS
![image](https://github.com/prgrms-be-devcourse/BE-04-TICKETPARIS/assets/49775540/6b76bea2-b65c-4391-b786-b753de760c8a)

>  프로그래머스 백엔드 데브코스 4기 2차 프로젝트(23.08.26 ~ 23.09.22) 타일러 X 한재원팀
> 
> [인터파크 티켓](https://tickets.interpark.com/) 서비스 클론코딩 진행

<br>

## 🎯 프로젝트 목표

> 핵심 키워드 **협업, 기본기**
> - 애자일 프로세스에 기반해 제대로 된 협업과 커뮤니케이션을 연습한다.
> - 기본적인 것에 초점을 맞춰 기본기를 다진다.

<br>

## 👨‍👨‍👦‍👦 타일러팀 소개

|                                  Scrum Master                                  |                                 Product Owner                                 |                                   Tech Lead                                   |                                   Developer                                   |
|:------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------:|:-----------------------------------------------------------------------------:|:-----------------------------------------------------------------------------:|
|                     [김영주](https://github.com/kylekim2123)                      |                     [황창현](https://github.com/Hchanghyeon)                     |                      [이현호](https://github.com/charlesuu)                      |                       [소재훈](https://github.com/jay-so)                        |
|  <img src="https://avatars.githubusercontent.com/u/49775540?v=4" width="150">  | <img src="https://avatars.githubusercontent.com/u/92444744?v=4" width="150">  | <img src="https://avatars.githubusercontent.com/u/76809524?v=4" width="150">  | <img src="https://avatars.githubusercontent.com/u/52352476?v=4" width="150">  |

<br>

## 📔 팀 문서

> 프로젝트에 대한 각종 정보는 아래 노션에서 확인 가능합니다.

[TICKETPARIS 프로젝트 노션](https://www.notion.so/backend-devcourse/2-46d92edebd7a406a99e1dcea32098bd8)

<br>

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

<br>

## 💾 패키지 구조
```
TICKETPARIS
├── deploy
├── gradle
│   └── wrapper
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── programmers
    │   │           └── ticketparis
    │   │               ├── auth
    │   │               │   ├── controller
    │   │               │   ├── dto
    │   │               │   ├── exception
    │   │               │   ├── mvc
    │   │               │   │   └── filter
    │   │               │   ├── repository
    │   │               │   └── service
    │   │               ├── common
    │   │               │   ├── config
    │   │               │   ├── dto
    │   │               │   ├── exception
    │   │               │   ├── pageable
    │   │               │   └── util
    │   │               ├── member
    │   │               │   ├── controller
    │   │               │   ├── domain
    │   │               │   ├── dto
    │   │               │   │   ├── request
    │   │               │   │   ├── response
    │   │               │   │   └── validator
    │   │               │   ├── enums
    │   │               │   ├── exception
    │   │               │   ├── repository
    │   │               │   │   ├── mapper
    │   │               │   │   └── mybatis
    │   │               │   └── service
    │   │               ├── performance
    │   │               │   ├── controller
    │   │               │   ├── domain
    │   │               │   ├── dto
    │   │               │   │   ├── request
    │   │               │   │   └── response
    │   │               │   ├── exception
    │   │               │   ├── repository
    │   │               │   │   ├── mapper
    │   │               │   │   └── mybatis
    │   │               │   └── service
    │   │               ├── ranking
    │   │               │   ├── controller
    │   │               │   ├── dto
    │   │               │   │   └── response
    │   │               │   ├── repository
    │   │               │   │   ├── mapper
    │   │               │   │   └── mybatis
    │   │               │   └── service
    │   │               ├── reservation
    │   │               │   ├── controller
    │   │               │   ├── domain
    │   │               │   ├── dto
    │   │               │   │   ├── request
    │   │               │   │   └── response
    │   │               │   ├── exception
    │   │               │   ├── repository
    │   │               │   │   ├── mapper
    │   │               │   │   └── mybatis
    │   │               │   └── service
    │   │               └── schedule
    │   │                   ├── controller
    │   │                   ├── domain
    │   │                   ├── dto
    │   │                   │   ├── request
    │   │                   │   └── response
    │   │                   ├── exception
    │   │                   ├── repository
    │   │                   │   ├── mapper
    │   │                   │   └── mybatis
    │   │                   └── service
    │   └── resources
    │       └── mapper
    └── test
        ├── java
        │   └── com
        │       └── programmers
        │           └── ticketparis
        │               ├── member
        │               │   ├── domain
        │               │   ├── repository
        │               │   └── service
        │               ├── performance
        │               │   ├── controller
        │               │   ├── domain
        │               │   ├── repository
        │               │   └── service
        │               ├── reservation
        │               │   ├── controller
        │               │   ├── domain
        │               │   ├── repository
        │               │   └── service
        │               └── schedule
        │                   ├── controller
        │                   ├── repository
        │                   └── service
        └── resources
```