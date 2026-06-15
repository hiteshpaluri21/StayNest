package com.staynest.serviceimpl;

import com.staynest.entities.AuditLog;
import com.staynest.repository.AuditLogRepository;
import com.staynest.service.AuditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    private static final Logger logger = LoggerFactory.getLogger(AuditLogServiceImpl.class);

    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditLogServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public void logAction(Integer userId, String action, String entityType) {
        // This shared method will be called by ALL modules (RIC, FDS, FBM, RBM)
        // after they clone and merge their code into this project
        logger.info("Logging action '{}' by userId: {} on entity: {}",
                action, userId, entityType);
        AuditLog log = new AuditLog(userId, action, entityType);
        auditLogRepository.save(log);
    }

    @Override
    public List<AuditLog> getLogsByUserId(Integer userId) {
        logger.info("Fetching audit logs for userId: {}", userId);
        return auditLogRepository.findByUserId(userId);
    }

    @Override
    public List<AuditLog> getAllLogs() {
        logger.info("Fetching all audit logs");
        return auditLogRepository.findAll();
    }
}
