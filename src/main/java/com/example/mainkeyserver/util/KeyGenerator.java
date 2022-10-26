package com.example.mainkeyserver.util;

import com.example.mainkeyserver.domain.KeyLogRepository;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Random;

@Setter
@Component
public class KeyGenerator {
    private int keyLength = 16;
    private final Random random;
    private int randomOrigin, randomBound;

    public KeyGenerator(KeyLogRepository keyLogRepository){
        random = new Random();
        setDefaultRandomRange();
        setRandomSeed(new RandomSeedMakeWithLastKey(random, keyLogRepository));
    }

    public String generateKey(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            char randomCharValue = (char) random.nextInt(randomOrigin, randomBound);
            stringBuilder.append(randomCharValue);
        }
        return stringBuilder.toString();
    }

    public void setRandomSeed(RandomSeedMaker randomSeedMaker){
        randomSeedMaker.setRandom(random);
        randomSeedMaker.makeSeed();
    }

    public void setRandomRange(int randomOrigin, int randomBound){
        this.randomOrigin = randomOrigin;
        this.randomBound = randomBound;
    }

    private void setDefaultRandomRange(){
        int startMeaningfulCharIndex = 32;
        int endMeaningfulCharIndex = 127;
        setRandomOrigin(startMeaningfulCharIndex);
        setRandomBound(endMeaningfulCharIndex);
    }
}
