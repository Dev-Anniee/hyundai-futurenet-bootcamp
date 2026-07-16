# ex17-app-board-zustand — zustand + axios 로 만드는 게시판 CRUD

전역 상태 관리 라이브러리 **zustand** 에 CRUD 로직(axios)을 모아두고,
컴포넌트들은 **props 없이** store 에서 직접 꺼내 쓰는 예제.

## 실행 방법

```bash
npm install     # 최초 1회 (zustand, axios 포함)
npm start        # http://localhost:3002
```

## ex17-axios(axios) vs ex17-zustand(zustand+axios)

| | ex17-axios | **ex17-zustand** |
| --- | --- | --- |
| 상태 위치 | App.jsx 안 (`useState`) | store 파일 (컴포넌트 밖) |
| CRUD 함수 위치 | App.jsx 안 | store 안 |
| 자식에게 전달 | **props 로 일일이 내려줌** | **props 없음** — 각자 store 에서 꺼냄 |
| 목록↔폼 연동 | App 이 중개 | store 가 공유 (직접 연동) |

## zustand 기본 문법

```js
import { create } from 'zustand'

export const useBoardStore = create((set, get) => ({
  posts: [],                                   // 상태
  addPost: (p) => set((s) => ({ posts: [p, ...s.posts] })),  // 상태 변경 함수
  // set : 상태를 바꾼다 (useState 의 setState 역할)
  // get : 현재 상태를 읽는다 (함수 안에서 최신 값이 필요할 때)
}))
```

컴포넌트에서 사용:

```js
const posts = useBoardStore((s) => s.posts)       // 필요한 것만 콕 집어 꺼냄
const addPost = useBoardStore((s) => s.addPost)
```

## 파일 구성

- `src/store/useBoardStore.js` — ★ 전역 상태 + axios CRUD (이 예제의 핵심)
- `src/App.jsx` — 목록 불러오기만 하고, 자식에게 props 를 안 내려줌
- `src/BoardForm.jsx` / `BoardList.jsx` / `BoardItem.jsx` — 각자 store 에서 직접 꺼내 씀

## 핵심 정리

1. 여러 컴포넌트가 공유하는 상태는 store 한 곳에 모은다.
2. 상태를 바꾸는 함수(CRUD)도 store 안에 같이 둔다.
3. 컴포넌트는 `useBoardStore((s) => s.필요한것)` 으로 직접 꺼내 쓴다 → **props 내려주기(prop drilling) 사라짐**.
