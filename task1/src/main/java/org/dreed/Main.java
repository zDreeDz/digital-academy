package org.dreed;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int n = sc.nextInt();
        System.out.print("Введите длину интревала: ");
        int m = sc.nextInt();

        int [] arr = new int[n];
        Arrays.setAll(arr, i -> ++i);

        int current = 0;
        System.out.print("Путь: ");
        do {
            System.out.print(arr[current]);
            current = (current + m - 1) % n;
        } while (current != 0);

    }
}