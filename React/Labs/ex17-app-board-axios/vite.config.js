import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3001,   // ex17-board(3000)과 동시에 켤 수 있게 3001 사용
    open: true,   // npm start 하면 브라우저 자동으로 열기
  },
})
