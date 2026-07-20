package kr.or.kosa.constroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosa.domain.Board;
import kr.or.kosa.dto.BoardRequest;
import kr.or.kosa.dto.MessageResponse;
import kr.or.kosa.dto.PageResponse;
import kr.or.kosa.service.BoardService;

// 게시판 CRUD API
//   GET    /boards?page=&size=&keyword=   목록 (공개)
//   GET    /boards/{id}                   상세 (공개, 조회수+1)
//   POST   /boards                        등록 (로그인 필요)
//   PUT    /boards/{id}                   수정 (로그인 필요)
//   DELETE /boards/{id}                   삭제 (로그인 필요)
// (로그인 필요 여부는 SecurityConfig 에서 제어)
@RestController
@RequestMapping("/boards")
public class BoardController {

	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping
	public PageResponse<Board> list(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "") String keyword) {
		return boardService.list(page, size, keyword);
	}

	@GetMapping("/{id}")
	public Board detail(@PathVariable Long id) {
		return boardService.read(id);
	}

	@PostMapping
	public ResponseEntity<Board> create(@RequestBody BoardRequest request, Authentication authentication) {
		String username = authentication.getName();
		return ResponseEntity.status(HttpStatus.CREATED).body(boardService.create(request, username));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Board> update(@PathVariable Long id, @RequestBody BoardRequest request,
			Authentication authentication) {
		return ResponseEntity.ok(boardService.update(id, request, authentication.getName()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> delete(@PathVariable Long id, Authentication authentication) {
		boardService.delete(id, authentication.getName());
		return ResponseEntity.ok(new MessageResponse("게시글이 삭제되었습니다."));
	}
}
