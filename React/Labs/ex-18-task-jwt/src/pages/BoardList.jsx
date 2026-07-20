import { useState } from "react";
import { Link } from "react-router-dom";

import Pagination from "../components/Pagination.jsx";
import { useBoards } from "../hooks/useBoard.js";

function BoardList() {
    const [page, setPage] = useState(0); // 현재 페이지
    const [inputKeyword, setInputKeyword] = useState(""); // 입력창 검색어
    const [keyword, setKeyword] = useState(""); // 실제 검색에 쓰는 검색어

    // 서버에서 페이징된 목록을 가져옵니다.
    const { data, isLoading, isError, error, isFetching } = useBoards({
        page,
        size: 10,
        keyword,
    });

    // 검색 버튼 클릭 시에만 keyword 확정 → 첫 페이지부터 다시 조회
    const handleSearch = (event) => {
        event.preventDefault();

        setPage(0);
        setKeyword(inputKeyword);
    };

    if (isLoading) {
        return (
            <div className="alert alert-info">게시글을 불러오는 중입니다.</div>
        );
    }

    if (isError) {
        return (
            <div className="alert alert-danger">
                게시글 조회 실패: {error.message}
            </div>
        );
    }

    // 페이징 응답: 실제 게시글은 data.content 안에 들어 있습니다.
    const boards = data?.content ?? [];

    return (
        <div className="card shadow-sm">
            <div className="card-body">
                <div className="d-flex justify-content-between align-items-center mb-3">
                    <div>
                        <h2 className="page-title mb-1">게시글 목록</h2>

                        {isFetching && (
                            <small className="text-secondary">
                                데이터를 갱신하고 있습니다.
                            </small>
                        )}
                    </div>

                    <Link className="btn btn-primary" to="/boards/write">
                        글쓰기
                    </Link>
                </div>

                <form className="row g-2 mb-3" onSubmit={handleSearch}>
                    <div className="col-md-9">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="제목으로 검색하세요."
                            value={inputKeyword}
                            onChange={(event) =>
                                setInputKeyword(event.target.value)
                            }
                        />
                    </div>

                    <div className="col-md-3 d-grid">
                        <button
                            type="submit"
                            className="btn btn-outline-primary"
                        >
                            검색
                        </button>
                    </div>
                </form>

                <div className="table-responsive">
                    <table className="table table-hover align-middle">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>조회수</th>
                                <th>등록일</th>
                            </tr>
                        </thead>

                        <tbody>
                            {boards.length === 0 ? (
                                <tr>
                                    <td colSpan="5" className="text-center py-4">
                                        게시글이 없습니다.
                                    </td>
                                </tr>
                            ) : (
                                boards.map((board) => (
                                    <tr key={board.id}>
                                        <td>{board.id}</td>

                                        <td>
                                            <Link
                                                className="board-title"
                                                to={`/boards/${board.id}`}
                                            >
                                                {board.title}
                                            </Link>
                                        </td>

                                        <td>{board.writer}</td>
                                        <td>{board.viewCount}</td>
                                        <td>
                                            {board.createdAt?.substring(0, 10)}
                                        </td>
                                    </tr>
                                ))
                            )}
                        </tbody>
                    </table>
                </div>

                <Pagination
                    currentPage={data.number}
                    totalPages={data.totalPages}
                    onPageChange={setPage}
                />
            </div>
        </div>
    );
}

export default BoardList;
