import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api";

const HomePage = () => {
    const [persona, setPersona] = useState(null);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const checkPersona = async () => {
            try {
                const response = await api.get("/persona/check");
                const data = response.data;

                if (data.hasPersona) {
                    setPersona(data);
                } else {
                    navigate("/persona/create");
                }
            } catch (err) {
                console.error("페르소나 체크 에러:", err);
                navigate("/login");
            } finally {
                setLoading(false);
            }
        };

        checkPersona();
    }, [navigate]);

    if (loading) {
        return <div className="flex justify-center items-center min-h-screen">로딩 중...</div>;
    }

    return (
        <div className="bg-gray-100 min-h-screen flex justify-center items-center p-4">
            {persona ? (
                <div className="bg-white p-8 rounded-lg shadow-lg w-96">
                    <h2 className="text-2xl font-bold mb-4 text-gray-800">
                        {persona.personaName}와의 대화
                    </h2>
                    <div className="mb-4 flex justify-center">
                        <img
                            src={persona.personaImage}
                            alt="페르소나"
                            className="w-32 h-32 rounded-full border-2 border-gray-300"
                        />
                    </div>
                    <div className="text-center mb-4 text-gray-600">
                        {persona.personaTraits.join(", ")}
                    </div>
                    <button className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded w-full">
                        대화하기
                    </button>
                </div>
            ) : (
                <div>페르소나를 생성 중입니다...</div>
            )}
        </div>
    );
};

export default HomePage;
