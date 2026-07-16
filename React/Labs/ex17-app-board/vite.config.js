import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,   // localhost:3000 으로 접속 (CRA 와 동일하게)
    open: true,   // npm start 하면 브라우저 자동으로 열기
  },
})
