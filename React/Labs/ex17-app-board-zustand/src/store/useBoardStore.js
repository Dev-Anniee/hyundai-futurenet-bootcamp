import { create } from 'zustand'
import axios from 'axios'

/*
  [ zustand store : 게시판 전역 상태 + axios CRUD ]

  ★ ex17-axios 와의 차이 ★
  - ex17-axios : 상태와 CRUD 함수가 App.jsx 안에 있었고, 자식에게 props 로 내려줬다.
  - ex17-zustand : 상태와 CRUD 함수를 '컴포넌트 밖' 이 store 에 모아둔다.
           어느 컴포넌트든 useBoardStore() 로 직접 꺼내 쓴다 → props 필요 없음!

  create((set, get) => ({ ...상태..., ...함수... }))
   - set : 상태를 바꾸는 함수 (useState 의 setState 역할)
   - get : 현재 상태를 읽는 함수 (함수 안에서 최신 값이 필요할 때)

  ※ JSONPlaceholder 는 가짜 서버라 실제 저장은 안 됨.
    요청을 보낸 뒤 결과를 store 의 posts 에 직접 반영해준다. (ex17-axios 와 동일)
*/

const URL = 'https://jsonplaceholder.typicode.com/posts'

export const useBoardStore = create((set, get) => ({

  // ---------- 상태(state) ----------
  posts: [],
  form: { title: '', body: '' },
  editingId: null,
  loading: true,
  error: '',

  // ---------- 폼 관련 함수 ----------
  // 입력값 바뀔 때: name 항목만 교체
  changeForm: (name, value) =>
    set((state) => ({ form: { ...state.form, [name]: value } })),

  resetForm: () => set({ form: { title: '', body: '' }, editingId: null }),

  // 수정 버튼: 그 글을 폼에 채우고 수정 모드로
  startEdit: (post) =>
    set({ form: { title: post.title, body: post.body }, editingId: post.id }),

  // ---------- CRUD (axios 요청) ----------

  // R : 목록 불러오기
  fetchPosts: async () => {
    try {
      const res = await axios.get(`${URL}?_limit=5`)
      set({ posts: res.data })
    } catch (err) {
      console.log(err)
      set({ error: '목록을 불러오지 못했습니다.' })
    } finally {
      set({ loading: false })
    }
  },

  // C / U : 등록 또는 수정 (editingId 로 구분)
  submitPost: async () => {
    const { form, editingId, posts } = get()   // 현재 상태 읽기

    if (form.title.trim() === '') {
      alert('제목은 필수입니다.')
      return
    }

    try {
      if (editingId === null) {
        // C : POST
        const res = await axios.post(URL, form)
        console.log('등록 응답:', res.data)
        const newPost = { ...res.data, id: Date.now() } // 화면용 고유 id
        set({ posts: [newPost, ...posts] })
      } else {
        // U : PUT
        const res = await axios.put(`${URL}/${editingId}`, form)
        console.log('수정 응답:', res.data)
        set({
          posts: posts.map((p) =>
            p.id === editingId ? { ...p, ...form } : p
          ),
        })
      }
      get().resetForm()   // store 안 다른 함수도 get() 으로 호출 가능
    } catch (err) {
      console.log(err)
      alert('요청 처리 중 오류가 발생했습니다.')
    }
  },

  // D : 삭제
  deletePost: async (id) => {
    if (!window.confirm('정말 삭제할까요?')) return
    try {
      await axios.delete(`${URL}/${id}`)
      set((state) => ({ posts: state.posts.filter((p) => p.id !== id) }))
      if (get().editingId === id) get().resetForm()
    } catch (err) {
      console.log(err)
      alert('삭제 중 오류가 발생했습니다.')
    }
  },
}))
