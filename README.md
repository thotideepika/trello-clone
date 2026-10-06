# 🚀 Trello Clone — Full-Stack Project Management Tool

A production-grade **Trello clone** with real-time collaboration, built using **Spring Boot 3**, **React + TypeScript**, **PostgreSQL**, and **WebSockets**.

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-green?logo=springboot)
![React](https://img.shields.io/badge/React-18-blue?logo=react)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?logo=postgresql)
![License](https://img.shields.io/badge/license-MIT-green)

---

## 📖 Overview

A full-stack Kanban board application where users can create boards, organize tasks into lists, and drag-and-drop cards to track progress — all synced in real time across multiple browser tabs via WebSocket.

---

## ✨ Features

- 🔐 Secure Authentication — JWT-based login & registration with BCrypt-hashed passwords
- 📋 Board Management — Create, view, and delete project boards
- 📝 Lists & Cards — Organize work with drag-and-drop Kanban columns
- ⚡ Real-Time Sync — Multiple clients see changes instantly via WebSocket (STOMP)
- 💾 Persistent Storage — PostgreSQL with Hibernate auto-schema generation
- 🎨 Trello-Inspired UI — Clean, responsive interface

---

## 🛠️ Tech Stack

**Backend:** Java 21, Spring Boot 3.2.5, Spring Security, Spring Data JPA, Hibernate, PostgreSQL 16, JJWT, WebSocket + STOMP, Maven

**Frontend:** React 18, TypeScript 5.2, Vite 5, React Router 6, react-beautiful-dnd, Axios, SockJS + STOMP.js

---

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Maven 3.9+
- Node.js 20+
- PostgreSQL 16+

### Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/thotideepika/trello-clone.git
   cd trello-clone
Setup PostgreSQL:

sql
CREATE DATABASE trello;
CREATE USER trello WITH PASSWORD 'trello123';
GRANT ALL PRIVILEGES ON DATABASE trello TO trello;
Start the backend:

bash
cd backend
mvn spring-boot:run
Start the frontend (new terminal):

bash
cd frontend
npm install
npm run dev
Open the app:

Frontend: http://localhost:3000

Backend: http://localhost:8080

👤 Author
Deepika

GitHub: @thotideepika

Email: thotideepika2711@gmail.com

