//package com.gmu.leadservice.util;
//
//import org.springframework.stereotype.Service;
//
//import com.gmu.leadservice.entity.EmployeeSequence;
//import com.gmu.leadservice.repository.EmployeeSequenceRepository;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class EmployeeIdGenerator {
//
//    private final EmployeeSequenceRepository repository;
//
//    @Transactional
//    public String generateEmployeeId() {
//        EmployeeSequence sequence = repository.lockSequence();
//        Long value = sequence.getNextValue();
//        sequence.setNextValue(value + 1);
//        repository.save(sequence);
//        return String.format("GMU%06d", value);
//    }
//}


