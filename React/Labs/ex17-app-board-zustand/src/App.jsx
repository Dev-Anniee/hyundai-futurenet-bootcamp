import { useEffect } from 'react'
import './App.css'

import { useBoardStore } from './store/useBoardStore'
import BoardForm from './BoardForm'
import BoardList from './BoardList'

/*
  [ ex17-zustand : zustand + axios ]

  ★ App 을 보세요 ★
  ex17-axios 에서는 App 이 posts, form, 모든 CRUD 함수를 갖고
  BoardForm / BoardList 에 props 로 일일이 내려줬다.

  ex17-zustand 에서는 그 모든 게 store 로 빠졌다.
  → App 은 처음에 목록만 불러오고, 자식에게 props 를 하나도 안 내려준다!
*/
function App() {

  // store 에서 필요한 것만 콕 집어 꺼낸다
  const loading = useBoardStore((s) => s.loading)
  const error = useBoardStore((s) => s.error)
  const fetchPosts = useBoardStore((s) => s.fetchPosts)

  // 처음 화면이 뜰 때 목록 GET
  useEffect(() => {
    fetchPosts()
  }, [fetchPosts])

  if (loading) return <div className="board"><h1>리액트 게시판 (zustand)</h1><p>로딩 중 .....</p></div>
  if (error)   return <div className="board"><h1>리액트 게시판 (zustand)</h1><p>{error}</p></div>

  return (
    <div className="board">
      <h1>리액트 게시판 (zustand)</h1>

      <div className="board-body">
        {/* props 가 하나도 없다! 각자 store 에서 알아서 꺼내 씀 */}
        <BoardForm />
        <BoardList />
      </div>
    </div>
  )
}

export default App
