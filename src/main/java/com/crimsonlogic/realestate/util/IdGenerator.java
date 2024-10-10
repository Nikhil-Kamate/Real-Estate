package com.crimsonlogic.realestate.util;

import java.util.Random;

public class IdGenerator {
	
	private static final int LOWER_BOUND = 100000;
    private static final int UPPER_BOUND = 999999;
 
    public static int generateRandomID() {
        Random random = new Random();
        return random.nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND;
    }

}
