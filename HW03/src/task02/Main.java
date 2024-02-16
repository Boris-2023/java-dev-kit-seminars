package task02;

// Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они одинаковые,
// и false в противном случае. Массивы могут быть любого типа данных,
// но должны иметь одинаковую длину и содержать элементы одного типа.
public class Main {

    public static <T> boolean compareArrays(T[] arr1, T[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].getClass().equals(arr2[i].getClass())) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Double[] arr1 = {5.0, 3.0, 1.0};
        Double[] arr2 = {2.0, 4.5, 7.0};
        System.out.println("Массивы Double равной длины: " + compareArrays(arr1, arr2));

        Double[] arr3 = {5.0, 3.0, 1.0};
        Double[] arr4 = {2.0, 4.5};
        System.out.println("Массивы Double разной длины: " + compareArrays(arr3, arr4));

        String[] arr5 = {"abc", "abc", "abc"};
        String[] arr6 = {"Array", "Hello, World!", "Privet!"};
        System.out.println("Массивы String равной длины: " + compareArrays(arr5, arr6));

    }
}
