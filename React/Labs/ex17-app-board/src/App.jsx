import { useState } from 'react'
import './App.css'

import BoardForm from './BoardForm'
import BoardList from './BoardList'

/*
  [ ex17-board : state 만으로 만드는 게시판 CRUD ]

  - 서버(axios) 없이 useState 만으로 게시글을 관리한다.
  - 게시글 1건 = { id, title, content, writer } 객체
  - 모든 상태를 App 이 가지고 있고(상태 끌어올리기),
    화면(BoardForm / BoardList)에는 props 로 값과 함수를 내려준다.

  C : Create (등록)  -> handleSubmit (editingId 가 null 일 때)
  R : Read   (목록)  -> posts.map()  => BoardList
  U : Update (수정)  -> handleEdit + handleSubmit (editingId 가 있을 때)
  D : Delete (삭제)  -> handleDelete
*/

function App() {

  // 1) 게시글 목록 (초기 샘플 2건)
  const [posts, setPosts] = useState([
    { id: 1, title: '첫 번째 글', content: '리액트 게시판 연습입니다.', writer: '홍길동' },
    { id: 2, title: '두 번째 글', content: 'state 만으로 CRUD 를 만들어요.', writer: '김코딩' },
  ])

  // 2) 다음 글에 부여할 id (1건 등록할 때마다 +1)
  const [nextId, setNextId] = useState(3)

  // 3) 입력 폼 상태 (제목 / 내용 / 작성자)
  const [form, setForm] = useState({ title: '', content: '', writer: '' })

  // 4) 지금 수정 중인 글의 id (null 이면 '새 글 등록' 모드)
  const [editingId, setEditingId] = useState(null)

  // input 이 바뀔 때: name 을 보고 해당 항목만 교체
  const handleChange = (e) => {
    const { name, value } = e.target
    setForm({ ...form, [name]: value })
  }

  // 폼 초기화
  const resetForm = () => {
    setForm({ title: '', content: '', writer: '' })
    setEditingId(null)
  }

  // 등록 버튼(또는 수정 완료 버튼) 클릭
  const handleSubmit = () => {
    // 간단한 유효성 검사
    if (form.title.trim() === '' || form.writer.trim() === '') {
      alert('제목과 작성자는 필수입니다.')
      return
    }

    if (editingId === null) {
      // ----- Create : 새 글 추가 -----
      const newPost = { id: nextId, ...form }
      setPosts([...posts, newPost])   // 기존 배열 + 새 글
      setNextId(nextId + 1)
    } else {
      // ----- Update : editingId 와 같은 글만 새 내용으로 교체 -----
      setPosts(
        posts.map((post) =>
          post.id === editingId ? { ...post, ...form } : post
        )
      )
    }

    resetForm()
  }

  // 수정 버튼 클릭 : 해당 글 내용을 폼에 채우고 수정 모드로 전환
  const handleEdit = (post) => {
    setForm({ title: post.title, content: post.content, writer: post.writer })
    setEditingId(post.id)
  }

  // ----- Delete : 해당 id 를 제외한 나머지만 남긴다 -----
  const handleDelete = (id) => {
    if (!window.confirm('정말 삭제할까요?')) return
    setPosts(posts.filter((post) => post.id !== id))

    // 수정 중이던 글을 삭제하면 폼도 초기화
    if (editingId === id) resetForm()
  }

  return (
    <div className="board">
      <h1>리액트 게시판</h1>

      <div className="board-body">
        <BoardForm
          form={form}
          editingId={editingId}
          onChange={handleChange}
          onSubmit={handleSubmit}
          onCancel={resetForm}
        />

        <BoardList
          posts={posts}
          onEdit={handleEdit}
          onDelete={handleDelete}
        />
      </div>
    </div>
  )
}

export default App
