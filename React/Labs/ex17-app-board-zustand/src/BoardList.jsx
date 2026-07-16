import { useBoardStore } from './store/useBoardStore'
import BoardItem from './BoardItem'

/*
  [ BoardList ]

  ex17-axios : App 에게서 posts 를 props 로 받았다.
  ex17-zustand : store 에서 posts 를 직접 꺼낸다. (App 은 아무것도 안 넘겨줬음)
*/
export default function BoardList() {

  const posts = useBoardStore((s) => s.posts)

  return (
    <div className="board-list">
      <h2>📚 게시글 목록 ({posts.length}건)</h2>

      {posts.length === 0 ? (
        <p className="empty">등록된 글이 없습니다.</p>
      ) : (
        // post 는 반복 데이터라 자식에게 넘겨준다 (이건 prop drilling 이 아님)
        posts.map((post) => <BoardItem key={post.id} post={post} />)
      )}
    </div>
  )
}
