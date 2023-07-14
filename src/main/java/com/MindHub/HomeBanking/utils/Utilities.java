package com.MindHub.HomeBanking.utils;

import java.util.Random;

public final class Utilities {
    private Utilities() {}
    public static String cardNumberGenerator() {
        Random random1 = new Random();
        return random1.nextInt(999)+ 1000 +
                "-" + random1.nextInt(999) + 1000 +
                "-" + random1.nextInt(999) + 1000 +
                "-" + random1.nextInt(999) + 1000;
    }
    public static Integer cvvGenerator(){
        Random random = new Random();
        return random.nextInt(999);
    }
    public static String accountNumberGenerator(){
        Random random = new Random();
        return "VIN-" + random.nextInt(99999999);
    }
}
