//package com.gmu.leadservice.repository;
//
//import com.gmu.leadservice.entity.AdEnquiryCall;
//import org.springframework.data.jpa.domain.Specification;
//import jakarta.persistence.criteria.Predicate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AdEnquiryCallSpecification {
//
//    // This method returns a "Specification" object that JPA uses to filter data
//    public static Specification<AdEnquiryCall> buildFilters(String district, String taluk, String program) {
//        return (root, query, cb) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            // If the user provided a district, add a filter condition
//            if (district != null && !district.isEmpty()) {
//                predicates.add(cb.equal(root.get("district"), district));
//            }
//            
//            // If the user provided a taluk, add a filter condition
//            if (taluk != null && !taluk.isEmpty()) {
//                predicates.add(cb.equal(root.get("taluk"), taluk));
//            }
//            
//            // If the user provided a program, look for it inside the university name
//            if (program != null && !program.isEmpty()) {
//                predicates.add(cb.like(root.get("interestedUniversity"), "%" + program + "%"));
//            }
//
//            // Combine all conditions with "AND"
//            return cb.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//}


