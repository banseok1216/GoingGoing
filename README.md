<img width="1267" alt="goinggoing" src="https://github.com/banseok1216/GoingGoing/assets/114564687/8473e02a-eb28-4479-a057-30efcaac60c6">

- 일정을 기록하고 기억하기 위한 플래너
- 나만의 외출 준비 루틴을 계획
- 나의 준비 상황을 일행과 공유해주는 푸시 알림

# Back-End

- 멀티 모듈 사용
    - common 모듈, api 모듈, domain 모듈, storage 모듈으로 관심사 분리
- JPA N+1 문제 해결
    - ManyToOne에서 일어나는 N+1 문제해결
- JWT 토큰 사용
    - state less 방식의 장점을 이용한 JWT 토큰 사용
- push 관련 서버와 비동기 통신을 위한 redis pub/sub 도입
