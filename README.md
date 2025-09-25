# 🏫 SCHMA — CBC School Management System

**SCHMA** is a **microservice-based school management system** built to support the **Competency-Based Curriculum (CBC)** model.  
It provides APIs and a frontend interface for **students, staff, and administrators** to interact seamlessly.  

The system focuses on **user management, curriculum tracking, assessments, analytics, and communication** within schools.

---

## 📋 Table of Contents
- [About](#about)  
- [Features](#features)  
- [Architecture](#architecture)  
- [Tech Stack](#tech-stack)  
- [Setup & Installation](#setup--installation)  
- [Usage](#usage)  
- [API Endpoints](#api-endpoints)  
- [Screenshots](#screenshots)  
- [Contributing](#contributing)  
- [License](#license)  
- [Contact](#contact)  

---

## 🏷 About

SCHMA is designed for **modern schools adopting CBC**.  
It integrates core services such as **user accounts, student records, assessment tracking, analytics dashboards, and communication tools**.  

The microservice design ensures **scalability, flexibility, and reliability** for institutions of any size.

---

## ✨ Features

- 👩‍🏫 **Teacher & Student Management**  
- 📝 **Assessment & Reporting** (continuous, formative, summative)  
- 🔐 **Role-Based Access Control** (admins, teachers, parents, students)  
- 🌐 **REST APIs** for integration with other systems  
- 📨 **Messaging & Notifications** (RabbitMQ )  

---

## 🏗 Architecture

The system follows a **microservices architecture**:

| Service                  | Purpose |
|---------------------------|---------|
| **Eureka Server**         | Service discovery / registry |
| **API Gateway**           | Entry point for all requests |
| **User Service**          | Manages authentication, roles, profiles |
| **Student Service**       | Manages student records, CBC profiles |
| **Staff Service**         | Manages staff data & workloads |
| **Assessment Service**    | Handles assessments, grading & reporting |
| **Analytics Service**     | Provides dashboards & performance metrics |
| **Notification Service**  | Sends messages, alerts, and parent updates |
| **Frontend (Vuejs)**      | Web UI for staff, students & admins |

**Optional Infrastructure Services**:
- **RabbitMQ** → async messaging  
- **Zipkin** → distributed tracing  

---

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot, Spring Security, Spring Data and JDBC
- **Frontend:** Vue.js  
- **Database:** MySQL 
- **Messaging:** RabbitMQ 
- **Tracing:** Zipkin 
- **Build & Deployment:** Maven, Docker  

---

## 🚀 Setup & Installation

### 1. Clone the repository
```bash
git clone https://github.com/sololemons/SCHMA.git
cd SCHMA
