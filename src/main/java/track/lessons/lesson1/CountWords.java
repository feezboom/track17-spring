package track.lessons.lesson1;

import sun.misc.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * Задание 1: Реализовать два метода
 *
 * Формат файла: текстовый, на каждой его строке есть (или/или)
 * - целое число (int)
 * - текстовая строка
 * - пустая строка (пробелы)
 *
 *
 * Пример файла - words.txt в корне проекта
 *
 * ******************************************************************************************
 *  Пожалуйста, не меняйте сигнатуры методов! (название, аргументы, возвращаемое значение)
 *
 *  Можно дописывать новый код - вспомогательные методы, конструкторы, поля
 *
 * ******************************************************************************************
 *
 */
public class CountWords {

    /**
     * Метод на вход принимает объект File, изначально сумма = 0
     * Нужно пройти по всем строкам файла, и если в строке стоит целое число,
     * то надо добавить это число к сумме
     * @param file - файл с данными
     * @return - целое число - сумма всех чисел из файла
     */
    public long countNumbers(File file) throws Exception {
        Scanner scanner = new Scanner(file);
        long acc = 0;
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            try {
                acc += Integer.parseInt(nextLine);
            } catch (Exception ignored) { }
        }
        return acc;
    }


    /**
     * Метод на вход принимает объект File, изначально результат= ""
     * Нужно пройти по всем строкам файла, и если в строка не пустая и не число
     * то надо присоединить ее к результату через пробел
     * @param file - файл с данными
     * @return - результирующая строка
     */
    public String concatWords(File file) throws Exception {
        Scanner scanner = new Scanner(file);
        StringBuilder acc = new StringBuilder();
        boolean first = true;
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            if (nextLine.isEmpty()) {
                continue;
            }

            try {
                Integer.parseInt(nextLine);
                // If successfully parsed - do nothing.
            } catch (Exception e) {
                // We found a word if parseInt failed.
                if (first) {
                    first = false;
                } else {
                    acc.append(" ");
                }

                acc.append(nextLine);
            }
        }
        return acc.toString();
    }

}
