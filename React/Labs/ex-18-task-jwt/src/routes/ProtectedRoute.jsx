import { Navigate } from "react-router-dom";

import useUserStore from "../store/userStore.js";

// 로그인한 사용자만 children(화면)을 볼 수 있게 하는 가드
function ProtectedRoute({ children }) {
    const isLogin = useUserStore((state) => state.isLogin);
    const authChecked = useUserStore((state) => state.authChecked);

    // 세션 확인(getMe)이 끝나기 전엔 판단을 미룹니다.
    // (새로고침 직후 로그인 상태인데도 로그인 페이지로 튕기는 것 방지)
    if (!authChecked) {
        return <div className="alert alert-info">확인 중입니다...</div>;
    }

    // 확인이 끝났는데 로그인 상태가 아니면 로그인 페이지로
    if (!isLogin) {
        return <Navigate to="/login" replace />;
    }

    return children;
}

export default ProtectedRoute;
