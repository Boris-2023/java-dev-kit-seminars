package task03;

// Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
// Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары,
// а также переопределение метода toString(), возвращающее строковое представление пары.
public class Pair <T, U>{
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public String toString(){
        return "\nЗначение №1 (класс " + first.getClass().getSimpleName() + "): " + first
                + "\nЗначение №2 (класс " + second.getClass().getSimpleName() + "): " + second;
    }

    public static void main(String[] args) {

        Pair<Float, Integer> pair1 = new Pair<>(1.0f, 25);
        System.out.println(pair1);

        System.out.println(new Pair<>("Hello, World!", 2.0));
    }
}
