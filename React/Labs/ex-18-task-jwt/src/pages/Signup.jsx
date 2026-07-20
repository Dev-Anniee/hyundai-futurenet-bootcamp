import { useState } from "react";
import { useNavigate } from "react-router-dom";

import { signup } from "../services/authService.js";

function Signup() {
    const navigate = useNavigate();

    const [form, setForm] = useState({ username: "", password: "", name: "" });
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setForm((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError("");

        // 명세 제약: username 4~50자, password 8~100자, name 최대 50자
        if (form.username.trim().length < 4) {
            setError("아이디는 4자 이상이어야 합니다.");
            return;
        }
        if (form.password.length < 8) {
            setError("비밀번호는 8자 이상이어야 합니다.");
            return;
        }
        if (!form.name.trim()) {
            setError("이름을 입력하세요.");
            return;
        }

        try {
            setLoading(true);
            await signup(form);
            alert("회원가입이 완료되었습니다. 로그인해 주세요.");
            navigate("/login");
        } catch (err) {
            if (err.response?.status === 409) {
                setError("이미 사용 중인 아이디입니다.");
            } else {
                setError("회원가입에 실패했습니다. 입력값을 확인하세요.");
            }
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="card shadow-sm">
            <div className="card-body">
                <h2 className="page-title">회원가입</h2>

                {error && <div className="alert alert-danger">{error}</div>}

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label">아이디 (4~50자)</label>
                        <input
                            type="text"
                            className="form-control"
                            name="username"
                            value={form.username}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">비밀번호 (8~100자)</label>
                        <input
                            type="password"
                            className="form-control"
                            name="password"
                            value={form.password}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">이름</label>
                        <input
                            type="text"
                            className="form-control"
                            name="name"
                            value={form.name}
                            onChange={handleChange}
                        />
                    </div>

                    <button
                        type="submit"
                        className="btn btn-primary"
                        disabled={loading}
                    >
                        {loading ? "가입 중..." : "회원가입"}
                    </button>
                </form>
            </div>
        </div>
    );
}

export default Signup;
