package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Task3 {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Иванов Иван Иванович", 30, "Разработка", 50000.0));
        employees.add(new Employee("Петрова Анна Сергеевна", 25, "Маркетинг", 65000.0));
        employees.add(new Employee("Сидоров Петр Алексеевич", 40, "Разработка", 45000.0));
        employees.add(new Employee("Кузнецова Мария Ивановна", 35, "Аналитика", 70000.0));
        employees.add(new Employee("Смирнов Сергей Петрович", 28, "Тестирование", 55000.0));

        System.out.println("=== Исходный список сотрудников ===");
        employees.forEach(System.out::println);

        List<Employee> sortedBySalary = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .collect(Collectors.toList());

        System.out.println("\n=== Сотрудники, отсортированные по возрастанию зарплаты ===");
        sortedBySalary.forEach(System.out::println);
    }
}