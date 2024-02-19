package task01;

import java.util.*;
import java.util.stream.Collectors;

//В рамках выполнения задачи необходимо:
//  1. Создайте коллекцию мужских и женских имен с помощью интерфейса List
//  2. Отсортируйте коллекцию в алфавитном порядке
//  3. Отсортируйте коллекцию по количеству букв в слове
//  4. Разверните коллекцию
public class NamesList {

    // task 1
    private static List<String> names = new ArrayList<>(Arrays.asList("Ольга", "Никифор", "Марта", "Семен", "Ян", "Христофор"));

    public static void main(String[] args) {

        System.out.println(names);

        // task 2
        sortByAlphaOrder(names);
        System.out.println(names);

        // task 3
        sortByWordLength(names);
        System.out.println(names);

        // task 3b
        System.out.println(sortByWordLengthStream(names));

        // task 4
        reverseList(names);
        System.out.println(names);
    }

    private static void sortByAlphaOrder(List<String> list) {
        // for String - byDefault alphabetical order
        Collections.sort(list);
    }

    private static void sortByWordLength(List<String> list) {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
    }

    private static List<String> sortByWordLengthStream(List<String> list) {
        return list.stream()
                .sorted((x, y) -> x.length() - y.length())
                .collect(Collectors.toList());
    }

    private static void reverseList(List<String> list) {
        Collections.reverse(list);
    }

}
