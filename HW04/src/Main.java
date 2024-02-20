import java.time.LocalDate;

public class Main {

 //    Создать справочник сотрудников
//    Необходимо:
//    Создать класс справочник сотрудников, который содержит внутри
//    коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
//    Табельный номер, Номер телефона, Имя, Стаж
//    Добавить метод, который ищет сотрудника по стажу (может быть список)
//    Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
//    Добавить метод, который ищет сотрудника по табельному номеру
//    Добавить метод добавление нового сотрудника в справочник
    public static void main(String[] args) {

        // создаем список сотрудников методом .add()
        Directory employees = new Directory();

        employees.add(new Employee("Vasya", "121", LocalDate.of(2022, 5, 21)));
        employees.add(new Employee("Masha", "234", LocalDate.of(2018, 11, 10)));
        employees.add(new Employee("Petya", "567", LocalDate.of(2023, 10, 2)));
        employees.add(new Employee("Sveta", "973", LocalDate.of(2010, 5, 5)));
        employees.add(new Employee("Senya", "543", LocalDate.of(2022, 4, 11)));
        employees.add(new Employee("Vasya", "842", LocalDate.of(2019, 12, 27)));
        employees.add(new Employee("Tanya", "266", LocalDate.of(2022, 7, 12)));

        System.out.println("Список сотрудников: " + employees);

        // поиск сотрудников по стажу (нижняя и верхняя граница включительно)
        int minYears = 2;
        int maxYears = 5;
        System.out.println("\nСписок сотрудников по стажу (от " + minYears + " до " + maxYears + " лет): "
                + employees.findEmployeeByYearsOnService(minYears, maxYears));

        // поиск сотрудников по имени
        String name = "Vasya";
        System.out.println("\nСписок сотрудников с именем '" + name + "': "
                + employees.findEmployeeByName(name));


        // поиск сотрудника по табельному номеру
        int employeeId = 3;
        System.out.println("\nПоиск сотрудника по табельному номеру ID '" + employeeId + "': "
                + employees.findEmployeeById(employeeId));

    }
}