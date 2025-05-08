import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/v1";

// Axios 인스턴스 생성
const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        "Content-Type": "application/json",
    },
});

// 요청 인터셉터: JWT 토큰 자동 포함
api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("token");
        if (token) {
            config.headers["Authorization"] = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// 응답 인터셉터: 에러 핸들링
api.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        // 인증 에러 처리 (401)
        if (error.response?.status === 401) {
            console.warn("인증이 만료되었습니다. 로그인 페이지로 이동합니다.");
            localStorage.removeItem("token");
            window.location.href = "/login";
        }

        return Promise.reject(error);
    }
);

export default api;
