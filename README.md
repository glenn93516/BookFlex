# SSAFY - 삼성전자 네트워크 사업부 (넷싸 프로젝트)

> 프로젝트 주제 : 머신러닝을 활용한 리뷰 분석 시스템 개발  
> 서비스 명 : 북플렉스  
> 팀명 : 앙상블  
> 개발기간 : 2021.02.22 ~ 20201.04.07 (7주)  
> 배포 주소 : https://j4f004.p.ssafy.io

# 📚[북플렉스](https://j4f004.p.ssafy.io)란? (서비스 개요)

설명

# 👨‍👩‍👧‍👦팀원 소개

|     이름      |            역할             |                  Contact                  |
| :-----------: | :-------------------------: | :---------------------------------------: |
| 곽온겸 (팀장) |      FE, ML 모델 개발       |  [Github](https://github.com/AntBean94)   |
|    김민혁     | BE, DevOps, 추천서비스 개발 |  [Github](https://github.com/glenn93516)  |
|    우희정     |    FE, BE, ML 모델 개발     |  [Github](https://github.com/hjmwoo1208)  |
|    이강림     |    FE, BE, ML 모델 개발     |  [Github](https://github.com/leekangrim)  |
|    조명희     |          기획, FE           | [Github](https://github.com/Jo-Myounghee) |

# 📁프로젝트 구조

## 📃문서

📃[컨벤션](http://#)  
📃[깃 브랜치 전략](http://#)  
📃[회의록](http://#)

## 📁기술 스택

|   분류    |                 기술                  |
| :-------: | :-----------------------------------: |
| Front-end |                  Vue                  |
| Back-end  | Java8, Python 3.7, Spring Boot, Flask |
|    DB     |              MySQL 8.021              |
|   CI/CD   |            Docker, Jenkins            |

## 📁시스템 아키텍쳐

![시스템구조](Document/system_architecture/system_arch.png)

## 📁ERD

![ERD](Document/DB/ERD_message_v2.1.png)

## 📁패키지 구조

### 백엔드

`spring boot`

```bash
backend
├─ .gitignore
├─ build.gradle
├─ ...
└─ src
   ├─ main
   │  ├─ java
   │  │   └─ backend
   │  │       ├─ controller
   │  │       │    ├─  ABCController.java
   │  │       │    └─  ...
   │  │       ├─ service
   │  │       │    ├─  (Interface) ABCService.java
   │  │       │    ├─  ABCServiceImpl.java
   │  │       │    └─  ...
   │  │       ├─ mapper
   │  │       │    ├─ ABCMapper.java
   │  │       │    └─ ...
   │  │       ├─ dto
   │  │       ├─ config       설정 파일 (swagger, security, ...)
   │  │       │    ├─  security
   │  │       │    └─  ...
   │  │       ├─ exception
   │  │       ├─ utils
   │  │       ├─ interceptor
   │  │       └─ BackendApplication.java
   │  └─ resources
   │     ├─ mappers           Mybatis mapper 폴더
   │     │  └─ abc.xml
   │     ├─ application.yml   설정 파일
   │     ├─ mybatis-config.xml mybatis 설정 파일
   │     └─ ...
	 └─ test                    테스트 파일
```

`flask`

```bash
flask
├─ app.py
├─ Dockerfile
├─ requirements.txt
├─ apis    : API Endpoints
│    ├─  recommendation.py
│    └─  ...
├─ models  : ORM 모델들
│    ├─  Book.py
│    ├─  Genre.py
│    └─  ...
└─ data    : 필요한 데이터들 (saved model, ...)
    ├─ lda
    └─ ...
```

### 프론트 엔드

```bash
frontend
├─ Dockerfile
├─ ...
└─ src
    ├─  assets      : 로고, 이미지 파일
    │      └─ ...
    ├─  components  : 컴포넌트
    │      ├─ Book
    │      └─ ...
    ├─  router      : 라우팅
    │      └─ index.js
    ├─  store       : vuex
    │      └─ index.js
    ├─  views       : 페이지
    │      └─ ...
    ├─  App.vue
    └─  main.js

```

# 📚주요 기능

기능 설명 + GIF 파일

# 🏆수상

- 베스트 팀 🏅
- 베스트 멤버 (곽온겸) 🏅
