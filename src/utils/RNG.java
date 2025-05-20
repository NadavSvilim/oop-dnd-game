package utils;

import java.util.Random;

public class RNG {
    public static int roll(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
