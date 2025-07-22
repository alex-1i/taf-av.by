package by.av.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Utils {

    private static final Random random = new Random();

    private Utils() {
    }

    /**
     * Данный метод генерирует случайный номер телефона состоящий из префикса + номера телефона
     * префикс выбирается случайно из массива ARRAY_PREFIX
     * далее выбираем первую цифру номера, так как номер не может начинаться с 0, берём любое число от 0 до 8 и добавляем 1
     * после перебором цикла выбираем оставшиеся 6 цифр от 0 до 9
     * @return возвращаем строку в формате XXYYYYYYY
     */
    public static String generatePhoneNumber() {
        final String[] ARRAY_PREFIX = {"25", "29", "33", "44"};

        StringBuilder number = new StringBuilder();
        number.append(ARRAY_PREFIX[random.nextInt(ARRAY_PREFIX.length)]);
        number.append(random.nextInt(9) + 1);

        for (int i = 0; i < 6; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }

    /**
     * Данный метод генерирует строку, состоящую из 10 случайных цифр
     * перебором цикла выбираем 10 цифр от 0 до 9
     * @return возвращаем строку из 10 случайных цифр
     */
    public static String generateInvalidInputData() {
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            // берём любое число от 0 до 9
            number.append(random.nextInt(10));
        }

        return number.toString();
    }

    public static String getRandomService(List<String> services) {
        if (services.isEmpty()) {
            throw new IllegalStateException("Список услуг пустой!");
        }

        return services.get(new Random().nextInt(services.size()));
    }

    public static Map.Entry<String, String> getRandomService(Map<String, String> services) {
        if (services.isEmpty()) {
            throw new IllegalStateException("Список услуг пустой!");
        }

        List<Map.Entry<String, String>> entries = new ArrayList<>(services.entrySet());
        return entries.get(new Random().nextInt(entries.size()));
    }
}
