package com.senyasas.cs.service;

import com.senyasas.cs.model.api.request.my.myRequest;
import com.senyasas.cs.model.mapping.myMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class myService {

    private final com.senyasas.cs.repository.myRepository myRepository;
    private final myMapper myMapper;

    public void example(myRequest request) {
        //return myMapper.toResponse(my);
    }
}
