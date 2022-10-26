package com.example.mainkeyserver.util;

import java.util.Random;

public interface RandomSeedMaker {
    void setRandom(Random random);
    void makeSeed();
}
