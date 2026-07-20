import { useEffect } from "react";

import Router from "./routes/Router.jsx";
import { getMe } from "./services/authService.js";
import useUserStore from "./store/userStore.js";

function App() {
    const login = useUserStore((state) => state.login);
    const setAuthChecked = useUserStore((state) => state.setAuthChecked);

    // 앱 시작 시: 세션 쿠키가 살아있으면 로그인 상태를 복원합니다.
    // (새로고침해도 로그인이 풀리지 않게)
    useEffect(() => {
        getMe()
            .then((user) => login(user)) // 세션 유효 → 사용자 정보 저장
            .catch(() => {
                // 401 등 → 로그인 안 된 상태 (아무것도 안 함)
            })
            .finally(() => setAuthChecked()); // 확인 끝났음을 표시
    }, [login, setAuthChecked]);

    return <Router />;
}

export default App;
