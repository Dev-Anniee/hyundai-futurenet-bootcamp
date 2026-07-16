import BoardItem from './BoardItem'

/*
  [ BoardList : 게시글 목록 ]

  - posts 배열을 map() 으로 돌면서 BoardItem 을 하나씩 그린다.
  - key 에는 각 글의 고유 id 를 넣는다.
*/
export default function BoardList({ posts, onEdit, onDelete }) {

  return (
    <div className="board-list">
      <h2>📚 게시글 목록 ({posts.length}건)</h2>

      {posts.length === 0 ? (
        <p className="empty">등록된 글이 없습니다.</p>
      ) : (
        posts.map((post) => (
          <BoardItem
            key={post.id}
            post={post}
            onEdit={onEdit}
            onDelete={onDelete}
          />
        ))
      )}
    </div>
  )
}
