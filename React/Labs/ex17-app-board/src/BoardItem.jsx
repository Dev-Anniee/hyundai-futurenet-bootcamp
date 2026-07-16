/*
  [ BoardItem : 게시글 1건 ]

  - 글 1건(post)을 받아서 화면에 그린다.
  - 수정 / 삭제 버튼을 누르면, App 에서 내려준 함수에 이 글의 정보를 넘긴다.
    · 수정 -> onEdit(post)   : 글 전체를 넘겨 폼을 채운다.
    · 삭제 -> onDelete(post.id) : id 만 넘겨 그 글을 지운다.
*/
export default function BoardItem({ post, onEdit, onDelete }) {

  return (
    <div className="board-item">
      <div className="board-item-head">
        <h3>{post.id}. {post.title}</h3>
        <span className="writer">✍️ {post.writer}</span>
      </div>

      <p className="content">{post.content}</p>

      <div className="buttons">
        <button className="ghost" onClick={() => onEdit(post)}>수정</button>
        <button className="danger" onClick={() => onDelete(post.id)}>삭제</button>
      </div>
    </div>
  )
}
