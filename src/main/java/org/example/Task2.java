package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов списка: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Количество элементов должно быть больше 0.");
            return;
        }

        List<Double> list = new ArrayList<>(n);
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            list.add(random.nextDouble() * 100);
        }

        System.out.println("Исходный список: " + list);

        // Пузырьковая сортировка (без изменений)
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    double temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }

        System.out.println("Отсортированный список: " + list);
    }
}
