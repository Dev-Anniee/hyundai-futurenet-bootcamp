import axios from "axios";

// 공통 axios 인스턴스
// baseURL 은 .env 의 VITE_API_BASE_URL 을 사용합니다. (끝에 /api 포함)
const api = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL || "/api",
    timeout: 5000,
    // ★ 세션 쿠키(JSESSIONID)를 요청에 담아 보내고, 응답 쿠키를 저장하려면 필수
    withCredentials: true,
    headers: {
        "Content-Type": "application/json",
    },
});

// 응답 인터셉터: 에러가 나면 콘솔에 원인을 출력해 디버깅을 돕습니다.
api.interceptors.response.use(
    (response) => response,
    (error) => {
        console.error("API 오류:", error.response?.data || error.message);

        return Promise.reject(error);
    }
);

export default api;
