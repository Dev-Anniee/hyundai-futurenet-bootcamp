# ex17-app-board-axios — axios 로 서버에 CRUD 요청하기

`axios` 로 서버(JSONPlaceholder)에 등록·수정·삭제 **요청(request)** 을 보내는 예제.
(ex17-board 은 state 만으로 처리 → 서버 요청 없음 / ex17-axios 는 async·await 로 비동기 요청)

## 실행 방법

```bash
npm install     # 최초 1회
npm start        # http://localhost:3001
```

## ⚠️ JSONPlaceholder 는 "가짜(fake)" 서버

POST / PUT / DELETE 요청에 성공 응답은 주지만 **실제로 저장하지 않는다.**
→ 새로고침하면 원래 5건으로 돌아간다.
→ 그래서 요청을 보낸 뒤 그 결과를 화면(state)에도 직접 반영해준다.
   (실무에서는 진짜 서버가 이 자리에 들어가고, 응답을 그대로 신뢰하거나 목록을 다시 불러온다.)

## CRUD ↔ HTTP 메서드 ↔ axios

| 기능 | HTTP | axios | 언제 |
| --- | --- | --- | --- |
| R (목록) | GET | `axios.get(URL)` | 화면 처음 뜰 때 (`useEffect`) |
| C (등록) | POST | `axios.post(URL, form)` | 등록 버튼 |
| U (수정) | PUT | `axios.put(`${URL}/${id}`, form)` | 수정 완료 버튼 |
| D (삭제) | DELETE | `axios.delete(`${URL}/${id}`)` | 삭제 버튼 |

## 핵심 개념

1. **비동기(async/await)**: 서버 응답은 언제 올지 모르므로 `await` 로 기다린다.
2. **try / catch**: 요청이 실패할 수 있으므로 에러를 잡아 사용자에게 알린다.
3. **loading / error 상태**: 요청 중·실패 상황을 화면에 표시한다.
4. **응답 후 state 반영**: 요청 성공 후 `posts` 배열을 갱신해 화면을 다시 그린다.

## 게시글 구조

```js
{ id, title, body }   // JSONPlaceholder 의 실제 구조
```

## 파일 구성

- `src/App.jsx` — axios 요청 + 상태 관리 (GET/POST/PUT/DELETE)
- `src/BoardForm.jsx` — 등록/수정 입력 폼
- `src/BoardList.jsx` / `src/BoardItem.jsx` — 목록 렌더링
