package org.dreed;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Необходимо указать пути к файлам tests.json и values.json.");
            return;
        }

        String testsFile = args[0];
        String valuesFile = args[1];

        try {
            // Читаем содержимое файлов
            String testsContent = new String(Files.readAllBytes(Paths.get(testsFile)));
            String valuesContent = new String(Files.readAllBytes(Paths.get(valuesFile)));

            // Преобразуем содержимое файлов в объекты JSON
            JSONObject testsJson = new JSONObject(testsContent);
            JSONArray valuesJson = new JSONArray(valuesContent);

            // Обрабатываем каждый тест в структуре tests.json
            processTests(testsJson, valuesJson);

            // Записываем результат в файл report.json
            FileWriter reportWriter = new FileWriter("report.json");
            reportWriter.write(testsJson.toString());
            reportWriter.close();

            System.out.println("Отчёт успешно сформирован в файле report.json.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файлов.");
        } catch (JSONException e) {
            System.out.println("Ошибка при обработке JSON.");
        }
    }

    private static void processTests(JSONObject testsJson, JSONArray valuesJson) throws JSONException {
        // Проверяем, является ли текущий объект JSON тестом
        if (testsJson.has("id") && testsJson.has("value")) {
            // Ищем соответствующий результат теста в values.json
            for (int i = 0; i < valuesJson.length(); i++) {
                JSONObject valueJson = valuesJson.getJSONObject(i);
                if (valueJson.getInt("id") == testsJson.getInt("id")) {
                    // Заполняем поле value текущего теста
                    testsJson.put("value", valueJson.getString("value"));
                    break;
                }
            }
        }

        // Обрабатываем вложенные тесты
        if (testsJson.has("values")) {
            JSONArray nestedTestsJson = testsJson.getJSONArray("values");
            for (int i = 0; i < nestedTestsJson.length(); i++) {
                JSONObject nestedTestJson = nestedTestsJson.getJSONObject(i);
                processTests(nestedTestJson, valuesJson);
            }
        }
    }
}