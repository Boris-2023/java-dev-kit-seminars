package task02;

import java.util.*;
import java.util.stream.Collectors;

// Создайте коллекцию мужских и женских имен с помощью интерфейса List - добавьте повторяющиеся значения
// 1. Получите уникальный список Set на основании List
// 2. Определите наименьший элемент (алфавитный порядок)
// 3. Определите наибольший элемент (по количеству букв в слове но в обратном порядке)
// 4. Удалите все элементы содержащие букву ‘A’
public class NamesSet {
    private static List<String> names = new ArrayList<>(Arrays.asList("Ольга", "Никифор", "Ольга", "Марта", "Семен", "Ян", "Христофор", "Семен"));

    // task 1
    private static HashSet<String> namesSet = new HashSet<>(names);

    public static void main(String[] args) {

        System.out.println(names);

        // task 1
        System.out.println(namesSet);

        // task 2
        System.out.println(firstItemByAlpha(namesSet));

        // task 3
        System.out.println(maxWordLength(namesSet));

        // task 4
//        System.out.println(removeAllThatContains(namesSet, "А"));

//        removeAllThatContainsByLambda(namesSet, "А");
//        System.out.println(namesSet);

        System.out.println(removeAllThatContainsByFilter(namesSet, "А"));

    }

    private static String firstItemByAlpha(Set<String> hSet) {
        return hSet.stream()
                .min(String::compareTo)
                .orElseGet(null);
    }

    private static String maxWordLength(Set<String> hSet) {
        return hSet.stream()
                .max(Comparator.comparingInt(String::length))
                .orElseGet(null);
    }

    private static Set<String> removeAllThatContains(Set<String> hSet, String toFind) {
        Set<String> res = new HashSet<>();
        for (String s : hSet) {
            if (!s.toLowerCase().contains(toFind.toLowerCase())) {
                res.add(s);
            }
        }
        return res;
    }

    private static void removeAllThatContainsByLambda(Set<String> hSet, String toFind) {
        hSet.removeIf(x -> x.toLowerCase().contains(toFind.toLowerCase()));
    }

    private static Set<String> removeAllThatContainsByFilter(Set<String> hSet, String toFind) {
        return hSet.stream()
                .filter(x -> !x.toLowerCase().contains(toFind.toLowerCase()))
                .collect(Collectors.toSet());
    }
}
