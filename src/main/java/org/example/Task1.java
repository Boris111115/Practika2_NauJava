package org.example;

import java.util.Arrays;
import java.util.Random;

public class Task1 {
    public static void main(String[] args) {
        int n = 10;
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
