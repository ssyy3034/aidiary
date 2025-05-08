import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import "./index.css";

console.log("main.jsx 렌더링 시작");

const rootElement = document.getElementById("root");
console.log("Root element:", rootElement);

if (rootElement) {
    ReactDOM.createRoot(rootElement).render(
        <React.StrictMode>
            <App />
        </React.StrictMode>
    );
    console.log("ReactDOM 렌더링 완료");
} else {
    console.error("Root element를 찾을 수 없습니다.");
}
