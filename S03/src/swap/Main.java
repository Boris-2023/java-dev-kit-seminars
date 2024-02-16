package swap;

import java.util.Arrays;

public class Main {
    // this method can be done w/o generics, just use Object class instead of T
    private static <T> void swap(T[] arr, int from, int to){
        T temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    public static void main(String[] args) {
//        Object[] arr = {1, 2.0f, "hello"};
        String[] arr = {"one", "two", "three"};
        System.out.println(Arrays.toString(arr));
        swap(arr, 0, 2);
        System.out.println(Arrays.toString(arr));

    }
}
