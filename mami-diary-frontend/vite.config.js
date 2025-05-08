import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  base: "./",  // 상대 경로 설정
  build: {
    outDir: "dist",
    rollupOptions: {
      input: "./index.html",  // 메인 엔트리 포인트 지정
    },
  },
  server: {
    open: true,
    port: 4173,
    strictPort: true,
  },
});
