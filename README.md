# PINGPONG-SERVER
About DDD 9기 IOS 3팀 SERVER

## About Service
- 🥖 명언제과점
- 개인 취향별 맞춤 명언 추천 서비스

## Skill Stack
- 🍃 SpringBoot 3.0.3
- Java 17
- PostgreSQL
- JPA/QueryDsl
- Github Action(CI/CD)
- AWS EC2/RDS
- Docker
- Swagger(springdoc-openapi:2.0.4)

## 🌟 Team
|Server Developer|Designer|Designer|iOS Developer|iOS Developer|
|:---:|:---:|:---:|:---:|:---:|
| <a href="https://github.com/Hyesooo"><img height="130px" width="130px" src="https://github.com/DDD-Community/PINGPONG-IOS/assets/87685946/bc02ac38-c9fe-4122-bed8-e1fbfb588567"/></a>|<a href=""><img height="130px" width="130px" src="https://github.com/DDD-Community/PINGPONG-IOS/assets/87685946/41bc501c-a144-4886-95c8-2d9fcc5815f2"/></a>|<a href=""><img height="130px" width="130px" src="https://github.com/DDD-Community/PINGPONG-IOS/assets/87685946/f0bf90cf-5464-45db-9362-ec3f2fa3411b"/></a>|<a href="https://github.com/Byeonjinha"><img height="130" width="130px" src="https://github.com/DDD-Community/PINGPONG-IOS/assets/87685946/8874c20c-06d4-4ea2-b069-29a32bbd8e4b"/></a>|<a href="https://github.com/Roy-wonj"><img height="130" width="130px" src="https://github.com/DDD-Community/PINGPONG-IOS/assets/87685946/07d3fa91-c702-4204-b0e9-00b554870675"/></a>|
|<a href="https://github.com/Hyesooo">김혜수</a>|<a href="">남윤지</a>|<a href="">박주미</a>|<a href="https://github.com/Byeonjinha">변진하</a>|<a href="https://github.com/Roy-wonji">서원지</a>|

