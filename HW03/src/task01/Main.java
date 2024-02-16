package task01;

// Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
// sum(), multiply(), divide(), subtract(). Параметры этих методов – два числа разного типа,
// над которыми должна быть произведена операция.
public class Main {
    public static void main(String[] args) {

        int val1 = 20;
        double val2 = 5;
        float val3 = 0;

        System.out.println(val1 + " + " + val2 + " = " + Calculator.sum(val1, val2));
        System.out.println(val1 + " * " + val2 + " = " + Calculator.multiply(val1, val2));
        System.out.println(val1 + " - " + val2 + " = " + Calculator.subtract(val1, val2));
        System.out.println(val1 + " / " + val2 + " = " + Calculator.divide(val1, val2));
        System.out.println(val2 + " / " + val3 + " = " + Calculator.divide(val2, val3));

    }
}
