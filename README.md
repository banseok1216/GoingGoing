# GoingGoing

![KakaoTalk_20240430_163159921](https://github.com/banseok1216/GoingGoing/assets/114564687/c87f30af-6077-4374-9e16-c1ed8c156359)

GoingGoing은 일정을 기록하고 기억하기 위한 플래너입니다. 여러분만의 외출 준비 루틴을 계획하고, 준비 상황을 일행과 공유할 수 있도록 도와줍니다. 다양한 기능으로 효율적인 외출을 즐길 수 있습니다.

## 주요 기능

- 일정 기록 및 관리: 일정을 기록하고 쉽게 관리할 수 있습니다.
- 개인화된 외출 루틴: 나만의 외출 준비 루틴을 계획하여 효율적인 외출을 도모할 수 있습니다.
- 일행과의 공유: 나의 준비 상황을 일행과 공유하여 함께 준비할 수 있습니다.
- 푸시 알림: 일행과의 공유를 위해 푸시 알림을 지원합니다.

## Back-End

![KakaoTalk_20240516_234351870](https://github.com/banseok1216/GoingGoing/assets/114564687/0a590827-0d36-4149-aef1-958f0fe9ea49)


GoingGoing은 멀티 모듈 아키텍처를 사용하여 관심사를 분리하고 각 모듈을 재사용 가능하게 만들었습니다. 아래는 주요 기술 및 구성 요소에 대한 설명입니다:

- **멀티 모듈 아키텍처**: common 모듈, api 모듈, domain 모듈, storage 모듈로 관심사를 분리하여 유지보수 및 확장성을 향상시켰습니다.
- **JPA N+1 문제 해결**: ManyToOne 관계에서 발생하는 N+1 문제를 해결하여 성능을 향상시켰습니다.
- **JWT 토큰 사용**: stateless 방식의 장점을 활용하여 JWT 토큰을 사용하여 인증 및 권한 부여를 구현했습니다.
- **rabbitmq 도입**: 비동기 통신을 위해 rabbitmq 도입하여 push 관련 서버와의 통신을 효율적으로 관리 및 다른 인스턴스와 캐시 동기화를 구현했습니다.
## 사용 기술

- Java
- Spring Boot
- JPA
- JWT
- Redis
- docker
