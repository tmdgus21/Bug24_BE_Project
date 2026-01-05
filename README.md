# Bug24_BE_Project
Bug24_BE_Project

jetbrain 2025.3
PostgreSQL 17
JDK 21

## 기능
- 간단한 로그인 기능
- `POST /users` : 회원 생성 (비밀번호 BCrypt 암호화 저장)
- `GET /users`  : 전체 회원 조회
- `POST /login` : username + password 로그인, 성공 시 "login success" 반환

## 테스트 방법
1. 서버 실행
2. `POST /users` 로 계정 생성
3. 같은 계정으로 `POST /login` 요청
