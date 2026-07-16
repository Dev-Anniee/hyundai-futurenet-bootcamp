import { useBoardStore } from './store/useBoardStore'

/*
  [ BoardItem ]

  ex17-axios : onEdit, onDelete 를 props 로 받아서 호출했다.
  ex17-zustand : startEdit, deletePost 를 store 에서 직접 꺼내 쓴다.
         (post 자체는 목록 반복 데이터라 props 로 받는다)
*/
export default function BoardItem({ post }) {

  const startEdit = useBoardStore((s) => s.startEdit)
  const deletePost = useBoardStore((s) => s.deletePost)

  return (
    <div className="board-item">
      <div className="board-item-head">
        <h3>{post.id}. {post.title}</h3>
      </div>

      <p className="content">{post.body}</p>

      <div className="buttons">
        <button className="ghost" onClick={() => startEdit(post)}>수정</button>
        <button className="danger" onClick={() => deletePost(post.id)}>삭제</button>
      </div>
    </div>
  )
}
