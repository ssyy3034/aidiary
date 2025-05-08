import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Signup from "./pages/Signup";
import Login from "./pages/Login";
import HomePage from "./pages/HomePage";
import PersonaCreate from "./pages/PersonaCreate.jsx";

const App = () => {
    // JWT 토큰이 있는지 여부로 로그인 여부 판단
    const isAuthenticated = !!localStorage.getItem("token");

    return (
        <Router>
            <div className="min-h-screen bg-gray-100 flex flex-col items-center justify-center p-6">
                <h1 className="text-3xl font-bold mb-6">Mami Diary</h1>

                <Routes>
                    {/* 초기 화면을 로그인 페이지로 리디렉션 */}
                    <Route path="/" element={<Navigate to={isAuthenticated ? "/home" : "/login"} />} />

                    {/* 로그인 / 회원가입 */}
                    <Route path="/login" element={<Login />} />
                    <Route path="/signup" element={<Signup />} />

                    {/* 페르소나 생성 페이지 */}
                    <Route path="/persona/create" element={isAuthenticated ? <PersonaCreate /> : <Navigate to="/login" />} />

                    {/* 메인 페이지 (페르소나 여부 체크 포함) */}
                    <Route path="/home" element={isAuthenticated ? <HomePage /> : <Navigate to="/login" />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;
