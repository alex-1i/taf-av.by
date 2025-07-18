package by.av.utils;

import java.util.Random;

public class Utils {

    static Random random = new Random();

    public static String generatePhoneNumber() {
        final String[] ARRAY_PREFIX = {"25", "29", "33", "44"};

        StringBuilder number = new StringBuilder();
        number.append(ARRAY_PREFIX[random.nextInt(ARRAY_PREFIX.length)]);
        // выбираем первую цифру номера, так как номер не может начинаться с 0, берём любое число от 0 до 8 и добавляем 1
        number.append(random.nextInt(9) + 1);

        // выбираем оставшиеся 6 цифр номера
        for (int i = 0; i < 6; i++) {
            // берём любое число от 0 до 9
            number.append(random.nextInt(10));
        }
        return number.toString();
    }

    public static String generateInvalidPhoneNumberOrEmail() {
        StringBuilder number = new StringBuilder();

        // генерируем число, состоящее из 10 цифр
        for (int i = 1; i < 10; i++) {
            // берём любое число от 0 до 9
            number.append(random.nextInt(10));
        }

        return number.toString();
    }
}
