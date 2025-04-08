# Aidiary 프로젝트

## 프로젝트 개요
Aidiary는 산모의 정서 관리와 가족 유대 형성을 지원하는 AI 기반 일기 서비스입니다.
주요 목표는 감정 기반 일기 작성, AI 조언 제공, 부모의 얼굴과 초음파 정보를 기반으로 한 아이 캐릭터 생성 및 정서적 상호작용을 통해 임신 중 산모의 정서적 안정과 가족 유대 강화를 돕는 것입니다.

## 주요 기능
- 회원가입 및 로그인 (JWT 기반 인증)
- 사용자 정보 조회, 수정, 삭제 (CRUD)
- 감정 기반 일기 작성 및 감정 분석
- AI 조언 제공
- 얼굴, 초음파 이미지 업로드 및 AI 캐릭터 생성
- 캐릭터와 정서적 상호작용

## 기술 스택
- 백엔드: Java, Spring Boot, JPA, Hibernate
- 데이터베이스: MariaDB
- 프론트엔드: React, Vite, Axios
- AI: OpenAI API 사용
- 인증: JWT
- 배포: Docker, EC2

## 프로젝트 구조
```
Aidiary/
├── src/
│   ├── main/
│   │   ├── java/org/aidiary/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── entity/
│   │   │   ├── dto/
│   │   │   ├── config/
│   │   └── resources/
├── docker/
├── scripts/
└── README.md
```

## API 명세
- `/api/v1/auth/register`: 회원가입
- `/api/v1/auth/login`: 로그인
- `/api/v1/user/info`: 사용자 정보 조회
- `/api/v1/user/update`: 사용자 정보 수정
- `/api/v1/user/delete`: 회원 탈퇴
- `/api/v1/file/upload`: 파일 업로드

## 실행 방법
1. Docker Compose로 서버 실행
2. Postman으로 API 테스트

## 개발 일정
- 1~2주차: 요구사항 분석 및 회원가입/로그인 구현
- 3~4주차: 감정 분석 일기 작성 및 캐릭터 생성
- 5~6주차: AI 조언 기능 및 상호작용 구현
- 7주차: 통합 테스트 및 배포


---
## 프론트엔드 & 백엔드 구현 계획 ##

 ✅ **현재까지 구현 완료된 기능 (백엔드)**

1. **회원 관리 기능**
   - 회원가입 API (POST /api/v1/auth/register)
   - 로그인 API (POST /api/v1/auth/login)
   - 사용자 정보 조회 API (GET /api/v1/user/info)
   - 사용자 정보 수정 API (PUT /api/v1/user/update)
   - 사용자 삭제 API (DELETE /api/v1/user/delete)
   - JWT 토큰 발급 및 검증

2. **파일 업로드 기능**
   - 파일 업로드 API (POST /api/v1/file/upload)

3. **공통 처리**
   - 공통 예외 처리 핸들러
   - JWT 인증 필터
   - Spring Security 설정 (JWT 기반)

---

#### 🚀 **구현 예정 기능**

##### 🔧 **백엔드**
1. **감정 분석 일기 기능**
   - 일기 작성 (POST /api/v1/diary/write)
   - 일기 조회 (GET /api/v1/diary/list)
   - 일기 수정 (PUT /api/v1/diary/update)
   - 일기 삭제 (DELETE /api/v1/diary/delete)
   - 감정 분석 API 연동 (OpenAI 사용)

2. **AI 페르소나 생성**
   - 부모 얼굴 & 초음파 사진 업로드
   - AI 기반 아이 캐릭터 생성
   - 정서적 상호작용 기능 구현

3. **일기 분석 리포트**
   - 월간 감정 요약 API (GET /api/v1/report/monthly)
   - 특정 기간 감정 분석 결과 (GET /api/v1/report/period)

---

##### 💻 **프론트엔드**
1. **회원 관리 페이지**
   - 회원가입 / 로그인 페이지
   - 사용자 정보 페이지 (정보 수정, 삭제)
   - JWT 토큰 저장 및 활용 (로컬스토리지 또는 세션)

2. **일기 작성 및 조회 페이지**
   - 일기 작성 폼 (텍스트 입력, 감정 분석 결과 표시)
   - 일기 목록 조회 (감정 태그와 함께 표시)
   - 일기 수정 / 삭제 기능

3. **페르소나 생성 및 상호작용 페이지**
   - 얼굴 / 초음파 사진 업로드 페이지
   - AI 페르소나 캐릭터 생성 결과 페이지
   - 캐릭터와 정서적 대화 페이지

4. **감정 리포트 페이지**
   - 월간 감정 요약 그래프
   - 특정 기간 감정 변화 차트
   - 긍정 / 부정 감정 비율 표시

---

#### 🗺️ **분업 제안**

- **백엔드**  
  - API 구현 (회원 관리, 감정 분석, 페르소나 생성)  
  - OpenAI API 연동  
  - 파일 업로드 및 저장 로직  
  - JWT 인증 로직 개선 및 리프레시 토큰 추가  

- **프론트엔드**  
  - 회원 관리 페이지 개발  
  - 일기 작성 / 조회 / 수정 / 삭제 페이지 개발  
  - 페르소나 생성 및 상호작용 페이지 개발  
  - 감정 리포트 페이지 개발  
  - API 연동 및 테스트  

---

#### ⚙️ **진행 방식**
1. **API 개발 후 문서화** (Swagger 사용 권장)  
2. **프론트엔드와 API 연동 테스트**  
3. **기능 단위로 완성 시마다 GitHub에 커밋**  
4. **주간 진행 상황 점검 및 코드 리뷰**  


