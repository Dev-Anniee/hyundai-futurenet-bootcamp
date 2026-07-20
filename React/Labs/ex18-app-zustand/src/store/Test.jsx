import { create } from "zustand";

const useStore = create((set) => ({
  value: "",

  setValue: (value) =>
    set({
      value: value,
    }),
}));

export default useStore;


/*
react 의 효율적인 사용
스니핏 개발의 편리성 .... zustand ...
*/