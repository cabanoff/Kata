//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Введите выражение (или 'exit' для выхода):");
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Программа завершена.");
                break;
            }
            try {
                String result = calc(input);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    public static String calc(String input) throws Exception {
        String[] parts = input.split("\\s+");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Неправильный формат ввода");
        }

        String first = parts[0]; // Исправлено на parts[0]
        String operator = parts[1]; // Исправлено на parts[1]
        String second = parts[2]; // Исправлено на parts[2]

        System.out.println("Программа завершена.");

        int num1, num2;
        boolean isRoman = false;

        // Проверка на валидность чисел и их преобразование в числовой формат
        try {
            num1 = Integer.parseInt(first);
            num2 = Integer.parseInt(second);
        } catch (NumberFormatException e) {
            try {
                num1 = RomanConverter.romanToArabic(first);
                num2 = RomanConverter.romanToArabic(second);
                isRoman = true;
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Неправильный формат чисел");
            }
        }

        System.out.println(num1);
        System.out.println(num2);

        // Проверка на диапазон чисел
        if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
            throw new IllegalArgumentException("Числа должны быть в диапазоне от 1 до 10");
        }

        int result;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                // Проверка на отрицательный результат при использовании римских чисел
                if (isRoman && result < 1) {
                    throw new IllegalArgumentException("Отрицательный результат невозможен при работе с римскими числами");
                }
                break;
            case "*": // Исправлено на "*"
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Неправильный оператор");
        }

        if (isRoman) {
            return RomanConverter.arabicToRoman(result);
        } else {
            return Integer.toString(result);
        }
    }


    static class RomanConverter {

        // Массивы для хранения римских цифр и их значений
        private static final String[] ROMAN_DIGITS = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        private static final int[] ROMAN_VALUES = {1, 4, 5, 9, 10, 40, 50, 90, 100};

        // Метод для преобразования римского числа в арабское
        public static int romanToArabic(String roman) {
            int result = 0;
            int i = 0;

            // Проверка на недопустимые последовательности
            if (roman.matches(".*IIII.*|.*VV.*|.*XXXX.*|.*LL.*|.*CCCC.*")) {
                throw new IllegalArgumentException("Недопустимая последовательность римских цифр");
            }

            while (i < roman.length()) {
                // Получаем значение текущего римского символа
                int value = getValue(roman.charAt(i));

                // Если это не последний символ и значение следующего символа больше,
                // значит это случай вычитания, например "IV"
                if (i + 1 < roman.length() && getValue(roman.charAt(i + 1)) > value) {
                    result += getValue(roman.charAt(i + 1)) - value;
                    i += 2; // Переходим через оба символа
                } else {
                    result += value;
                    i++;
                }
            }
            return result;
        }

        // Метод для преобразования арабского числа в римское
        public static String arabicToRoman(int arabic) {
            if (arabic < 1 || arabic > 3999) {
                throw new IllegalArgumentException("Число должно быть в диапазоне от 1 до 3999");
            }

            StringBuilder roman = new StringBuilder();
            int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

            for (int i = 0; i < values.length; i++) {
                while (arabic >= values[i]) {
                    arabic -= values[i];
                    roman.append(symbols[i]);
                }
            }

            return roman.toString();
        }


        // Вспомогательный метод для получения значения римского символа
        private static int getValue(char r) {
            switch (r) {
                case 'I': return 1;
                case 'V': return 5;
                case 'X': return 10;
                case 'L': return 50;
                case 'C': return 100;
                case 'D': return 500;
                case 'M': return 1000;
                default: throw new IllegalArgumentException("Недопустимый римский символ");
            }
        }
    }


}
