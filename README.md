# Portfolio Management System (Full Stack)

A complete production-ready portfolio ecosystem that enables dynamic content management using a secure Android Admin App and displays it on a modern responsive web application.

---

## Project Overview

This is a Full Stack Portfolio Management System designed with a scalable and modular architecture.

The system consists of:

* Portfolio Website (Frontend – HTML, CSS, JavaScript)
* Android Admin App (Kotlin, MVVM)
* Spring Boot Backend API (JWT Secured)

The system allows real-time updates where any change from the Admin App is instantly reflected on the Website.

---

## Core Features

* JWT + OTP based Admin Authentication
* Android Admin Panel (MVVM Architecture)
* Dynamic API-driven Portfolio Website
* Cloud Deployment (AWS EC2 + RDS)
* Image & PDF Upload Support
* SMTP Email Service (OTP + Contact Form)
* Real-time Data Sync
* Scalable 3-Tier Architecture

---

## System Architecture

```id="archbig001"
                     ANDROID ADMIN APPLICATION
                   (Kotlin + MVVM + Retrofit)
                                │
                                │
                                ▼
                  SPRING BOOT BACKEND APPLICATION
        (REST API, JWT Authentication, Spring Security)
                                │
        ┌───────────────────────┼───────────────────────┐
        │                       │                       │
        ▼                       ▼                       ▼
   AUTH SERVICE           BUSINESS LOGIC           FILE SERVICE
 (OTP + JWT)        (Skills, Projects, Resume)     (Uploads)
        │                       │                       │
        └───────────────┬───────┴───────────────┬───────┘
                        │                       │
                        ▼                       ▼
                 MYSQL DATABASE           FILE STORAGE
                    (AWS RDS)          (/uploads directory)
                        │
                        │
                        ▼
                 PUBLIC REST APIs (/api/usr/*)
                        │
                        ▼
              PORTFOLIO WEB APPLICATION
             (HTML, CSS, JavaScript)
                        │
                        ▼
                     END USER
```

---

## Tech Stack

### Frontend

* HTML5, CSS3, JavaScript

### Backend

* Spring Boot
* Spring Security
* JWT Authentication
* REST APIs
* Hibernate / JPA

### Mobile App

* Kotlin
* Jetpack Compose
* MVVM Architecture
* Retrofit

### Database

* MySQL (AWS RDS)

### Cloud & DevOps

* AWS EC2
* AWS RDS
* Nginx
* Ubuntu Linux

### Other

* SMTP (Email Service)
* File Upload System

---

## Frontend (Portfolio Website)

### Structure

```id="web001"
portfolio-web
│
├── index.html
├── certificate.html
│
├── assets
│
│   ├── css
│   │   ├── colors.css
│   │   ├── base.css
│   │   ├── layout.css
│   │   ├── components.css
│   │   ├── sections.css
│   │   └── responsive.css
│   │
│   ├── js
│   │   ├── main.js
│   │   ├── navigation.js
│   │   ├── theme.js
│   │   ├── skills.js
│   │   ├── projects.js
│   │   ├── contact.js
│   │   └── api.js
│   │
│   ├── images
│   │   ├── banner
│   │   ├── profile
│   │   ├── projects
│   │   └── icons
│   │
│   └── resume
│       └── resume.pdfe
```

### Features

* Fully responsive (Mobile / Tablet / Desktop)
* Dynamic API-based content rendering
* Resume download
* Contact form integration
* Smooth navigation

### Data Flow

```id="webflow001"
Browser → API → Backend → Database
        ← JSON Response ←
        → UI Render
```

---

## Backend (Spring Boot)

### Architecture

```id="backend001"
Controller → Service → Repository → Database
```

