package org.example.springbootassignment.utils;

import java.util.Random;

public class UniqueNumber {

    public static int generateAccountNumber(int min, int max){
        Random random = new Random();
        return random.nextInt((max - min )+min);
    }


    public static String generateTransactionID() {
        return "TXN" + System.currentTimeMillis() + UniqueNumber.generateAccountNumber(1000, 9999);
    }
}

