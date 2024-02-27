package org.example;

// демонстрация парадокса Монти Холла (Парадокс Монти Холла — Википедия ) и наглядно убедиться в верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).
// Необходимо:
// Создать свой Java Maven или Gradle проект;
// Подключите зависимость lombok и возможно какую то математическую библиотеку (напр. commons-math3)
// Самостоятельно реализовать прикладную задачу;
// Сохранить результат игр в одну из коллекций или в какой то библиотечный класс
// Вывести на экран статистику по победам и поражениям
// В качестве ответа прислать ссылку на репозиторий, в котором присутствует все важные файлы проекта (напр pom.xml)

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import static java.lang.Math.round;


public class Main {
    final static int GAMES_TOTAL_NUMBER = 1000;
    static DescriptiveStatistics changeChoice = new DescriptiveStatistics();
    static DescriptiveStatistics keepChoice = new DescriptiveStatistics();

    public static void main(String[] args) {


        for (int i = 0; i < GAMES_TOTAL_NUMBER; i++) {
            Game game = new Game();

            System.out.printf("\rИдет игра №: %d / %d", i + 1, GAMES_TOTAL_NUMBER);

            collectStatistics(game);
        }

        printStatistics();
    }

    private static void collectStatistics(Game game) {
        int res = game.isPlayerWin() ? 1 : 0;

        if (game.isPlayerChangedChoice()) {
            changeChoice.addValue(res);
        } else {
            keepChoice.addValue(res);
        }
    }

    private static void printStatistics() {
        System.out.println("\rВсего проведено игр: " + GAMES_TOTAL_NUMBER);
        System.out.println("\nИгрок менял первоначальный выбор:"
                + "\nколичество раз: " + changeChoice.getN()
                + "\nиз них выиграл: " + round(changeChoice.getSum())
                + "\nдоля выигрышей: " + round(changeChoice.getMean()*100d)/100d
        );
        System.out.println("\nИгрок НЕ менял первоначальный выбор:"
                + "\nколичество раз: " + keepChoice.getN()
                + "\nиз них выиграл: " + round(keepChoice.getSum())
                + "\nдоля выигрышей: " + round(keepChoice.getMean()*100d)/100d
        );
    }

}