```id="backend001"
portfolio-backend
│
├── src
│   │
│   ├── main
│   │   │
│   │   ├── java
│   │   │   │
│   │   │   └── com
│   │   │       │
│   │   │       └── abhishekyadav
│   │   │           │
│   │   │           └── portfolio_backend
│   │   │               │
│   │   │               ├── PortfolioBackendApplication.java
│   │   │               │
│   │   │               ├── adi
│   │   │               │   │
│   │   │               │   ├── controller
│   │   │               │   │   │
│   │   │               │   │   ├── AdminAuthController.java
│   │   │               │   │   ├── SkillAdminController.java
│   │   │               │   │   ├── ProjectAdminController.java
│   │   │               │   │   ├── ResumeAdminController.java
│   │   │               │   │   └── ContactAdminController.java
│   │   │               │   │
│   │   │               │   ├── service
│   │   │               │   │   │
│   │   │               │   │   ├── AdminAuthService.java
│   │   │               │   │   ├── SkillAdminService.java
│   │   │               │   │   ├── ProjectAdminService.java
│   │   │               │   │   ├── ResumeAdminService.java
│   │   │               │   │   └── ContactAdminService.java
│   │   │               │   │
│   │   │               │   └── service
│   │   │               │       └── impl
│   │   │               │           │
│   │   │               │           ├── AdminAuthServiceImpl.java
│   │   │               │           ├── SkillAdminServiceImpl.java
│   │   │               │           ├── ProjectAdminServiceImpl.java
│   │   │               │           ├── ResumeAdminServiceImpl.java
│   │   │               │           └── ContactAdminServiceImpl.java
│   │   │               │
│   │   │               ├── usr
│   │   │               │   │
│   │   │               │   ├── controller
│   │   │               │   │   │
│   │   │               │   │   ├── SkillUserController.java
│   │   │               │   │   ├── ProjectUserController.java
│   │   │               │   │   ├── ResumeUserController.java
│   │   │               │   │   └── ContactUserController.java
│   │   │               │   │
│   │   │               │   ├── service
│   │   │               │   │   │
│   │   │               │   │   ├── SkillUserService.java
│   │   │               │   │   ├── ProjectUserService.java
│   │   │               │   │   ├── ResumeUserService.java
│   │   │               │   │   └── ContactUserService.java
│   │   │               │   │
│   │   │               │   └── service
│   │   │               │       └── impl
│   │   │               │           │
│   │   │               │           ├── SkillUserServiceImpl.java
│   │   │               │           ├── ProjectUserServiceImpl.java
│   │   │               │           ├── ResumeUserServiceImpl.java
│   │   │               │           └── ContactUserServiceImpl.java
│   │   │               │
│   │   │               ├── common
│   │   │               │   │
│   │   │               │   ├── config
│   │   │               │   │   │
│   │   │               │   │   ├── CorsConfig.java
│   │   │               │   │   ├── SecurityConfig.java
│   │   │               │   │   └── FileStorageConfig.java
│   │   │               │   │
│   │   │               │   ├── dto
│   │   │               │   │   │
│   │   │               │   │   ├── SkillDto.java
│   │   │               │   │   ├── ProjectDto.java
│   │   │               │   │   ├── ResumeDto.java
│   │   │               │   │   ├── ContactMessageDto.java
│   │   │               │   │   ├── OtpRequestDto.java
│   │   │               │   │   └── OtpVerifyDto.java
│   │   │               │   │
│   │   │               │   ├── entity
│   │   │               │   │   │
│   │   │               │   │   ├── SkillEntity.java
│   │   │               │   │   ├── ProjectEntity.java
│   │   │               │   │   ├── ResumeEntity.java
│   │   │               │   │   └── ContactMessageEntity.java
│   │   │               │   │
│   │   │               │   ├── repository
│   │   │               │   │   │
│   │   │               │   │   ├── SkillRepository.java
│   │   │               │   │   ├── ProjectRepository.java
│   │   │               │   │   ├── ResumeRepository.java
│   │   │               │   │   └── ContactMessageRepository.java
│   │   │               │   │
│   │   │               │   ├── security
│   │   │               │   │   │
│   │   │               │   │   ├── jwt
│   │   │               │   │   │   │
│   │   │               │   │   │   ├── JwtUtil.java
│   │   │               │   │   │   ├── JwtFilter.java
│   │   │               │   │   │   └── JwtAuthenticationEntryPoint.java
│   │   │               │   │   │
│   │   │               │   │   └── otp
│   │   │               │   │       │
│   │   │               │   │       ├── OtpService.java
│   │   │               │   │       └── OtpStore.java
│   │   │               │   │
│   │   │               │   ├── util
│   │   │               │   │   │
│   │   │               │   │   ├── FileStorageUtil.java
│   │   │               │   │   └── ApiResponseUtil.java
│   │   │               │   │
│   │   │               │   └── exception
│   │   │               │       │
│   │   │               │       ├── GlobalExceptionHandler.java
│   │   │               │       ├── ResourceNotFoundException.java
│   │   │               │       └── UnauthorizedException.java
│   │   │               │
│   │   │               └── resources
│   │   │                   │
│   │   │                   ├── application.yml
│   │   │                   ├── application-dev.yml
│   │   │                   ├── application-prod.yml
│   │   │                   └── static
│   │   │
│   │   └── resources
│   │
│   └── test
│
├── .env
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

### Security

* JWT Authentication
* OTP-based login
* Admin vs Public API separation

### Authentication Flow

```id="auth001"
Email → OTP → Verify → JWT → Access APIs
```

---

## Complete API List

### Authentication

* POST /api/adi/auth/send-otp
* POST /api/adi/auth/verify-otp

### Skills

* GET /api/usr/skills
* POST /api/adi/skills
* DELETE /api/adi/skills/{id}

### Projects

* GET /api/usr/projects
* POST /api/adi/projects
* DELETE /api/adi/projects/{id}

### Resume

* GET /api/usr/resume
* POST /api/adi/resume
* DELETE /api/adi/resume/{id}

### Messages

* POST /api/usr/contact
* GET /api/adi/messages

---

## Database Design

Tables:

* Skills
* Projects
* Resume
* Contact Messages

Each table contains:

* ID
* CreatedAt
* Relevant fields

---

## File Storage

```id="storage001"
/uploads/projects/
/uploads/resume/
/uploads/certificates/
```

---

## Email Service (SMTP)

Used for:

* OTP Authentication
* Contact Form

Configuration:

* Host: smtp.gmail.com
* Port: 587
* TLS Enabled

---

## Android Admin App

### Architecture (MVVM)

```id="android001"
UI → ViewModel → Repository → API → Backend
```

```id="android001"
portfolio-admin-app
│
└── com.abhishekyadav.portfolioadmin
    │
    ├── MainActivity.kt
    │
    ├── data
    │   │
    │   ├── api
    │   │   ├── ApiService.kt
    │   │   └── RetrofitClient.kt
    │   │
    │   ├── model
    │   │   ├── Skill.kt
    │   │   ├── Project.kt
    │   │   ├── Resume.kt
    │   │   ├── Message.kt
    │   │   ├── AuthResponse.kt
    │   │   └── OtpRequest.kt
    │   │
    │   └── repository
    │       ├── AuthRepository.kt
    │       ├── SkillRepository.kt
    │       ├── ProjectRepository.kt
    │       ├── ResumeRepository.kt
    │       └── MessageRepository.kt
    │
    ├── ui
    │   │
    │   ├── navigation
    │   │   ├── Routes.kt
    │   │   └── AppNavGraph.kt
    │   │
    │   ├── screens
    │   │   │
    │   │   ├── splash
    │   │   │   └── SplashScreen.kt
    │   │   │
    │   │   ├── login
    │   │   │   └── LoginScreen.kt
    │   │   │
    │   │   ├── verifyotp
    │   │   │   └── VerifyOtpScreen.kt
    │   │   │
    │   │   ├── skills
    │   │   │   └── SkillsScreen.kt
    │   │   │
    │   │   ├── projects
    │   │   │   └── ProjectsScreen.kt
    │   │   │
    │   │   ├── resume
    │   │   │   └── ResumeScreen.kt
    │   │   │
    │   │   └── messages
    │   │       └── MessagesScreen.kt
    │   │
    │   └── components
    │       ├── BottomNavigationBar.kt
    │       ├── SkillItem.kt
    │       ├── ProjectItem.kt
    │       ├── ResumeItem.kt
    │       └── MessageItem.kt
    │
    ├── viewmodel
    │   ├── AuthViewModel.kt
    │   ├── SkillViewModel.kt
    │   ├── ProjectViewModel.kt
    │   ├── ResumeViewModel.kt
    │   └── MessageViewModel.kt
    │
    └── utils
        ├── TokenManager.kt
        ├── JwtInterceptor.kt
        ├── Constants.kt
        └── FileUtils.kt
```

### Features

* Secure OTP login
* JWT token handling
* Add/Delete Skills
* Add/Delete Projects
* Upload Resume
* View Messages



## Deployment

* Backend → AWS EC2
* Database → AWS RDS
* Frontend → Vercel
* Reverse Proxy → Nginx

---

## Data Flow

```id="flow001"
Admin App → Backend → Database
       ↓
Website ← Backend ← Data
```

---

## Security Features

* JWT Authentication
* OTP-based login
* Role-based API separation
* Secure file handling

---

## Project Goal

To demonstrate:

* System Design
* API Development
* Cloud Deployment
* Mobile + Web Integration

---

## Future Enhancements

* React / Next.js frontend
* Admin dashboard
* Role-based access control
* Analytics

---

## Author

Abhishek Yadav
[abhi.dev2505@gmail.com](mailto:abhi.dev2505@gmail.com)

---

## Conclusion

This project is a complete full-stack portfolio system showcasing real-world development, system design, and cloud deployment.
This project is only for self  uses.