## Project Structure
```
├── java
│   └── com
│       └── pingpong 
│           └── quoteBakery
│               ├── QuoteBakeryApplication.java
│               ├── app // 도메인 관련
│               │   ├── controller
│               │   │   ├── HomeController.java
│               │   │   ├── MyPageController.java
│               │   │   ├── OnBoardController.java
│               │   │   └── SearchController.java
│               │   ├── domain
│               │   │   ├── Like.java
│               │   │   ├── Quote.java
│               │   │   └── UserPreference.java
│               │   ├── dto
│               │   │   ├── LikeDto.java
│               │   │   ├── QuoteDto.java
│               │   │   ├── QuoteMultiSearchDto.java
│               │   │   ├── QuoteSingleSearchDto.java
│               │   │   └── UserPrefDto.java
│               │   ├── enums
│               │   │   ├── QuoteFlavor.java
│               │   │   ├── QuoteMood.java
│               │   │   └── QuoteSource.java
│               │   ├── persistence
│               │   │   ├── LikeRepository.java
│               │   │   ├── QuoteRepository.java
│               │   │   ├── QuoteRepositoryCustom.java
│               │   │   ├── UserPreferenceRepository.java
│               │   │   └── implementation
│               │   │       └── QuoteRepositoryImpl.java
│               │   ├── resource
│               │   │   ├── LikeCreateResource.java
│               │   │   ├── LikeResource.java
│               │   │   ├── QuoteConverter.java
│               │   │   ├── QuoteResource.java
│               │   │   ├── QuoteSearchResource.java
│               │   │   ├── RandomQuoteSearchPageResource.java
│               │   │   ├── RandomQuoteSearchResource.java
│               │   │   ├── UserPrefCreateResource.java
│               │   │   ├── UserPrefResource.java
│               │   │   └── UserPrefUpdateResource.java
│               │   └── service
│               │       ├── QuoteService.java
│               │       ├── UserPrefService.java
│               │       └── implementation
│               │           ├── QuoteServiceImpl.java
│               │           └── UserPrefServiceImpl.java
│               ├── com // 공통 컴포넌트(API 응답, 예외처리 등)
│               │   ├── api
│               │   │   └── response
│               │   │       └── ApiRes.java
│               │   ├── converter
│               │   │   └── CommonConverter.java
│               │   ├── dto
│               │   │   └── BaseDto.java
│               │   ├── entity
│               │   │   ├── BaseEntity.java
│               │   │   └── QueryDslSupport.java
│               │   ├── exception
│               │   │   ├── BusinessInvalidValueException.java
│               │   │   └── GlobalExceptionHandler.java
│               │   ├── resource
│               │   │   ├── BaseResource.java
│               │   │   └── PageResource.java
│               │   └── util
│               │       └── StringUtil.java
│               └── sys // 유저, 권한, 코드 등 시스템 관련
│                   ├── config
│                   │   ├── FirebaseInitializer.java
│                   │   └── WebSecurityConfig.java
│                   ├── controller
│                   │   ├── CodeController.java
│                   │   └── UserController.java
│                   ├── domain
│                   │   ├── CommCd.java
│                   │   ├── CommCdTp.java
│                   │   ├── User.java
│                   │   └── WithdrawalReason.java
│                   ├── dto
│                   │   ├── CommCdDto.java
│                   │   ├── CommCdTpDto.java
│                   │   ├── FBUserRequestDto.java
│                   │   ├── TokenDto.java
│                   │   ├── UserDto.java
│                   │   └── WithdrawalDto.java
│                   ├── filter
│                   │   └── FirebaseFilter.java
│                   ├── repository
│                   │   ├── CommCdRepository.java
│                   │   ├── CommCdTpRepository.java
│                   │   ├── UserRepository.java
│                   │   └── WithdrawalRepository.java
│                   ├── resource
│                   │   ├── CommCdConverter.java
│                   │   ├── CommCdResource.java
│                   │   ├── CommCdTpResource.java
│                   │   ├── UserResource.java
│                   │   ├── UserUpdateResource.java
│                   │   └── UserWithdrawalResource.java
│                   └── service
│                       ├── CommCdService.java
│                       ├── CommCdTpService.java
│                       ├── TokenService.java
│                       ├── UserDetailService.java
│                       ├── UserService.java
│                       └── implementation
│                           ├── CommCdServiceImpl.java
│                           ├── CommCdTpServiceImpl.java
│                           └── TokenServiceImpl.java
└── resources // 설정 파일
    ├── application-dev.yml
    ├── application-local.yml
    ├── application-prod.yml
    ├── application.yml
    ├── firebase.json
    ├── static
    └── templates

```
### ERD
<img width="615" alt="스크린샷 2023-12-02 15 08 23" src="https://github.com/DDD-Community/PINGPONG-SERVER/assets/25236852/8ddba3e1-40e0-4d81-99a3-e914c6f148f5">

### APIs
http://3.39.40.128:9090/swagger-ui/index.html#

### WIKI
🔗 [스프링부트 3.0 프로젝트 생성/세팅 기록](https://github.com/14-team13/acoe-backend/wiki/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%83%9D%EC%84%B1%EA%B8%B0%EB%A1%9D)  
🔗 [깃헙액션 CICD 파이프라인 구축 기록](https://github.com/14-team13/acoe-backend/wiki/CICD-%ED%8C%8C%EC%9D%B4%ED%94%84%EB%9D%BC%EC%9D%B8-%EA%B5%AC%EC%B6%95%EA%B8%B0%EB%A1%9D)  
🔗 [깃헙 서브모듈로 설정정보 보호기록](https://github.com/14-team13/acoe-backend/wiki/Submodule%EB%A1%9C-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%84%A4%EC%A0%95%EC%A0%95%EB%B3%B4-%EB%B3%B4%ED%98%B8-%EA%B8%B0%EB%A1%9D)  
