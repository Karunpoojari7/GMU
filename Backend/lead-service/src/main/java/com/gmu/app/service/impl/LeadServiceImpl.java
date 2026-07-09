//package com.gmu.leadservice.service.impl;
//
//import com.gmu.leadservice.constants.LeadConstants;
//import com.gmu.leadservice.dto.request.CreateLeadRequest;
//import com.gmu.leadservice.dto.response.LeadResponse;
//import com.gmu.leadservice.entity.AdEnquiryCall;
//import com.gmu.leadservice.entity.CurrentAdmissionYear;
//import com.gmu.leadservice.entity.User;
//import com.gmu.leadservice.exception.NotFoundException;
//import com.gmu.leadservice.mapper.LeadMapper;
//import com.gmu.leadservice.repository.AdEnquiryCallRepository;
//import com.gmu.leadservice.repository.CurrentAdmissionYearRepository;
//import com.gmu.leadservice.repository.UserRepository;
//import com.gmu.leadservice.service.LeadService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class LeadServiceImpl implements LeadService {
//
//    private final AdEnquiryCallRepository repository;
//    private final UserRepository userRepository;
//    private final CurrentAdmissionYearRepository currentAdmissionYearRepository;
//    private final LeadMapper mapper;
//    private final AuditServiceImpl auditService; // 1. Added Audit Service
//
//    @Override
//    @Transactional
//    public LeadResponse createLead(CreateLeadRequest request) {
//        AdEnquiryCall entity = mapper.toEntity(request);
//        entity.setAdmissionYear(getCurrentAcademicYear());
//        AdEnquiryCall savedEntity = repository.save(entity);
//        return mapper.toResponse(savedEntity);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public LeadResponse getLeadByEnquiryNo(Integer enquiryNo) {
//        AdEnquiryCall entity = repository.findById(enquiryNo)
//                .orElseThrow(() -> new NotFoundException("Lead record not found for Enquiry No: " + enquiryNo));
//        return mapper.toResponse(entity);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Page<LeadResponse> getPagedLeads(String bucketType, String searchValue, Pageable pageable) {
//        String academicYear = getCurrentAcademicYear();
//        Page<AdEnquiryCall> entityPage;
//
//        switch (bucketType.toLowerCase()) {
//            case "hot":
//                entityPage = repository.findHotLeads(academicYear, pageable);
//                break;
//            case "cold":
//                entityPage = repository.findColdLeads(academicYear, pageable);
//                break;
//            case "not-consulted":
//                entityPage = repository.findNotConsultedLeads(academicYear, pageable);
//                break;
//            default:
//                entityPage = repository.findAll(pageable);
//                break;
//        }
//
//        return entityPage.map(mapper::toResponse);
//    }
//
//    @Override
//    @Transactional
//    public int assignLeadsToCaller(String callersEmpId, List<Integer> enquiryIds) {
//        User caller = userRepository.findByEmpId(callersEmpId)
//                .orElseThrow(() -> new NotFoundException("Telecaller not found with Employee ID: " + callersEmpId));
//
//        List<AdEnquiryCall> leads = repository.findAllById(enquiryIds);
//        for (AdEnquiryCall lead : leads) {
//            lead.setCallersEmpId(callersEmpId);
//            lead.setCallerName(caller.getName());
//            lead.setLastUpdated(LocalDateTime.now());
//        }
//        repository.saveAll(leads);
//        
//        // 2. Audit Logging (Parity with PHP admin_tools.php)
//        auditService.logAction("ADMIN", "ASSIGN", "ad_enquiry_call", "Assigned " + leads.size() + " leads to " + caller.getName(), "127.0.0.1");
//        
//        return leads.size();
//    }
//
//    @Override
//    @Transactional
//    public int reassignLeads(String callersEmpId, List<Integer> enquiryIds) {
//        User caller = userRepository.findByEmpId(callersEmpId)
//            .orElseThrow(() -> new NotFoundException("Caller not found"));
//
//        List<AdEnquiryCall> leads = repository.findAllById(enquiryIds);
//        for (AdEnquiryCall lead : leads) {
//            lead.setCallersEmpId(callersEmpId);
//            lead.setCallerName(caller.getName());
//            lead.setLastUpdated(LocalDateTime.now());
//        }
//        repository.saveAll(leads);
//        
//        // 3. Audit Logging
//        auditService.logAction("ADMIN", "REASSIGN", "ad_enquiry_call", "Reassigned " + leads.size() + " leads to " + caller.getName(), "127.0.0.1");
//        
//        return leads.size();
//    }
//
//    private String getCurrentAcademicYear() {
//        CurrentAdmissionYear activeConfig = currentAdmissionYearRepository.findFirstByOrderBySlNoDesc();
//        return (activeConfig != null) ? activeConfig.getAcademicYear() : LeadConstants.DEFAULT_ACADEMIC_YEAR;
//    }
//    
//    @Override
//    @Transactional
//    public int bulkUpdateStatus(List<Integer> enquiryIds, String newStatus) {
//        List<AdEnquiryCall> leads = repository.findAllById(enquiryIds);
//        
//        for (AdEnquiryCall lead : leads) {
//            lead.setStatus(newStatus);
//            lead.setLastUpdated(LocalDateTime.now());
//        }
//        
//        repository.saveAll(leads);
//        
//        // Log this bulk action (Audit parity)
//        auditService.logAction("ADMIN", "BULK_UPDATE", "ad_enquiry_call", 
//            "Updated " + leads.size() + " leads to status: " + newStatus, "127.0.0.1");
//            
//        return leads.size();
//    }
//}


