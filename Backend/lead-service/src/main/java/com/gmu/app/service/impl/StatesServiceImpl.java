//package com.gmu.leadservice.service.impl;
//
//import com.gmu.leadservice.dto.response.StateResponse;
//import com.gmu.leadservice.mapper.LeadMapper;
//import com.gmu.leadservice.repository.StatesRepository;
//import com.gmu.leadservice.service.StatesService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class StatesServiceImpl implements StatesService {
//
//    private final StatesRepository statesRepository;
//    private final LeadMapper mapper;
//
//    @Override
//    public List<StateResponse> getAllStates() {
//        return statesRepository.findAll().stream()
//                .map(mapper::toStateResponse)
//                .collect(Collectors.toList());
//    }
//}


