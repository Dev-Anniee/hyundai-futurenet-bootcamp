/*
  [ BoardForm : 글 등록 / 수정 입력 폼 ]

  - 자기 상태(state)를 갖지 않는 '화면 전용' 컴포넌트.
  - 값(form)과 함수(onChange, onSubmit, onCancel)를 App 에게서 props 로 받는다.
  - editingId 가 있으면 '수정' 모드, 없으면 '등록' 모드로 버튼 글자만 바뀐다.
*/
export default function BoardForm({ form, editingId, onChange, onSubmit, onCancel }) {

  const isEditing = editingId !== null

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
          onChange={onChange}
        />
      </div>

      <div className="row">
        <label>작성자</label>
        <input
          type="text"
          name="writer"
          value={form.writer}
          placeholder="작성자를 입력하세요"
          onChange={onChange}
        />
      </div>

      <div className="row">
        <label>내용</label>
        <textarea
          name="content"
          rows={4}
          value={form.content}
          placeholder="내용을 입력하세요"
          onChange={onChange}
        />
      </div>

      <div className="buttons">
        <button className="primary" onClick={onSubmit}>
          {isEditing ? '수정 완료' : '등록'}
        </button>
        {isEditing && (
          <button className="ghost" onClick={onCancel}>
            취소
          </button>
        )}
      </div>
    </div>
  )
}
