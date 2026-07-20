package kr.or.kosa.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import kr.or.kosa.domain.Board;
import kr.or.kosa.dto.BoardRequest;
import kr.or.kosa.dto.PageResponse;
import kr.or.kosa.mapper.BoardMapper;

// 게시판 CRUD 비즈니스 로직 (최소 구현)
@Service
public class BoardService {

	private final BoardMapper boardMapper;

	public BoardService(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	// 목록 (페이징 + 검색)
	public PageResponse<Board> list(int page, int size, String keyword) {
		String kw = (keyword == null) ? "" : keyword;
		int offset = page * size;
		return PageResponse.of(
				boardMapper.findAll(offset, size, kw),
				page, size,
				boardMapper.count(kw));
	}

	// 상세 조회 (조회수 +1)
	public Board read(Long id) {
		boardMapper.increaseViewCount(id);
		return boardMapper.findById(id);
	}

	// 단건 조회 (조회수 증가 없음 - 수정 응답용)
	public Board find(Long id) {
		return boardMapper.findById(id);
	}

	// 등록 (owner = 로그인 계정 username)
	public Board create(BoardRequest req, String username) {
		Board board = new Board();
		board.setTitle(req.title());
		board.setWriter(req.writer());
		board.setOwner(username);
		board.setContent(req.content());
		boardMapper.insert(board);
		return board;
	}

	// 수정 (본인 글만)
	public Board update(Long id, BoardRequest req, String username) {
		checkOwner(id, username);
		Board board = new Board();
		board.setId(id);
		board.setTitle(req.title());
		board.setWriter(req.writer());
		board.setContent(req.content());
		boardMapper.update(board);
		return boardMapper.findById(id);
	}

	// 삭제 (본인 글만)
	public void delete(Long id, String username) {
		checkOwner(id, username);
		boardMapper.deleteById(id);
	}

	// 소유자 검증: 글이 없으면 404, 내 글이 아니면 403
	private void checkOwner(Long id, String username) {
		Board board = boardMapper.findById(id);
		if (board == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다.");
		}
		if (board.getOwner() == null || !board.getOwner().equals(username)) {
			throw new AccessDeniedException("본인이 작성한 글만 수정/삭제할 수 있습니다.");
		}
	}
}
