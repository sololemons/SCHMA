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
- [Usage](#-usage)
- [API Documentation](#-api-documentation)
- [Screenshots](#-screenshots)
- [Contributing](#-contributing)
- [License](#-license)
- [Support](#-support)

## 🎯 About

SCHMA (School Management System) is a comprehensive, microservice-based platform designed specifically for modern educational institutions implementing Kenya's Competency-Based Curriculum (CBC). Built with scalability and flexibility in mind, SCHMA provides a complete solution for managing students, staff, assessments, analytics, and communication within educational institutions.

### Why SCHMA?

- **CBC-Compliant**: Built from the ground up to support Kenya's Competency-Based Curriculum requirements
- **Microservice Architecture**: Scalable, maintainable, and flexible system design
- **Role-Based Access**: Secure access control for administrators, teachers, parents, and students
- **Real-Time Communication**: Integrated messaging and notification systems
- **Comprehensive Analytics**: Data-driven insights for better educational outcomes

## ✨ Features

### 👥 User Management
- **Multi-role Support**: Administrators, teachers, parents, and students
- **Secure Authentication**: JWT-based authentication with Spring Security
- **Profile Management**: Comprehensive user profiles with photo uploads
- **Role-based Permissions**: Granular access control system

### 📚 Academic Management
- **CBC Integration**: Full support for Competency-Based Curriculum
- **Student Records**: Complete academic and personal records management
- **Staff Management**: Teacher profiles, qualifications, and workload tracking
- **Class Management**: Dynamic class creation and student assignment

### 📊 Assessment & Reporting
- **Multiple Assessment Types**: Continuous, formative, and summative assessments
- **CBC Grading System**: Competency-based grading and reporting
- **Progress Tracking**: Real-time student progress monitoring
- **Report Generation**: Automated report cards and academic transcripts
- **Parent Portals**: Real-time access to student performance

### 📈 Analytics & Insights
- **Performance Dashboards**: Visual analytics for administrators and teachers
- **Trend Analysis**: Academic performance trends and insights
- **Custom Reports**: Flexible reporting system with export capabilities
- **Data Visualization**: Interactive charts and graphs

### 💬 Communication
- **Messaging System**: Internal messaging between users
- **Notifications**: Real-time alerts and updates
- **Parent Communication**: Direct communication channels with parents
- **Announcements**: School-wide announcements and notices

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
          │User       │  │Student  │  │Staff     │
          │Service    │  │Service  │  │Service   │
          └───────────┘  └─────────┘  └──────────┘
                    │           │           │
          ┌─────────▼─┐  ┌──────▼──┐  ┌────▼─────┐
          │Assessment │  │Analytics│  │Notification│
          │Service    │  │Service  │  │Service   │
          └───────────┘  └─────────┘  └──────────┘
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
| **User Service** | 8081 | Authentication, authorization, user profiles | Spring Boot + Security |
| **Student Service** | 8082 | Student records, CBC profiles, enrollment | Spring Boot + JPA |
| **Staff Service** | 8083 | Staff management, qualifications, schedules | Spring Boot + JPA |
| **Assessment Service** | 8084 | Assessments, grading, CBC evaluation | Spring Boot + JPA |
| **Analytics Service** | 8085 | Dashboards, reports, performance metrics | Spring Boot + Analytics |
| **Notification Service** | 8086 | Messaging, alerts, parent communications | Spring Boot + RabbitMQ |
| **Frontend** | 3000 | Web interface for all user types | Vue.js + Router |

### Infrastructure Services

- **RabbitMQ**: Asynchronous messaging between services
- **Zipkin**: Distributed tracing and monitoring
- **MySQL**: Primary data store for all services
- **Docker**: Containerization for easy deployment

## 💻 Tech Stack

### Backend
- **Java 11+**: Core programming language
- **Spring Boot 2.7+**: Application framework
- **Spring Cloud**: Microservices framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Data persistence layer
- **MySQL**: Primary database
- **RabbitMQ**: Message broker
- **Maven**: Build tool and dependency management

### Frontend
- **Vue.js 3**: Progressive JavaScript framework
- **Vue Router**: Client-side routing
- **Vuex**: State management
- **Axios**: HTTP client for API calls
- **Bootstrap/Tailwind**: UI styling framework

### DevOps & Monitoring
- **Docker**: Containerization
- **Docker Compose**: Multi-container orchestration
- **Zipkin**: Distributed tracing
- **Maven**: Build automation
- **Git**: Version control

## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed:
- **Java 11 or higher**
- **Maven 3.6+**
- **Node.js 14+ and npm**
- **MySQL 8.0+**
- **Docker & Docker Compose** (optional but recommended)
- **Git**

### Quick Start with Docker

1. **Clone the repository**
   ```bash
   git clone https://github.com/sololemons/SCHMA.git
   cd SCHMA
   ```

2. **Start all services using Docker Compose**
   ```bash
   docker-compose up -d
   ```

3. **Access the application**
   - Frontend: http://localhost:3000
   - API Gateway: http://localhost:8080
   - Eureka Dashboard: http://localhost:8761

### Manual Setup

#### 1. Database Setup
```sql
CREATE DATABASE schma_users;
CREATE DATABASE schma_students; 
CREATE DATABASE schma_staff;
CREATE DATABASE schma_assessments;
CREATE DATABASE schma_analytics;
CREATE DATABASE schma_notifications;

-- Create a user for the application
CREATE USER 'schma_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON schma_*.* TO 'schma_user'@'localhost';
FLUSH PRIVILEGES;
```

#### 2. Start Infrastructure Services

**Start RabbitMQ:**
```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

**Start Zipkin (optional):**
```bash
docker run -d --name zipkin -p 9411:9411 openzipkin/zipkin
```

#### 3. Start Backend Services

Start services in the following order:

```bash
# 1. Start Eureka Server
cd eureka-server
mvn spring-boot:run

# 2. Start API Gateway (in a new terminal)
cd ../api-gateway
mvn spring-boot:run

# 3. Start all microservices (each in a new terminal)
cd ../user-service && mvn spring-boot:run
cd ../student-service && mvn spring-boot:run
cd ../staff-service && mvn spring-boot:run
cd ../assessment-service && mvn spring-boot:run
cd ../analytics-service && mvn spring-boot:run
cd ../notification-service && mvn spring-boot:run
```

#### 4. Start Frontend

```bash
cd frontend
npm install
npm run serve
```

### Configuration

Update the `application.yml` files in each service with your specific configuration:

```yaml
# Example configuration for user-service
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/schma_users
    username: schma_user
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver
  
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

## 📖 Usage

### Default User Accounts

After setup, you can login with these default accounts:

| Role | Email | Password | Access Level |
|------|-------|----------|--------------|
| **System Admin** | admin@schma.co.ke | admin123 | Full system access |
| **Principal** | principal@school.ac.ke | principal123 | School management |
| **Teacher** | teacher@school.ac.ke | teacher123 | Class and student management |
| **Parent** | parent@email.com | parent123 | Student progress access |
| **Student** | student@school.ac.ke | student123 | Personal dashboard access |

### Key Workflows

#### 1. Student Enrollment
1. Admin/Principal creates student account
2. System generates admission number
3. Student assigned to appropriate class/grade
4. CBC profile automatically created
5. Parent account linked (if provided)

#### 2. Assessment Management
1. Teacher creates assessment (formative/summative)
2. Define competencies and learning outcomes
3. Students complete assessments
4. Teacher grades using CBC rubrics
5. System generates progress reports
6. Parents receive notifications

#### 3. CBC Report Generation
1. System compiles student performance data
2. Maps scores to CBC competency levels
3. Generates comprehensive reports
4. Distributes reports to parents/guardians
5. Archives reports for historical tracking

## 📚 API Documentation

### Base URLs
- **Production**: `https://api.schma.co.ke`
- **Development**: `http://localhost:8080`

### Authentication
All API requests require authentication via JWT tokens:

```bash
# Login to get token
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@schma.co.ke","password":"admin123"}'

# Use token in subsequent requests
curl -X GET http://localhost:8080/api/students \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Key Endpoints

#### User Management
```bash
POST   /api/auth/login              # User login
POST   /api/auth/register           # User registration
GET    /api/users                   # Get all users
GET    /api/users/{id}              # Get user by ID
PUT    /api/users/{id}              # Update user
DELETE /api/users/{id}              # Delete user
```

#### Student Management
```bash
GET    /api/students                # Get all students
POST   /api/students                # Create new student
GET    /api/students/{id}           # Get student details
PUT    /api/students/{id}           # Update student
GET    /api/students/{id}/grades    # Get student grades
GET    /api/students/{id}/reports   # Get student reports
```

#### Assessment Management
```bash
GET    /api/assessments             # Get all assessments
POST   /api/assessments             # Create assessment
GET    /api/assessments/{id}        # Get assessment details
PUT    /api/assessments/{id}        # Update assessment
POST   /api/assessments/{id}/grade  # Submit grades
```

### API Response Format

All API responses follow this structure:

```json
{
  "success": true,
  "message": "Operation successful",
  "data": {
    // Response data here
  },
  "timestamp": "2025-01-15T10:30:00Z",
  "errors": [] // Only present if success is false
}
```

For complete API documentation, visit: `http://localhost:8080/swagger-ui.html`

## 📱 Screenshots

### Dashboard Views
- **Admin Dashboard**: Complete system overview with analytics
- **Teacher Dashboard**: Class management and student progress
- **Parent Portal**: Real-time student performance tracking
- **Student Portal**: Personal academic dashboard

### Key Features
- **CBC Report Cards**: Competency-based progress reports
- **Assessment Interface**: User-friendly grading system
- **Analytics Dashboard**: Visual performance metrics
- **Communication Hub**: Integrated messaging system

*Screenshots will be added in the next release*

## 🤝 Contributing

We welcome contributions from the community! Here's how you can help:

### Getting Started
1. **Fork the repository**
2. **Create a feature branch**: `git checkout -b feature/amazing-feature`
3. **Make your changes**
4. **Test thoroughly**
5. **Commit your changes**: `git commit -m 'Add amazing feature'`
6. **Push to the branch**: `git push origin feature/amazing-feature`
7. **Create a Pull Request**

### Contribution Guidelines
- Follow Java/Spring Boot best practices
- Write comprehensive tests for new features
- Update documentation for any API changes
- Follow conventional commit message format
- Ensure code passes all existing tests

### Areas for Contribution
- 🐛 Bug fixes
- ✨ New features
- 📚 Documentation improvements
- 🧪 Test coverage improvements
- 🎨 UI/UX enhancements
- 🌍 Localization/Internationalization

### Code Style
- **Java**: Follow Google Java Style Guide
- **JavaScript**: Use ESLint with Airbnb configuration
- **Vue.js**: Follow Vue.js Style Guide
- **Commit Messages**: Use Conventional Commits format

## 🔒 Security

Security is a top priority for SCHMA. We implement:

- **JWT Authentication**: Secure token-based authentication
- **Role-Based Access Control**: Granular permissions system
- **Data Encryption**: Sensitive data encryption at rest and in transit
- **Input Validation**: Comprehensive input sanitization
- **SQL Injection Protection**: Prepared statements and ORM
- **CORS Configuration**: Proper cross-origin resource sharing setup

### Reporting Security Issues
If you discover a security vulnerability, please email: security@schma.co.ke

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
- **Email**: support@schma.co.ke
- **Website**: [www.schma.co.ke](https://www.schma.co.ke)

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
