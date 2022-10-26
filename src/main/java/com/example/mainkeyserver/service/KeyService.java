package com.example.mainkeyserver.service;

import com.example.mainkeyserver.util.KeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class KeyService {
    private final KeyGenerator keyGenerator;

    public String generateKey(int length){
        keyGenerator.setKeyLength(length);
        return keyGenerator.generateKey();
    }
}
