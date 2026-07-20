import api from "../api/axios.js";

// 회원가입: POST /auth/signup  (인증 불필요)
export const signup = async ({ username, password, name }) => {
    const response = await api.post("/auth/signup", { username, password, name });

    return response.data;
};

// 로그인: POST /auth/login  → 성공 시 서버가 JSESSIONID 쿠키를 발급
export const login = async ({ username, password }) => {
    const response = await api.post("/auth/login", { username, password });

    return response.data;
};

// 로그인 사용자 조회: GET /auth/me  (세션 쿠키 필요)
export const getMe = async () => {
    const response = await api.get("/auth/me");

    return response.data;
};

// 로그아웃: POST /auth/logout  → 서버 세션·쿠키 폐기
export const logout = async () => {
    const response = await api.post("/auth/logout");

    return response.data;
};
