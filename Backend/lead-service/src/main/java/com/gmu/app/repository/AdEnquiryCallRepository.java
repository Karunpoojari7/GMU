//package com.gmu.leadservice.repository;
//
//import com.gmu.leadservice.entity.AdEnquiryCall;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface AdEnquiryCallRepository extends JpaRepository<AdEnquiryCall, Integer> {
//
//    // --- 1. Filter Logic (Mapped from lead_overview.php) ---
//    
//    @Query(value = "SELECT * FROM ad_enquiry_call WHERE admission_year = :academicYear " +
//                   "AND status = 'ENQUIRY' AND call_count > 0 " +
//                   "AND opinion LIKE 'Interested'", 
//           nativeQuery = true)
//    Page<AdEnquiryCall> findHotLeads(@Param("academicYear") String academicYear, Pageable pageable);
//
//    @Query(value = "SELECT * FROM ad_enquiry_call WHERE admission_year = :academicYear " +
//                   "AND status = 'ENQUIRY' AND call_count > 0 " +
//                   "AND (opinion LIKE 'Entrance Exam' OR opinion LIKE 'Not Decided' OR opinion LIKE 'Not interested' " +
//                   "OR opinion LIKE 'Not Received' OR opinion LIKE 'Not decided' OR opinion LIKE 'Wrong number' " +
//                   "OR opinion LIKE 'Already admitted')", 
//           nativeQuery = true)
//    Page<AdEnquiryCall> findColdLeads(@Param("academicYear") String academicYear, Pageable pageable);
//
//    @Query(value = "SELECT * FROM ad_enquiry_call WHERE admission_year = :academicYear " +
//                   "AND call_count = 0 AND callers_emp_id IS NOT NULL AND callers_emp_id != ''", 
//           nativeQuery = true)
//    Page<AdEnquiryCall> findNotConsultedLeads(@Param("academicYear") String academicYear, Pageable pageable);
//
//    // --- 2. Dashboard Stats (Mapped from index.php/fetch_dashboard_stats.php) ---
//
//    // Total Count
//    long countByAdmissionYear(String admissionYear);
//    
//    // Status Count
//    long countByStatusAndAdmissionYear(String status, String admissionYear);
//    
//    // Allotted: callers_emp_id is not null AND not empty
//    long countByCallersEmpIdIsNotNullAndCallersEmpIdNot(String emptyString);
//    
//    // Not Allotted: callers_emp_id is null OR empty
//    long countByCallersEmpIdIsNull();
//}


