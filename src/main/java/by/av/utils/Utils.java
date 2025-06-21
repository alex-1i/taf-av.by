package by.av.utils;

import java.util.Random;

public class Utils {

    public static String generatePhoneNumber() {
        String[] prefix = {"25", "29", "33", "44"};
        Random random = new Random();

        StringBuilder number = new StringBuilder();
        number.append(prefix[random.nextInt(prefix.length)]);
        number.append(random.nextInt(9) + 1);

        for (int i = 0; i < 6; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }
}
