import { useEffect, useState } from 'react'
import axios from 'axios'
import './App.css'

import BoardForm from './BoardForm'
import BoardList from './BoardList'

/*
  [ ex17-axios : axios 로 서버에 CRUD 요청 보내기 ]  (JSONPlaceholder)

  - ex17-board 은 state 만으로 처리 → 서버 요청이 없었다.
  - ex17-axios 는 axios 로 서버에 "요청(request)"을 보낸다. (async / await = 비동기)

  ★ 아주 중요한 주의 ★
  JSONPlaceholder 는 '가짜(fake)' 서버다.
  POST / PUT / DELETE 를 보내면 성공 응답은 정상적으로 돌려주지만
  실제로 저장하지는 않는다. (그래서 새로고침하면 원래대로 돌아감)
  → 그래서 "요청을 보낸 뒤, 그 결과를 화면(state)에도 직접 반영" 해준다.
     실무에서는 진짜 서버가 이 자리에 들어가고, 보통 다시 목록을 불러오거나
     서버가 준 응답을 그대로 신뢰한다.

  게시글 1건 = { id, title, body }   (JSONPlaceholder 의 실제 구조)
*/

const URL = 'https://jsonplaceholder.typicode.com/posts'

function App() {

  const [posts, setPosts] = useState([])
  const [form, setForm] = useState({ title: '', body: '' })
  const [editingId, setEditingId] = useState(null)

  const [loading, setLoading] = useState(true)  // 처음 목록 불러오는 중?
  const [error, setError] = useState('')        // 에러 메시지

  // ----- R : 처음 화면이 뜰 때 목록을 GET 요청으로 불러오기 -----
  useEffect(() => {
    axios.get(`${URL}?_limit=5`)
      .then((res) => setPosts(res.data))
      .catch((err) => {
        console.log(err)
        setError('목록을 불러오지 못했습니다.')
      })
      .finally(() => setLoading(false))
  }, [])

  const handleChange = (e) => {
    const { name, value } = e.target
    setForm({ ...form, [name]: value })
  }

  const resetForm = () => {
    setForm({ title: '', body: '' })
    setEditingId(null)
  }

  // 등록 / 수정 완료 버튼
  const handleSubmit = async () => {
    if (form.title.trim() === '') {
      alert('제목은 필수입니다.')
      return
    }

    try {
      if (editingId === null) {
        // ----- C : POST 로 새 글 등록 요청 -----
        const res = await axios.post(URL, form)
        console.log('등록 응답:', res.data)   // { id: 101, ...form }

        // 가짜 서버는 항상 id:101 을 주므로, 목록에서 겹치지 않게 화면용 고유 id 사용
        const newPost = { ...res.data, id: Date.now() }
        setPosts([newPost, ...posts])   // 맨 앞에 추가
      } else {
        // ----- U : PUT 로 수정 요청 -----
        const res = await axios.put(`${URL}/${editingId}`, form)
        console.log('수정 응답:', res.data)

        setPosts(posts.map((post) =>
          post.id === editingId ? { ...post, ...form } : post
        ))
      }
      resetForm()
    } catch (err) {
      console.log(err)
      alert('요청 처리 중 오류가 발생했습니다.')
    }
  }

  const handleEdit = (post) => {
    setForm({ title: post.title, body: post.body })
    setEditingId(post.id)
  }

  // ----- D : DELETE 로 삭제 요청 -----
  const handleDelete = async (id) => {
    if (!window.confirm('정말 삭제할까요?')) return

    try {
      await axios.delete(`${URL}/${id}`)
      setPosts(posts.filter((post) => post.id !== id))
      if (editingId === id) resetForm()
    } catch (err) {
      console.log(err)
      alert('삭제 중 오류가 발생했습니다.')
    }
  }

  // 처음 로딩 / 에러 화면
  if (loading) return <div className="board"><h1>리액트 게시판 (axios)</h1><p>로딩 중 .....</p></div>
  if (error)   return <div className="board"><h1>리액트 게시판 (axios)</h1><p>{error}</p></div>

  return (
    <div className="board">
      <h1>리액트 게시판 (axios)</h1>

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
