package kr.or.kosa.dto;

// 게시글 등록/수정 요청 바디: { "title": "...", "writer": "...", "content": "..." }
public record BoardRequest(String title, String writer, String content) {
}
