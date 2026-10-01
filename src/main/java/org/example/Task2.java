package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task2 {
    public static void main(String[] args) {
        int n = 10;
        List<Double> list = new ArrayList<>(n);
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            list.add(random.nextDouble() * 100);
        }

        System.out.println("Исходный список: " + list);

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
