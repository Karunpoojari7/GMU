//package com.gmu.leadservice.controller;
//
//import com.gmu.leadservice.dto.request.CreateLeadRequest;
//import com.gmu.leadservice.dto.response.ApiResponse;
//import com.gmu.leadservice.dto.response.LeadResponse;
//import com.gmu.leadservice.service.LeadService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/leads")
//@RequiredArgsConstructor
//public class LeadController {
//
//    private final LeadService leadService;
//
//    @PostMapping
//    public ResponseEntity<ApiResponse<LeadResponse>> createLead(@Valid @RequestBody CreateLeadRequest request) {
//        LeadResponse response = leadService.createLead(request);
//        return new ResponseEntity<>(ApiResponse.success("Lead created successfully", response), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{enquiryNo}")
//    public ResponseEntity<ApiResponse<LeadResponse>> getLeadByEnquiryNo(@PathVariable Integer enquiryNo) {
//        LeadResponse response = leadService.getLeadByEnquiryNo(enquiryNo);
//        return ResponseEntity.ok(ApiResponse.success("Lead found", response));
//    }
//
//    @GetMapping("/overview/{bucketType}")
//    public ResponseEntity<ApiResponse<Page<LeadResponse>>> getPagedLeads(
//            @PathVariable String bucketType,
//            @RequestParam(required = false) String search,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        
//        Pageable pageable = PageRequest.of(page, size);
//        Page<LeadResponse> pagedLeads = leadService.getPagedLeads(bucketType, search, pageable);
//        return ResponseEntity.ok(ApiResponse.success("Leads retrieved successfully", pagedLeads));
//    }
//
//    @PutMapping("/assign")
//    public ResponseEntity<ApiResponse<String>> assignLeads(
//            @RequestParam String callersEmpId,
//            @RequestBody List<Integer> enquiryIds) {
//        
//        int updatedCount = leadService.assignLeadsToCaller(callersEmpId, enquiryIds);
//        return ResponseEntity.ok(ApiResponse.success("Successfully assigned " + updatedCount + " lead records.", null));
//    }
//}

