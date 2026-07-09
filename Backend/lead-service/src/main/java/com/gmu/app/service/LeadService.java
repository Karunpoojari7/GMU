package com.gmu.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gmu.app.dto.request.CreateLeadRequest;
import com.gmu.app.dto.response.LeadResponse;

public interface LeadService {

    // Core CRUD Operation
    LeadResponse createLead(CreateLeadRequest request);
    LeadResponse getLeadByEnquiryNo(Integer enquiryNo);

    // Server-side pagination matching legacy PHP DataTables buckets
    Page<LeadResponse> getPagedLeads(String bucketType, String searchValue, Pageable page);

    // Bulk caller assignment logic from the admin_tools action
    int assignLeadsToCaller(String callersEmpId, List<Integer> enquiryIds);
    int reassignLeads(String callersEmpId, List<Integer> enquiryIds);

    // Logic: Update multiple leads at once (e.g. mark as 'Closed' or 'Invalid')
    int bulkUpdateStatus(List<Integer> enquiryIds, String newStatus);
}