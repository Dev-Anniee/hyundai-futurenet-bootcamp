import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import { useBoard, useUpdateBoard } from "../hooks/useBoard.js";

function BoardUpdate() {
    const { id } = useParams();
    const navigate = useNavigate();

    // 먼저 기존 게시글을 조회합니다.
    const { data: board, isLoading, isError } = useBoard(id);

    const updateMutation = useUpdateBoard();

    const [form, setForm] = useState({
        title: "",
        content: "",
        writer: "",
    });

    // 조회한 게시글(board)이 도착하면 폼에 값을 채웁니다.
    useEffect(() => {
        if (board) {
            setForm({
                title: board.title ?? "",
                content: board.content ?? "",
                writer: board.writer ?? "",
            });
        }
    }, [board]);

    const handleChange = (event) => {
        const { name, value } = event.target;

        setForm((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if (!form.title.trim()) {
            alert("제목을 입력하세요.");
            return;
        }

        if (!form.content.trim()) {
            alert("내용을 입력하세요.");
            return;
        }

        updateMutation.mutate(
            {
                id,
                boardData: form,
            },
            {
                onSuccess: () => {
                    alert("게시글이 수정되었습니다.");
                    navigate(`/boards/${id}`);
                },
                onError: () => {
                    alert("게시글 수정에 실패했습니다.");
                },
            }
        );
    };

    if (isLoading) {
        return (
            <div className="alert alert-info">게시글을 불러오는 중입니다.</div>
        );
    }

    if (isError) {
        return (
            <div className="alert alert-danger">
                게시글을 조회할 수 없습니다.
            </div>
        );
    }

    return (
        <div className="card shadow-sm">
            <div className="card-body">
                <h2 className="page-title">게시글 수정</h2>

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label">제목</label>

                        <input
                            type="text"
                            className="form-control"
                            name="title"
                            value={form.title}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">작성자</label>

                        <input
                            type="text"
                            className="form-control"
                            name="writer"
                            value={form.writer}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">내용</label>

                        <textarea
                            className="form-control"
                            name="content"
                            rows="10"
                            value={form.content}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="d-flex gap-2">
                        <button
                            type="submit"
                            className="btn btn-primary"
                            disabled={updateMutation.isPending}
                        >
                            {updateMutation.isPending ? "수정 중..." : "수정"}
                        </button>

                        <button
                            type="button"
                            className="btn btn-secondary"
                            onClick={() => navigate(`/boards/${id}`)}
                        >
                            취소
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default BoardUpdate;
