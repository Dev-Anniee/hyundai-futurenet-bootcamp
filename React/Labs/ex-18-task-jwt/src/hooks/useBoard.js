import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";

import {
    getBoards,
    getBoard,
    createBoard,
    updateBoard,
    deleteBoard,
} from "../services/boardService.js";

// 목록 조회 훅: page/size/keyword 가 바뀌면 자동으로 다시 조회
export const useBoards = ({ page, size, keyword }) => {
    return useQuery({
        queryKey: ["boards", page, size, keyword],
        queryFn: () => getBoards({ page, size, keyword }),
        // 페이지 이동 시 이전 데이터를 잠깐 유지해 화면 깜빡임을 줄임
        placeholderData: (previousData) => previousData,
    });
};

// 상세 조회 훅
export const useBoard = (id) => {
    return useQuery({
        queryKey: ["board", id],
        queryFn: () => getBoard(id),
        enabled: Boolean(id),
    });
};

// 등록 훅
export const useCreateBoard = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: createBoard,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ["boards"] });
        },
    });
};

// 수정 훅
export const useUpdateBoard = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: updateBoard,
        onSuccess: (_, variables) => {
            queryClient.invalidateQueries({ queryKey: ["boards"] });
            queryClient.invalidateQueries({
                queryKey: ["board", variables.id],
            });
        },
    });
};

// 삭제 훅
export const useDeleteBoard = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: deleteBoard,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ["boards"] });
        },
    });
};
