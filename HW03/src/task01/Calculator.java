package task01;

public class Calculator {
    public static <T extends Number, U extends Number> double sum(T val1, U val2) {
        return val1.doubleValue() + val2.doubleValue();
    }

    public static <T extends Number, U extends Number> double multiply(T val1, U val2) {
        return val1.doubleValue() * val2.doubleValue();
    }

    public static <T extends Number, U extends Number> double divide(T val1, U val2) {
        if (val2.doubleValue() != 0F) {
            return val1.doubleValue() / val2.doubleValue();
        } else {
            throw new ArithmeticException("Division by zero is not allowed!");
        }
    }

    public static <T extends Number, U extends Number> double subtract(T val1, U val2) {
        return val1.doubleValue() - val2.doubleValue();
    }

}
