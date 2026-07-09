//package com.gmu.leadservice.service.impl;
//
//import com.gmu.leadservice.constants.LeadConstants;
//import com.gmu.leadservice.entity.CurrentAdmissionYear;
//import com.gmu.leadservice.repository.CurrentAdmissionYearRepository;
//import com.gmu.leadservice.service.AdmissionYearService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AdmissionYearServiceImpl implements AdmissionYearService {
//
//    private final CurrentAdmissionYearRepository repository;
//
//    @Override
//    public String getCurrentAcademicYear() {
//        CurrentAdmissionYear activeConfig = repository.findFirstByOrderBySlNoDesc();
//        return (activeConfig != null) ? activeConfig.getAcademicYear() : LeadConstants.DEFAULT_ACADEMIC_YEAR;
//    }
//}


