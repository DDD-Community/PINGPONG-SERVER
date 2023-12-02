# PINGPONG-SERVER
About DDD 9기 IOS 3팀 SERVER

## About Service
- 🥖 명언제과점
- 개인 취향별 맞춤 명언 추천 서비스

## Skill Stack
- 🍃 SpringBoot 3.0.3
- Java 17
- JPA/QueryDsl/PostgreSQL
- Github Action
- AWS EC2/RDS
- Docker
- Swagger

## 🌟 Team
|Developer|Designer|Designer|Developer|Developer|
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
