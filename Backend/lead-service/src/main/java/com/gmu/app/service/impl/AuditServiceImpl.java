//package com.gmu.leadservice.service.impl;
//
//import com.gmu.leadservice.entity.GmuAudit;
//import com.gmu.leadservice.repository.GmuAuditRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class AuditServiceImpl {
//
//    private final GmuAuditRepository auditRepository;
//
//    public void logAction(String user, String action, String tableName, String description, String ip) {
//        GmuAudit audit = new GmuAudit();
//        audit.setUser(user);
//        audit.setAction(action);
//        audit.setTableName(tableName);
//        audit.setDescription(description);
//        audit.setIp(ip);
//        audit.setDatetime(LocalDateTime.now());
//        
//        auditRepository.save(audit);
//    }
//}



