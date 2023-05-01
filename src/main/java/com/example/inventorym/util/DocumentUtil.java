package com.example.inventorym.util;

import java.util.Random;

public final class DocumentUtil {

    private DocumentUtil() {
    }

    private static final String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";

    public static String generateDocNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(SYMBOLS.length());
            sb.append(SYMBOLS.charAt(index));
        }
        return sb.toString();
    }
}
