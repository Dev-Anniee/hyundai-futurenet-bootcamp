# ex17-app-board — state 만으로 만드는 게시판 CRUD

서버(axios) 없이 `useState` 만으로 게시글을 관리하는 예제입니다.
(ex17-app-ajax 는 axios 로 목록을 "읽기"만 했다면, 이 예제는 등록/수정/삭제까지 합니다.)

## 실행 방법

```bash
npm install     # 최초 1회
npm run dev      # http://localhost:5173
```

## 게시글 데이터 구조

```js
{ id, title, content, writer }   // 글 1건 = 객체
```

## CRUD 흐름

| 기능 | 처리 함수 | 핵심 배열 메서드 |
| --- | --- | --- |
| C (등록) | `handleSubmit` (editingId === null) | `[...posts, newPost]` |
| R (목록) | `posts.map()` → `BoardList` | `map` + `key` |
| U (수정) | `handleEdit` → `handleSubmit` (editingId 존재) | `map` + 조건부 교체 |
| D (삭제) | `handleDelete` | `filter` |

## 파일 구성

- `src/App.jsx` — 모든 state 와 CRUD 함수 (상태 끌어올리기)
- `src/BoardForm.jsx` — 등록/수정 입력 폼 (props 로 값·함수 받음)
- `src/BoardList.jsx` — 목록을 `map()` 으로 렌더링
- `src/BoardItem.jsx` — 글 1건 + 수정/삭제 버튼

## 학습 포인트

1. **불변성**: state 배열을 직접 바꾸지 않고 `map`/`filter`/`spread` 로 새 배열을 만든다.
2. **상태 끌어올리기**: 모든 상태를 `App` 이 갖고, 자식에겐 props 로 값과 함수를 내린다.
3. **하나의 폼으로 등록·수정 겸용**: `editingId` 값으로 모드를 구분한다.
