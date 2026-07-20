# JWT 인증 API - Postman 테스트 가이드

Base URL: `http://localhost:8090`

## 준비
1. Oracle 실행 후 `src/main/resources/db/oracle_user2.sql` 을 **1회 실행** (user2 테이블 생성)
2. 서버 실행: `./gradlew bootRun` (또는 IDE 에서 `Kosa3SecurityJwt1Application` 실행)
3. 서버 시작 시 초기 계정이 자동 생성됨
   - `admin` / `12345678`  → ROLE_ADMIN
   - `hong`  / `12345678`  → ROLE_USER

---

## 1. 회원가입  `POST /auth/signup`
- Body → raw → JSON
```json
{ "username": "kim1", "password": "12345678", "name": "김철수" }
```
- 성공 **201**
```json
{ "id": 4, "username": "kim1", "name": "김철수", "role": "ROLE_USER" }
```
- 아이디 중복 **409**
```json
{ "status": 409, "error": "Conflict", "message": "이미 사용 중인 아이디입니다: kim1" }
```

## 2. 로그인  `POST /auth/login`
```json
{ "username": "kim1", "password": "12345678" }
```
- 성공 **200** — 응답 바디에 `token` 이 보이고, `token` 쿠키(HttpOnly)도 함께 내려감
```json
{ "id": 4, "username": "kim1", "name": "김철수", "role": "ROLE_USER",
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9...." }
```
- 실패 **401**
```json
{ "status": 401, "error": "Unauthorized", "message": "아이디 또는 비밀번호가 올바르지 않습니다." }
```

## 3. 내 정보  `GET /auth/me`  (인증 필요)
- 인증 방법 2가지 중 하나:
  - **헤더**: `Authorization: Bearer <위에서 받은 token>`
  - **쿠키**: 로그인 응답의 `token` 쿠키 (Postman 이 자동 저장/전송)
- 성공 **200**
```json
{ "id": 4, "username": "kim1", "name": "김철수", "role": "ROLE_USER" }
```
- 토큰 없음/만료 **401**

## 4. 로그아웃  `POST /auth/logout`
- 성공 **200** — `token` 쿠키 만료(삭제)
```json
{ "message": "로그아웃 되었습니다." }
```

---

## 권한(ROLE) 테스트
`Authorization: Bearer <token>` 헤더를 넣고 요청

| 요청 | hong(USER) 토큰 | admin(ADMIN) 토큰 |
|------|-----------------|-------------------|
| `GET /user/info`  | 200 | 401/403 |
| `GET /admin/info` | 403 | 200 |

- 권한 부족 **403**
```json
{ "status": 403, "error": "Forbidden", "message": "접근 권한이 없습니다." }
```

---

## 참고
- 기존 강의용 엔드포인트(`/login`, `/register`, `/user/info`, `/admin/info`)도 그대로 동작합니다.
- 프론트(`ex-18-task-jwt`)와 연동 시: 프론트는 `/api/auth/*` 로 호출 → Vite 프록시가 `http://localhost:8090` 으로 전달 → JWT 쿠키로 자동 인증됩니다.
