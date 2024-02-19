package task03;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Задание № 3
//  1. Создайте телефонный справочник с помощью Map - телефон это ключ, а имя значение
//  2. Найдите человека с самым маленьким номером телефона
//  3. Найдите номер телефона человека чье имя самое большое в алфавитном порядке
public class PhoneBookMap {


    public static void main(String[] args) {

        // task 1
        Map<String, String> phoneBook = new HashMap<>();

        phoneBook.putIfAbsent("123", "Sergey");
        phoneBook.putIfAbsent("456", "Ivan");
        phoneBook.putIfAbsent("654", "Semen");
        phoneBook.putIfAbsent("241", "Tatiana");
        phoneBook.putIfAbsent("946", "Gorillych");

        System.out.println(phoneBook);

        // task 2
        System.out.println(smallestNumber(phoneBook) + ": " + phoneBook.get(smallestNumber(phoneBook)));

        // task 3
        System.out.println(phoneOfBiggestNameAlpha(phoneBook) + ": " + phoneBook.get(phoneOfBiggestNameAlpha(phoneBook)));


    }

    private static String smallestNumber(Map<String, String> map) {
        return map.keySet().stream()
                .map(Integer::parseInt)
                .min(Comparator.comparingInt(x -> x))
                .get()
                .toString();
    }

    private static String phoneOfBiggestNameAlpha(Map<String, String> map) {
        return map.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
    }


}
