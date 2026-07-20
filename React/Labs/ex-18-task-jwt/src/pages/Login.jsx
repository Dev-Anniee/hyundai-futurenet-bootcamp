import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import { login as loginApi } from "../services/authService.js";
import useUserStore from "../store/userStore.js";

function Login() {
    const navigate = useNavigate();
    const login = useUserStore((state) => state.login); // Zustand 저장 함수

    const [form, setForm] = useState({ username: "", password: "" });
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setForm((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError("");

        if (!form.username.trim() || !form.password.trim()) {
            setError("아이디와 비밀번호를 입력하세요.");
            return;
        }

        try {
            setLoading(true);

            // 서버에 로그인 요청 → 성공 시 JSESSIONID 쿠키가 발급됨
            const user = await loginApi(form);

            // 응답으로 온 사용자 정보를 전역 상태에 저장
            login(user);

            navigate("/");
        } catch (err) {
            if (err.response?.status === 401) {
                setError("아이디 또는 비밀번호가 올바르지 않습니다.");
            } else {
                setError("로그인에 실패했습니다. 잠시 후 다시 시도하세요.");
            }
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="card shadow-sm">
            <div className="card-body">
                <h2 className="page-title">로그인</h2>

                {error && <div className="alert alert-danger">{error}</div>}

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label">아이디</label>
                        <input
                            type="text"
                            className="form-control"
                            name="username"
                            value={form.username}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">비밀번호</label>
                        <input
                            type="password"
                            className="form-control"
                            name="password"
                            value={form.password}
                            onChange={handleChange}
                        />
                    </div>

                    <button
                        type="submit"
                        className="btn btn-primary"
                        disabled={loading}
                    >
                        {loading ? "로그인 중..." : "로그인"}
                    </button>

                    <Link className="btn btn-link" to="/signup">
                        회원가입
                    </Link>
                </form>
            </div>
        </div>
    );
}

export default Login;
