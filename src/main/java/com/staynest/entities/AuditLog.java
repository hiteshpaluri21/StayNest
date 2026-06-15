package com.staynest.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "AuditLog")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AuditID")
    private Integer auditId;

    @Column(name = "UserID", nullable = false)
    private Integer userId;

    @Column(name = "Action", nullable = false, length = 50)
    private String action;

    @Column(name = "EntityType", length = 50)
    private String entityType;

    @CreationTimestamp
    @Column(name = "Timestamp", updatable = false)
    private LocalDateTime timestamp;

    // ── Constructors ──────────────────────────────────────────────────────────

    public AuditLog() {}

    public AuditLog(Integer userId, String action, String entityType) {
        this.userId     = userId;
        this.action     = action;
        this.entityType = entityType;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public Integer       getAuditId()                            { return auditId; }
    public void          setAuditId(Integer auditId)             { this.auditId = auditId; }

    public Integer       getUserId()                             { return userId; }
    public void          setUserId(Integer userId)               { this.userId = userId; }

    public String        getAction()                             { return action; }
    public void          setAction(String action)                { this.action = action; }

    public String        getEntityType()                         { return entityType; }
    public void          setEntityType(String entityType)        { this.entityType = entityType; }

    public LocalDateTime getTimestamp()                          { return timestamp; }
    public void          setTimestamp(LocalDateTime timestamp)   { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "AuditLog{auditId=" + auditId + ", userId=" + userId
                + ", action='" + action + "', entityType='" + entityType
                + "', timestamp=" + timestamp + "}";
    }
}
