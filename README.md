# Spring Boot Microservices E-commerce System

A fully functional e-commerce microservices project built with Spring Boot 3, Spring Cloud, Kafka, MySQL, and Docker/Kubernetes.  
This project demonstrates microservices architecture, event-driven communication, distributed transactions (Saga pattern), and resiliency patterns like Circuit Breaker.

## Table of Contents
1. [Project Overview](#project-overview)
2. [Tech Stack](#tech-stack)
3. [Architecture](#architecture)
4. [Services](#services)
5. [Setup & Run](#setup--run)
6. [Endpoints](#endpoints)
7. [Event Flows](#event-flows)
8. [Saga & Transactions](#saga--transactions)
9. [Observability](#observability)
10. [Docker & Kubernetes](#docker--kubernetes)
11. [Testing](#testing)
12. [License](#license)

## Project Overview
This project simulates an e-commerce system using microservices architecture. 
It includes services for Orders, Payments, Inventory management, and Notifications. 
The system demonstrates event-driven communication, distributed transactions (Saga pattern), and resiliency patterns like Circuit Breaker.

## Tech Stack
- **Backend:** Spring Boot 3, Spring Cloud, Spring Security, Spring Data JPA
- **Database:** MySQL (Docker), PostgreSQL optional
- **Messaging/Event:** Kafka, JMS (ActiveMQ)
- **Microservices Patterns:** Gateway, Eureka, Circuit Breaker, Saga, Distributed Tracing
- **Build & Dependency:** Maven + BOM
- **Containerization:** Docker, Kubernetes
- **Mapping & Utilities:** MapStruct, Lombok

## Architecture
The system follows a microservices architecture:

- **OrderService:** Handles order creation and status
- **PaymentService:** Processes payments and updates order status
- **InventoryService:** Manages stock and updates after purchase
- **NotificationService:** Sends email/SMS notifications

![](diagrams/architecture.png)

## Services
- **OrderService:** `/orders` - CRUD for orders
- **PaymentService:** `/payments` - Process payments
- **InventoryService:** `/inventory` - Check and update stock
- **NotificationService:** `/notifications` - Send notifications

## Setup & Run

### Prerequisites
- Java 17+
- Maven 3.8+
- Docker & Docker Compose
- Kubernetes (Minikube/Kind) optional

### Steps
1. Clone the repository:
   ```bash
   git clone <repo-url>
   cd springboot-microservices-ecommerce
