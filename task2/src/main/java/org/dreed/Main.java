package org.dreed;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Необходимо указать два аргумента - пути к файлам с координатами и радиусом окружности, а также с координатами точек.");
            return;
        }

        String circleFile = args[0];
        String pointsFile = args[1];

        try {
            // Считываем координаты и радиус окружности из файла
            Scanner circleScanner = new Scanner(new File(circleFile));
            float centerX = circleScanner.nextFloat();
            float centerY = circleScanner.nextFloat();
            float radius = circleScanner.nextFloat();
            circleScanner.close();

            // Считываем координаты точек из файла
            Scanner pointsScanner = new Scanner(new File (pointsFile));
            while (pointsScanner.hasNextFloat()) {
                float pointX = pointsScanner.nextFloat();
                float pointY = pointsScanner.nextFloat();

                // Рассчитываем расстояние от центра окружности до точки
                double distance = Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));

                // Проверяем положение точки относительно окружности и выводим результат
                if (Math.abs(distance - radius) < 1e-6) {
                    System.out.println("0");
                } else if (distance < radius) {
                    System.out.println("1");
                } else {
                    System.out.println("2");
                }
            }
            pointsScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        }
    }
}