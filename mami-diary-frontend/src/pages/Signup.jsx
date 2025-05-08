import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../api";

const Signup = () => {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleSignup = async (e) => {
        e.preventDefault();
        setError("");

        try {
            const response = await api.post("/auth/register", {
                name,
                email,
                password,
            });

            console.log("회원가입 성공:", response.data);
            navigate("/login");
        } catch (err) {
            console.error("회원가입 에러:", err);
            setError(err.response?.data?.message || "회원가입에 실패했습니다.");
        }
    };

    return (
        <div className="bg-gray-100 min-h-screen flex justify-center items-center p-4">
            <div className="bg-white p-8 rounded-lg shadow-lg w-80">
                <h2 className="text-2xl font-bold mb-4 text-gray-800">회원가입</h2>

                {error && <p className="text-red-500 mb-4">{error}</p>}

                <form onSubmit={handleSignup}>
                    <input
                        type="text"
                        placeholder="이름"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        className="mb-2 p-2 w-full border border-gray-300 rounded text-gray-700"
                        required
                    />
                    <input
                        type="email"
                        placeholder="이메일"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="mb-2 p-2 w-full border border-gray-300 rounded text-gray-700"
                        required
                    />
                    <input
                        type="password"
                        placeholder="비밀번호"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="mb-4 p-2 w-full border border-gray-300 rounded text-gray-700"
                        required
                    />
                    <button
                        type="submit"
                        className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded w-full mb-2"
                    >
                        회원가입
                    </button>
                </form>

                <div className="text-center">
                    <Link to="/login" className="text-blue-500 hover:underline">
                        이미 계정이 있나요? 로그인하기
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default Signup;
