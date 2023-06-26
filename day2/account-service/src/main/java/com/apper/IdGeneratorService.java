package com.apper;

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class IdGeneratorService {

    private static final int VERIFICATION_CODE_LENGTH = 6;

    private static final Random random = new Random();

    public static String generateRandomCharacters(int length) {
        return RandomStringUtils.random(length, true, true);
    }

    public static String generateVerificationCode() {
        return generateRandomCharacters(VERIFICATION_CODE_LENGTH);
    }

    public static String getNextId() {
        return String.valueOf(random.nextInt(100));
    }

}