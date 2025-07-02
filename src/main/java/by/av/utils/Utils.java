package by.av.utils;

import java.util.Random;

public class Utils {

    static Random random = new Random();

    public static String generatePhoneNumber() {
        String[] prefix = {"25", "29", "33", "44"};

        StringBuilder number = new StringBuilder();
        number.append(prefix[random.nextInt(prefix.length)]);
        number.append(random.nextInt(9) + 1);

        for (int i = 0; i < 6; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }

    public static String generateInvalidPhoneNumber() {
        StringBuilder number = new StringBuilder();

        for (int i = 1; i < 10; i++) {
            number.append(random.nextInt(10));
        }

        return number.toString();
    }
}
