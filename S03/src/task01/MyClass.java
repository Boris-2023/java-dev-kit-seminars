package task01;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;

// создать обобщенный класс с тремя параметрами (T, V, K)
// класс модержит три переменные этих типов, конструктор, принимающий
// на вход параметры типа (T, V, K), геттеры
//
// Создать метод, выводящий на консоль имена классов для трех переменных класса.
// Наложить ограничения на параметры типа:
// T должен реализовать интерфейс Comparable
// V должен реализовать интерфейс DataInput, расширять класс InputStream
// K расширять класс Number

public class MyClass<T extends Comparable<T>, V extends InputStream & DataInput, K extends Number> {
    private T t;
    private V v;
    private K k;

    public MyClass(T t, V v, K k) {
        this.t = t;
        this.v = v;
        this.k = k;
    }

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }

    public K getK() {
        return k;
    }

    public void printTypes() {
        System.out.println("the type for T: " + t.getClass().getSimpleName()
                + "\nthe type for V: " + v.getClass().getSimpleName()
                + "\nthe type for K: " + k.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        MyClass<String, DataInputStream, Integer> myClass = new MyClass<>("abs", new DataInputStream(System.in), 555);
        myClass.printTypes();
    }
}
