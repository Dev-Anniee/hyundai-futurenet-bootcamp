import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

import "bootstrap/dist/css/bootstrap.min.css";
import "./index.css";

import App from "./App.jsx";

// TanStack Query가 서버 데이터를 캐시(임시 저장)하고 관리하는 중앙 객체입니다.
const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            retry: 1, // 요청이 실패하면 1번 더 재시도
            staleTime: 1000 * 30, // 30초 동안은 데이터를 "최신(fresh)"으로 간주
            gcTime: 1000 * 60 * 5, // 안 쓰는 캐시는 5분 뒤 메모리에서 삭제(기본값과 동일)
        },
    },
});

createRoot(document.getElementById("root")).render(
    <StrictMode>
        {/* QueryClientProvider로 감싸야 앱 어디서든 useQuery / useMutation 사용 가능 */}
        <QueryClientProvider client={queryClient}>
            <App />
        </QueryClientProvider>
    </StrictMode>
);
