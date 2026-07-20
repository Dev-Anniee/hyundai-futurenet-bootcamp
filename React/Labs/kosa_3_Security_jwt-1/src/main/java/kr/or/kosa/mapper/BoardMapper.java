package kr.or.kosa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.or.kosa.domain.Board;

@Mapper
public interface BoardMapper {

	// 목록 (제목 keyword 검색 + 페이징). keyword 가 빈 문자열이면 전체.
	@Select("""
			SELECT id, title, writer, owner, content, view_count, created_at
			FROM board
			WHERE title LIKE '%' || #{keyword} || '%'
			ORDER BY id DESC
			OFFSET #{offset} ROWS FETCH NEXT #{size} ROWS ONLY
			""")
	List<Board> findAll(@Param("offset") int offset, @Param("size") int size, @Param("keyword") String keyword);

	// 전체 개수 (페이징 계산용)
	@Select("SELECT COUNT(*) FROM board WHERE title LIKE '%' || #{keyword} || '%'")
	long count(@Param("keyword") String keyword);

	// 단건 조회
	@Select("SELECT id, title, writer, owner, content, view_count, created_at FROM board WHERE id = #{id}")
	Board findById(Long id);

	// 등록 (owner = 작성 계정)
	@Insert("INSERT INTO board(title, writer, owner, content, view_count) VALUES(#{title}, #{writer}, #{owner}, #{content}, 0)")
	void insert(Board board);

	// 수정
	@Update("UPDATE board SET title = #{title}, writer = #{writer}, content = #{content} WHERE id = #{id}")
	void update(Board board);

	// 삭제
	@Delete("DELETE FROM board WHERE id = #{id}")
	void deleteById(Long id);

	// 조회수 +1
	@Update("UPDATE board SET view_count = view_count + 1 WHERE id = #{id}")
	void increaseViewCount(Long id);
}
