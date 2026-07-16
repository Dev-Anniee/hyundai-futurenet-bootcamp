import { useBoardStore } from './store/useBoardStore'

/*
  [ BoardForm ]

  ex17-axios : App 에게서 form, onChange, onSubmit ... 을 props 로 받았다.
  ex17-zustand : props 없이 store 에서 직접 꺼낸다. (아래를 보세요)
*/
export default function BoardForm() {

  const form = useBoardStore((s) => s.form)
  const editingId = useBoardStore((s) => s.editingId)
  const changeForm = useBoardStore((s) => s.changeForm)
  const submitPost = useBoardStore((s) => s.submitPost)
  const resetForm = useBoardStore((s) => s.resetForm)

  const isEditing = editingId !== null

  const handleChange = (e) => changeForm(e.target.name, e.target.value)

  return (
    <div className="board-form">
      <h2>{isEditing ? '✏️ 글 수정' : '📝 새 글 등록'}</h2>

      <div className="row">
        <label>제목</label>
        <input
          type="text"
          name="title"
          value={form.title}
          placeholder="제목을 입력하세요"
          onChange={handleChange}
        />
      </div>

      <div className="row">
        <label>내용</label>
        <textarea
          name="body"
          rows={5}
          value={form.body}
          placeholder="내용을 입력하세요"
          onChange={handleChange}
        />
      </div>

      <div className="buttons">
        <button className="primary" onClick={submitPost}>
          {isEditing ? '수정 완료' : '등록'}
        </button>
        {isEditing && (
          <button className="ghost" onClick={resetForm}>
            취소
          </button>
        )}
      </div>
    </div>
  )
}
