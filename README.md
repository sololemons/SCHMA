# SCHMA - School Management System

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white" alt="Vue.js">
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/RabbitMQ-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white" alt="RabbitMQ">
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker">
</div>

<p align="center">
  <strong>A modern, microservice-based school management system built specifically for Kenya's Competency-Based Curriculum (CBC)</strong>
</p>

<p align="center">
  <a href="#-features">Features</a> •
  <a href="#-architecture">Architecture</a> •
  <a href="#-getting-started">Getting Started</a> •
  <a href="#-api-documentation">API Docs</a> •
  <a href="#-contributing">Contributing</a>
</p>

## 📋 Table of Contents

- [About](#-about)
- [Features](#-features) 
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Getting Started](#-getting-started)
- [License](#-license)
- [Support](#-support)

## 🎯 About

SCHMA (School Management System) is a comprehensive, microservice-based platform designed specifically for modern educational institutions implementing Kenya's Competency-Based Curriculum (CBC). Built with scalability and flexibility in mind, SCHMA provides a complete solution for managing students, staff, assessments, analytics, and communication within educational institutions.

### Why SCHMA?

- **CBC-Compliant**: Built from the ground up to support Kenya's Competency-Based Curriculum requirements
- **Microservice Architecture**: Scalable, maintainable, and flexible system design
- **Role-Based Access**: Secure access control for administrators, teachers, and students
- **Real-Time Communication**: Integrated messaging and notification systems
- **Comprehensive Analytics**: Data-driven insights for better educational outcomes

## ✨ Features

### 👥 User Management
- **Multi-role Support**: Administrators, teachers and students
- **Secure Authentication**: JWT-based authentication with Spring Security
- **Role-based Permissions**: Granular access control system

### 📚 Academic Management
- **CBC Integration**: Full support for Competency-Based Curriculum
- **Student Records**: Complete academic and personal records management
- **Staff Management**: Teacher profiles and workload tracking
- **Class Management**: Dynamic class creation and student assignment

### 📊 Assessment & Reporting
- **Multiple Assessment Types**: Continuous, formative, and summative assessments
- **CBC Grading System**: Competency-based grading and reporting
- **Progress Tracking**: Real-time student progress monitoring
- **Report Generation**: Automated report cards and academic transcripts

### 📈 Analytics & Insights
- **Performance Dashboards**: Visual analytics for administrators and teachers

### 💬 Communication
- **Messaging System**: Internal messaging between users
- **Notifications**: Real-time alerts and updates
- **Announcements**: School-wide announcements In NoticeBoards

## 🏗️ Architecture

SCHMA follows a microservices architecture pattern with the following services:

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   Frontend      │    │   API Gateway    │    │  Eureka Server  │
│   (Vue.js)      │◄──►│  (Spring Cloud)  │◄──►│ (Service Disc.) │
└─────────────────┘    └──────────────────┘    └─────────────────┘
                                │
                    ┌───────────┼───────────┐
                    │           │           │
          ┌─────────▼─┐  ┌──────▼──┐  ┌────▼─────┐
          │Admin      │  │Student  │  │Staff     │
          │Service    │  │Service  │  │Service   │
          └───────────┘  └─────────┘  └──────────┘
                    │           │           
          ┌─────────▼─┐  ┌──────▼──   ┐ 
          │Grading │  │  │Notification
          │Service    │  │Service     │
          └───────────┘  └──────────  ┘
                                │
                    ┌───────────┼───────────┐
          ┌─────────▼─┐  ┌──────▼──┐  ┌────▼─────┐
          │MySQL      │  │RabbitMQ │  │Zipkin    │
          │Database   │  │Messaging│  │Tracing   │
          └───────────┘  └─────────┘  └──────────┘
```

### Core Services

| Service | Port | Purpose | Technology |
|---------|------|---------|------------|
| **Eureka Server** | 8761 | Service discovery and registry | Spring Cloud Netflix |
| **API Gateway** | 8080 | Single entry point, routing, load balancing | Spring Cloud Gateway |
| **Student Service** | 8081 | Student records, CBC profiles, enrollment | Spring Boot + JPA |
| **Staff Service** | 8082 | Staff management, qualifications, schedules | Spring Boot + JPA |
| **Grade Service** | 8084 | Assessments, grading, CBC evaluation | Spring Boot + JPA |
| **Notification Service** | 8085 | Messaging, alerts, parent communications | Spring Boot + RabbitMQ |
| **Frontend** | 3000 | Web interface for all user types | Vue.js + Router |

### Infrastructure Services

- **RabbitMQ**: Asynchronous messaging between services
- **Zipkin**: Distributed tracing and monitoring
- **MySQL**: Primary data store for all services
- **Docker**: Containerization for easy deployment

## 💻 Tech Stack

### Backend
- **Java 21**: Core programming language
- **Spring Boot 3.5.3+**: Application framework
- **Spring Cloud**: Microservices framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Data persistence layer
- **MySQL**: Primary database
- **RabbitMQ**: Message broker
- **Maven**: Build tool and dependency management

### Frontend
- **Vue.js 3**: Progressive JavaScript framework
- **Vue Router**: Client-side routing
- **Pinia**: State management
- **Axios**: HTTP client for API calls
- **Tailwind**: UI styling framework



## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed:
- **Java 21**
- **Maven 3.6+**
- **Node.js 14+ and npm**
- **MySQL 8.0+**
- **Docker & Docker Desktop** (recommended in running the zipkin and rabbitMQ containers )
- **Git**

### Quick Start with Docker

1. **Clone the repository**
   ```bash
   git clone https://github.com/sololemons/SCHMA.git
   cd SCHMA
   ```


### Manual Setup

#### 1. Database Setup
```sql
CREATE DATABASE studentservice;
CREATE DATABASE staffservice;
CREATE DATABASE gradeservice;
CREATE DATABASE adminservice;
CREATE DATABASE chatservice;

```

#### 2. Start Infrastructure Services

**Start RabbitMQ:**
```bash
   docker pull rabbitmq:3-management
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

**Start Zipkin (optional):**
```bash
   docker pull openzipkin/zipkin
docker run -d --name zipkin -p 9411:9411 openzipkin/zipkin
```

#### 3. Start Backend Services

Start services in the following order:

-EurekaServer
-ApiGateway
Then the rest in whatever order

#### 4. Start Frontend

```bash
cd frontend
npm install
npm run dev
```

## 🔒 Security

Security is a top priority for SCHMA. We implement:

- **JWT Authentication**: Secure token-based authentication
- **Role-Based Access Control**: Granular permissions system
- **Data Encryption**: Sensitive data encryption at rest and in transit
- **Input Validation**: Comprehensive input sanitization
- **SQL Injection Protection**: Prepared statements and ORM
- **CORS Configuration**: Proper cross-origin resource sharing setup



## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2025 SCHMA Contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software...
```

## 📞 Support

### Community Support
- **GitHub Issues**: [Create an issue](https://github.com/sololemons/SCHMA/issues)
- **Discussions**: [GitHub Discussions](https://github.com/sololemons/SCHMA/discussions)
- **Documentation**: [Wiki](https://github.com/sololemons/SCHMA/wiki)

### Professional Support
For professional support, custom development, or enterprise solutions:
- **Email**: solomonndimu75@gmail.com

### Contributors

Thanks to all contributors who have helped make SCHMA better:

<a href="https://github.com/sololemons/SCHMA/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=sololemons/SCHMA" />
</a>

---

<div align="center">
  <p><strong>Made with ❤️ for Kenyan Schools</strong></p>
  <p>If you find this project helpful, please consider giving it a ⭐️</p>
  
  <a href="https://github.com/sololemons/SCHMA/stargazers">
    <img src="https://img.shields.io/github/stars/sololemons/SCHMA?style=social" alt="GitHub Stars">
  </a>
  <a href="https://github.com/sololemons/SCHMA/network/members">
    <img src="https://img.shields.io/github/forks/sololemons/SCHMA?style=social" alt="GitHub Forks">
  </a>
</div>
