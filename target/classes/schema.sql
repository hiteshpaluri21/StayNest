-- ─────────────────────────────────────────────────────────────────────────────
-- StayNest Database Schema
-- IAM-001 : User and AuditLog tables
-- Author  : Hitesh Paluri (2507322)
-- ─────────────────────────────────────────────────────────────────────────────

CREATE DATABASE IF NOT EXISTS staynest_db;
USE staynest_db;

-- ─── User ─────────────────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS User (
    UserID     INT          AUTO_INCREMENT PRIMARY KEY,
    Name       VARCHAR(100) NOT NULL,
    Role       ENUM('GUEST','FRONTDESK','HOUSEKEEPING','FBMANAGER','REVENUEMANAGER','ADMIN') NOT NULL,
    Email      VARCHAR(150) NOT NULL UNIQUE,
    Phone      VARCHAR(20),
    PropertyID INT,
    Password   VARCHAR(255) NOT NULL,
    Status     ENUM('ACTIVE','INACTIVE') NOT NULL DEFAULT 'ACTIVE'
);

-- ─── AuditLog ─────────────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS AuditLog (
    AuditID    INT         AUTO_INCREMENT PRIMARY KEY,
    UserID     INT         NOT NULL,
    Action     VARCHAR(50) NOT NULL,
    EntityType VARCHAR(50),
    Timestamp  DATETIME    DEFAULT NOW(),
    CONSTRAINT fk_audit_user FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- ─── Seed: 5 users (one per role) ─────────────────────────────────────────────
-- Passwords are BCrypt of 'password123'
INSERT INTO User (Name, Role, Email, Phone, PropertyID, Password, Status) VALUES
('Hitesh Admin',     'ADMIN',          'admin@staynest.com',        '9000000001', 1, '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lHHG', 'ACTIVE'),
('Rajesh Guest',     'GUEST',          'guest@staynest.com',        '9000000002', 1, '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lHHG', 'ACTIVE'),
('Priya FrontDesk',  'FRONTDESK',      'frontdesk@staynest.com',    '9000000003', 1, '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lHHG', 'ACTIVE'),
('Arjun Housekeep',  'HOUSEKEEPING',   'housekeeping@staynest.com', '9000000004', 1, '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lHHG', 'ACTIVE'),
('Meera Revenue',    'REVENUEMANAGER', 'revenue@staynest.com',      '9000000005', 1, '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lHHG', 'ACTIVE');

-- ─── Seed: 3 audit log entries ─────────────────────────────────────────────────
INSERT INTO AuditLog (UserID, Action, EntityType) VALUES
(1, 'LOGIN',         'User'),
(1, 'CREATE_USER',   'User'),
(3, 'STATUS_UPDATE', 'User');
