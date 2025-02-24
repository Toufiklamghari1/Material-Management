# Material Management System

A comprehensive resource management solution for educational institutions handling equipment tracking, maintenance, and procurement.

## Features
    - **Role-Based Access Control**: Multiple user roles (Admin, Teacher, Technician, Supplier)
    - **Resource Management**: Track computers, printers, and other equipment
    - **Maintenance Workflow**: Panne reporting → Technical analysis → Repair tracking
    - **Procurement System**: Manage bids and supplier submissions
    - **Notification System**: Real-time updates for important events
    - **Inventory Tracking**: Monitor equipment allocation and status

## Core Modules

### 1. User Management
    - Handles authentication/authorization
    - Manages profiles for different roles (Admin, Teacher, Technician, Supplier)
    - Password encryption and JWT security

### 2. Resource Controller
    - Manages computer/printers inventory
    - Handles equipment allocation and tracking
    - Maintains warranty information

### 3. Maintenance Module
    - Breakdown reporting system
    - Technical analysis and repair tracking
    - Warranty status checks

### 4. Procurement System
    - Bid creation and management (Appels d'offre)
    - Supplier submissions handling
    - Blacklist management for suppliers

### 5. Notification Service
    - Real-time event notifications
    - User-specific alert system
    - Status update communications

## Key Technologies
    - **Backend**: Spring Boot 2.7+
    - **Security**: Spring Security + JWT
    - **Database**: MongoDB
    - **Templating**: Thymeleaf and Bootstrap
    - **Build Tool**: Maven
