import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vite.dev/config/
export default defineConfig({
    plugins: [react()],
    server: {
        // host: true → 0.0.0.0 바인딩. 같은 네트워크의 다른 기기에서
        // http://192.168.2.177:5173 으로 접속할 수 있게 합니다.
        host: true,
        port: 5173,
        // 개발 서버 프록시:
        // 브라우저는 같은 출처(localhost:5173)의 /api 로 요청하고,
        // Vite가 이를 실제 백엔드(192.168.2.183:3001)로 전달합니다.
        // → 크로스 사이트가 아니게 되어 세션 쿠키(JSESSIONID)가 정상 동작합니다.
        proxy: {
            "/api": {
                target: "http://192.168.2.183:3001",
                changeOrigin: true, // 요청의 Host 헤더를 백엔드 기준으로 변경
                cookieDomainRewrite: "", // Set-Cookie 도메인을 현재 호스트로 맞춤
            },
        },
    },
});
