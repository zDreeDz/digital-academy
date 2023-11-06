package org.dreed;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Необходимо указать путь к файлу с элементами массива.");
            return;
        }

        String inputFile = args[0];

        try {
            // Считываем элементы массива из файла
            Scanner scanner = new Scanner(new File(inputFile));
            int[] nums = new int[scanner.nextInt()];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = scanner.nextInt();
            }
            scanner.close();

            // Находим минимальное и максимальное значение в массиве
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                min = Math.min(min, num);
                max = Math.max(max, num);
            }

            // Вычисляем минимальное количество ходов
            int moves = max - min;

            // Выводим результат
            System.out.println("Минимальное количество ходов: " + moves);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        }
    }
}