package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Практическая работа 2 ===");
            System.out.println("1. Задание 1: Среднее значение массива");
            System.out.println("2. Задание 2: Пузырьковая сортировка");
            System.out.println("3. Задание 3: Stream API (сортировка по зарплате)");
            System.out.println("4. Задание 4: HTTP-клиент (заголовки)");
            System.out.println("5. Задание 5: Синхронизация папок");
            System.out.println("0. Выход");
            System.out.print("Выберите задание: ");

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> Task1.main(args);
                case 2 -> Task2.main(args);
                case 3 -> Task3.main(args);
                case 4 -> Task4.main(args);
                case 5 -> Task5.main(args);
                case 0 -> {
                    System.out.println("Выход.");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}