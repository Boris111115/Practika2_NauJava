package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов массива: ");
        int n = scanner.nextInt();

        // Защита от n = 0 (деление на ноль)
        if (n <= 0) {
            System.out.println("Количество элементов должно быть больше 0.");
            return;
        }

        int[] array = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(100);
        }

        System.out.println("Массив: " + Arrays.toString(array));

        double sum = 0;
        for (int value : array) {
            sum += value;
        }

        double average = sum / n;
        System.out.println("Среднее значение: " + average);
    }
}
