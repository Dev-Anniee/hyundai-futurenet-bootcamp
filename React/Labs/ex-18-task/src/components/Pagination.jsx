// currentPage: 현재 페이지(0부터), totalPages: 전체 페이지 수
// onPageChange: 페이지 버튼 클릭 시 실행할 함수
function Pagination({ currentPage, totalPages, onPageChange }) {
    // 페이지가 1개 이하면 페이징 UI를 그리지 않습니다.
    if (!totalPages || totalPages <= 1) {
        return null;
    }

    const pages = Array.from({ length: totalPages }, (_, index) => index);

    return (
        <nav className="mt-4">
            <ul className="pagination justify-content-center">
                <li className={`page-item ${currentPage === 0 ? "disabled" : ""}`}>
                    <button
                        type="button"
                        className="page-link"
                        onClick={() => onPageChange(currentPage - 1)}
                    >
                        이전
                    </button>
                </li>

                {pages.map((page) => (
                    <li
                        key={page}
                        className={`page-item ${
                            currentPage === page ? "active" : ""
                        }`}
                    >
                        <button
                            type="button"
                            className="page-link"
                            onClick={() => onPageChange(page)}
                        >
                            {page + 1}
                        </button>
                    </li>
                ))}

                <li
                    className={`page-item ${
                        currentPage === totalPages - 1 ? "disabled" : ""
                    }`}
                >
                    <button
                        type="button"
                        className="page-link"
                        onClick={() => onPageChange(currentPage + 1)}
                    >
                        다음
                    </button>
                </li>
            </ul>
        </nav>
    );
}

export default Pagination;
