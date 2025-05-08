import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api";

const PersonaCreate = () => {
    const [personaName, setPersonaName] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleCreatePersona = async (e) => {
        e.preventDefault();
        setError("");

        try {
            const response = await api.post("/persona/create", {
                name: personaName,
            });

            console.log("페르소나 생성 성공:", response.data);
            navigate("/home");
        } catch (err) {
            console.error("페르소나 생성 에러:", err);
            setError(err.response?.data?.message || "페르소나 생성에 실패했습니다.");
        }
    };

    return (
        <div className="bg-gray-100 min-h-screen flex justify-center items-center p-4">
            <div className="bg-white p-8 rounded-lg shadow-lg w-80">
                <h2 className="text-2xl font-bold mb-4 text-gray-800">페르소나 생성</h2>

                {error && <p className="text-red-500 mb-4">{error}</p>}

                <form onSubmit={handleCreatePersona}>
                    <input
                        type="text"
                        placeholder="페르소나 이름"
                        value={personaName}
                        onChange={(e) => setPersonaName(e.target.value)}
                        className="mb-4 p-2 w-full border border-gray-300 rounded text-gray-700"
                        required
                    />
                    <button
                        type="submit"
                        className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded w-full"
                    >
                        생성하기
                    </button>
                </form>
            </div>
        </div>
    );
};

export default PersonaCreate;
