package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * конструкитор RegExp создает объект регулярного выражения для сопоставления текста с шаблоном
 * В RegExp проверяются такеи исключения, как - try/catch
 * @author Буянова Дарья 21ит35
 */
public class RegExp {
    public static void main(String[] args) throws Exception {
        String regExp = "\\d+\\s[+,\\-, *, %, /, ^]\\s\\d+"; //класс для чтения регулярного выражения
        double result = 0;
        String inputString;
        try (BufferedReader fr = new BufferedReader(new FileReader("src/com/company/input.txt"));
             BufferedWriter ad = new BufferedWriter(new FileWriter("src/com/company/output.txt"))) {

            while ((inputString = fr.readLine()) != null) {
                if ((inputString.trim().matches(regExp))) {
                    result = split(inputString.split(" "), result);
                    ad.write(result + "\n");
                    System.out.println(result);
                } else {
                    ad.write("Введены некорректные данные" + "\n");
                    System.out.println("Ввод не корректен!");
                }
            }
        }
    }

    /**
     * метод для работы с введенно пользователем строкой и поиска в нем нужных значений
     * @param array - массив для разделения строки на значения
     * @param previousResult - переменная, которая проверяет предыдущий результат
     * @return - используется для выполнения явного возврата из метода calculate
     * @throws Exception - обрабатывает исключения
     */
    private static double split(String[] array, double previousResult) throws Exception {
        String operand;
        double number1;
        double number2;
        if (array.length == 3) {
            number1 = Double.parseDouble(array[0]);
            operand = array[1];
            number2 = Double.parseDouble(array[2]);
            return calculate(number1, number2, operand);
        } else {
            throw new Exception("Ввод не корректен");
        }
    }

    /**
     * метод для реализации базовых функций калькулятора
     * @param number1 - значение 1
     * @param number2 - значение 2
     * @param operand - действие, которое должно быть применено к значению
     * @return - для возвращения результат действия
     * @throws Exception - для обрабатки исключения
     */
    private static double calculate(double number1, double number2, String operand) throws Exception {
        switch (operand) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                return number1 / number2;
            case "^":
                return Math.pow(number1, number2);
            case "%":
                return number1 % number2;
            default:
                throw new Exception("Ввод не корректен");
        }
    }
}
