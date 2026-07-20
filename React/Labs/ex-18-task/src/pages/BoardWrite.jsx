import { useState } from "react";
import { useNavigate } from "react-router-dom";

import { useCreateBoard } from "../hooks/useBoard.js";
import useUserStore from "../store/userStore.js";

function BoardWrite() {
    const navigate = useNavigate();
    const user = useUserStore((state) => state.user);

    const createMutation = useCreateBoard();

    // 작성자는 로그인한 사용자 이름으로 기본값 세팅
    const [form, setForm] = useState({
        title: "",
        content: "",
        writer: user?.name ?? "",
    });

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

        if (!form.writer.trim()) {
            alert("작성자를 입력하세요.");
            return;
        }

        if (!form.content.trim()) {
            alert("내용을 입력하세요.");
            return;
        }

        // mutate(값, { onSuccess, onError }) 로 서버에 등록 요청
        createMutation.mutate(form, {
            onSuccess: () => {
                alert("게시글이 등록되었습니다.");
                navigate("/boards");
            },
            onError: (error) => {
                alert(
                    error.response?.data?.message ??
                        "게시글 등록에 실패했습니다."
                );
            },
        });
    };

    return (
        <div className="card shadow-sm">
            <div className="card-body">
                <h2 className="page-title">게시글 등록</h2>

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
                            disabled={createMutation.isPending}
                        >
                            {createMutation.isPending ? "등록 중..." : "등록"}
                        </button>

                        <button
                            type="button"
                            className="btn btn-secondary"
                            onClick={() => navigate("/boards")}
                        >
                            취소
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default BoardWrite;
