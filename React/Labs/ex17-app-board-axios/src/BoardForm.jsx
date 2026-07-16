/*
  [ BoardForm : 글 등록 / 수정 입력 폼 ]

  - 자기 상태 없이 App 에게서 값(form)과 함수(props)를 받아 화면만 그린다.
  - editingId 유무로 '등록' / '수정' 버튼 글자가 바뀐다.
  - JSONPlaceholder 구조에 맞춰 title / body 두 항목만 받는다.
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
        <label>내용</label>
        <textarea
          name="body"
          rows={5}
          value={form.body}
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
