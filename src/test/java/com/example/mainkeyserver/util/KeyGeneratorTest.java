package com.example.mainkeyserver.util;

import com.example.mainkeyserver.domain.KeyLog;
import com.example.mainkeyserver.domain.KeyLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class KeyGeneratorTest {
    @Autowired
    private KeyLogRepository keyLogRepository;

    @Test
    void generateKey(){
        //Given
        int keyLength = 16;
        //When
        Random random = new Random();
        makeSeed(random, getLastIndexKey());

        //Then
        String newKey = generateKey(random, keyLength);
        assertNotNull(newKey);
        assertEquals(keyLength, newKey.length()); // 16 is keyLength
    }

    private String generateKey(Random random, int keyLength){
        int origin = 32;
        int bound = 127;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            char randomCharValue = (char) random.nextInt(origin, bound);
            stringBuilder.append(randomCharValue);
        }
        return stringBuilder.toString();
    }

    private void makeSeed(Random random, String lastKey) {
        if (lastKey == null)
            random.setSeed(System.currentTimeMillis());
        else{
            int seed = stringToInt(lastKey);
            random.setSeed(seed);
        }
    }

    private int stringToInt(String lastKey){
        return lastKey.hashCode();
    }

    private String getLastIndexKey(){
        long lastKeyIndex = keyLogRepository.count() - 1;
        if (lastKeyIndex == -1){
            return null;
        }

        KeyLog keyLog = keyLogRepository.findById(lastKeyIndex).orElseThrow();
        return keyLog.getKeyValue();
    }
}
