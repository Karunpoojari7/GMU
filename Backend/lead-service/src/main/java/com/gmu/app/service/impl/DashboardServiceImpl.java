//package com.gmu.leadservice.service.impl;
//
//import com.gmu.leadservice.repository.AdEnquiryCallRepository;
//import com.gmu.leadservice.service.DashboardService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class DashboardServiceImpl implements DashboardService {
//
//    private final AdEnquiryCallRepository repository;
//
//    @Override
//    public Map<String, Long> getDashboardStats() {
//        Map<String, Long> stats = new HashMap<>();
//        
//        // 1. Total Leads
//        stats.put("total", repository.count());
//        
//        // 2. Allotted Leads (Logic from legacy PHP: callers_emp_id NOT NULL)
//        stats.put("allotted", repository.countByCallersEmpIdIsNotNullAndCallersEmpIdNot(""));
//        
//        // 3. Not Allotted Leads (Logic from legacy PHP: callers_emp_id IS NULL)
//        stats.put("notAllotted", repository.countByCallersEmpIdIsNull());
//        
//        return stats;
//    }
//}


