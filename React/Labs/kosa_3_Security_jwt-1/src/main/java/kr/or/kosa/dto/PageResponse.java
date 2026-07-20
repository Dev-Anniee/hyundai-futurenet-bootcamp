package kr.or.kosa.dto;

import java.util.List;

// 프론트가 기대하는 페이징 응답 모양: { content, number, size, totalElements, totalPages }
public record PageResponse<T>(List<T> content, int number, int size, long totalElements, int totalPages) {

	public static <T> PageResponse<T> of(List<T> content, int page, int size, long totalElements) {
		int totalPages = size == 0 ? 0 : (int) Math.ceil((double) totalElements / size);
		return new PageResponse<>(content, page, size, totalElements, totalPages);
	}
}
