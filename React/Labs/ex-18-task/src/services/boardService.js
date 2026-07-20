import api from "../api/axios.js";

// 목록 조회: GET /boards?page=&size=&keyword=  → 페이징 객체 { content, totalPages, ... }
export const getBoards = async ({ page = 0, size = 10, keyword = "" }) => {
    const response = await api.get("/boards", {
        params: { page, size, keyword },
    });

    return response.data;
};

// 상세 조회: GET /boards/{id}  (조회 성공 시 서버에서 viewCount +1)
export const getBoard = async (id) => {
    const response = await api.get(`/boards/${id}`);

    return response.data;
};

// 등록: POST /boards  (로그인 필요)
export const createBoard = async (boardData) => {
    const response = await api.post("/boards", boardData);

    return response.data;
};

// 전체 수정: PUT /boards/{id}  (title, writer, content 모두 전달 / 로그인 필요)
export const updateBoard = async ({ id, boardData }) => {
    const response = await api.put(`/boards/${id}`, boardData);

    return response.data;
};

// 삭제: DELETE /boards/{id}  (로그인 필요)
export const deleteBoard = async (id) => {
    const response = await api.delete(`/boards/${id}`);

    return response.data;
};
