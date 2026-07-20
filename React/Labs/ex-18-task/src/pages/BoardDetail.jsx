import { Link, useNavigate, useParams } from "react-router-dom";

import { useBoard, useDeleteBoard } from "../hooks/useBoard.js";
import useUserStore from "../store/userStore.js";

function BoardDetail() {
    const { id } = useParams();
    const navigate = useNavigate();

    // 로그인 여부에 따라 수정/삭제 버튼 노출
    const isLogin = useUserStore((state) => state.isLogin);

    const { data: board, isLoading, isError, error } = useBoard(id);

    const deleteMutation = useDeleteBoard();

    const handleDelete = () => {
        const result = window.confirm("게시글을 삭제하시겠습니까?");

        if (!result) {
            return;
        }

        deleteMutation.mutate(id, {
            onSuccess: () => {
                alert("게시글이 삭제되었습니다.");
                navigate("/boards");
            },
            onError: (err) => {
                if (err.response?.status === 401) {
                    alert("로그인이 필요합니다.");
                    navigate("/login");
                    return;
                }
                alert("게시글 삭제에 실패했습니다.");
            },
        });
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

    return (
        <div className="card shadow-sm">
            <div className="card-body">
                <h2 className="mb-3">{board.title}</h2>

                <div className="border-top border-bottom py-3 mb-4">
                    <div className="row">
                        <div className="col-md-4">작성자: {board.writer}</div>
                        <div className="col-md-4">조회수: {board.viewCount}</div>
                        <div className="col-md-4">
                            등록일: {board.createdAt?.substring(0, 10)}
                        </div>
                    </div>
                </div>

                <div
                    className="mb-5"
                    style={{ minHeight: "200px", whiteSpace: "pre-wrap" }}
                >
                    {board.content}
                </div>

                <div className="d-flex gap-2">
                    {/* 로그인한 사용자에게만 수정/삭제 노출 */}
                    {isLogin && (
                        <>
                            <Link
                                className="btn btn-outline-primary"
                                to={`/boards/${id}/update`}
                            >
                                수정
                            </Link>

                            <button
                                type="button"
                                className="btn btn-outline-danger"
                                onClick={handleDelete}
                                disabled={deleteMutation.isPending}
                            >
                                {deleteMutation.isPending ? "삭제 중..." : "삭제"}
                            </button>
                        </>
                    )}

                    <Link className="btn btn-secondary" to="/boards">
                        목록
                    </Link>
                </div>
            </div>
        </div>
    );
}

export default BoardDetail;
