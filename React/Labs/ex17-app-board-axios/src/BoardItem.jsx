/*
  [ BoardItem : 게시글 1건 ]

  - 글 1건(post = { id, title, body })을 받아 화면에 그린다.
  - 수정 -> onEdit(post) : 글 전체를 넘겨 폼을 채운다.
  - 삭제 -> onDelete(post.id) : id 만 넘겨 그 글을 지운다.
*/
export default function BoardItem({ post, onEdit, onDelete }) {

  return (
    <div className="board-item">
      <div className="board-item-head">
        <h3>{post.id}. {post.title}</h3>
      </div>

      <p className="content">{post.body}</p>

      <div className="buttons">
        <button className="ghost" onClick={() => onEdit(post)}>수정</button>
        <button className="danger" onClick={() => onDelete(post.id)}>삭제</button>
      </div>
    </div>
  )
}
