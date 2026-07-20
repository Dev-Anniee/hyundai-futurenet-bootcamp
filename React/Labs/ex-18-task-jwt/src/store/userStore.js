import { create } from "zustand";

// create((set) => ({ 상태, 상태변경함수 }))
// set() 은 Zustand가 주는 "상태를 바꾸는 함수" 입니다.
const useUserStore = create((set) => ({
    user: null, // 로그인한 사용자 정보
    isLogin: false, // 로그인 여부
    authChecked: false, // 앱 시작 시 세션 확인(getMe)이 끝났는지 여부

    // 로그인: 사용자 정보를 저장하고 isLogin을 true로
    login: (userData) =>
        set({
            user: userData,
            isLogin: true,
        }),

    // 로그아웃: 정보를 비우고 isLogin을 false로
    logout: () =>
        set({
            user: null,
            isLogin: false,
        }),

    // 세션 확인 완료 표시 (성공/실패 상관없이 한 번 호출)
    setAuthChecked: () => set({ authChecked: true }),
}));

export default useUserStore;
