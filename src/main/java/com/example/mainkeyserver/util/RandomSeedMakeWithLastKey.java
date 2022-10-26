package com.example.mainkeyserver.util;

import com.example.mainkeyserver.domain.KeyLog;
import com.example.mainkeyserver.domain.KeyLogRepository;
import lombok.Setter;

import javax.transaction.Transactional;
import java.util.Random;

@Setter
public class RandomSeedMakeWithLastKey implements RandomSeedMaker {
    private Random random;
    private final String lastKey;
    private boolean hasNoKeyData = false;

    public RandomSeedMakeWithLastKey(Random random, KeyLogRepository keyLogRepository){
        this.random = random;
        lastKey = getLastIndexKey(keyLogRepository);
    }

    @Override
    public void makeSeed() {
        if (hasNoKeyData)
            setSeedWithBasicRule();
        else{
            int seed = stringToInt(lastKey);
            random.setSeed(seed);
        }
    }

    @Transactional
    public String getLastIndexKey(KeyLogRepository keyLogRepository){
        long lastKeyIndex = keyLogRepository.count() - 1;
        if (lastKeyIndex == -1){
            setHasNoKeyData(true);
            return null;
        }

        KeyLog keyLog = keyLogRepository.findById(lastKeyIndex).orElseThrow();
        return keyLog.getKeyValue();
    }

    private int stringToInt(String value){
        return value.hashCode();
    }

    private void setSeedWithBasicRule(){
        random.setSeed(System.currentTimeMillis());
    }
}