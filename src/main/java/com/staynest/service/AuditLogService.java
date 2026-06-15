package com.staynest.service;

import com.staynest.entities.AuditLog;

import java.util.List;

public interface AuditLogService {

    // Shared method — called by ALL modules after merge
    void logAction(Integer userId, String action, String entityType);

    List<AuditLog> getLogsByUserId(Integer userId);

    List<AuditLog> getAllLogs();
}
