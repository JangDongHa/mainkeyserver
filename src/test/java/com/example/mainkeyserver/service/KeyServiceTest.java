package com.example.mainkeyserver.service;

import com.example.mainkeyserver.util.KeyGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class KeyServiceTest {
    @Autowired
    private KeyGenerator keyGenerator;

    // Key 생성 API
    @Test
    void generateKey(){
        // Given
        int length = 16;
        // When
        String newKey = keyGenerator.generateKey();
        // Then
        assertNotNull(newKey);
        assertEquals(16, newKey.length());
    }
}
