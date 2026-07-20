import { Link, NavLink, useNavigate } from "react-router-dom";

import { logout as logoutApi } from "../services/authService.js";
import useUserStore from "../store/userStore.js";

function Header() {
    const navigate = useNavigate();
    const { user, isLogin, logout } = useUserStore();

    const handleLogout = async () => {
        try {
            await logoutApi(); // 서버 세션·쿠키 폐기
        } catch {
            // 서버 로그아웃이 실패해도 화면 상태는 로그아웃 처리
        } finally {
            logout(); // Zustand 전역 상태 비우기
            navigate("/");
        }
    };

    return (
        <header>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <div className="container">
                    <Link className="navbar-brand" to="/">
                        React Board
                    </Link>

                    <div className="navbar-nav me-auto">
                        <NavLink className="nav-link" to="/">
                            홈
                        </NavLink>
                        <NavLink className="nav-link" to="/boards">
                            게시판
                        </NavLink>
                    </div>

                    <div className="d-flex align-items-center gap-2 text-white">
                        {isLogin ? (
                            <>
                                <span>{user?.name}님</span>
                                <button
                                    type="button"
                                    className="btn btn-outline-light btn-sm"
                                    onClick={handleLogout}
                                >
                                    로그아웃
                                </button>
                            </>
                        ) : (
                            <>
                                <Link
                                    className="btn btn-outline-light btn-sm"
                                    to="/login"
                                >
                                    로그인
                                </Link>
                                <Link
                                    className="btn btn-light btn-sm"
                                    to="/signup"
                                >
                                    회원가입
                                </Link>
                            </>
                        )}
                    </div>
                </div>
            </nav>
        </header>
    );
}

export default Header;
