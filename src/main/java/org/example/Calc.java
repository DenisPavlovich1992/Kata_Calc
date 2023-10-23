package org.example;

import java.util.Arrays;
import java.util.List;

class Calc {
    public static int Calc(String input) throws Exception {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " не является римским числом");
        }

        return result;
    }
    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 100)) {
            throw new IllegalArgumentException(number + " is not in range (0,100]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static String arab_or_rome(String input) throws Exception {
        String[] num = input.toUpperCase().split(" ");
        if (isNumeric(num[0]) && isNumeric(num[2])) { // Если оба элемента - арабские числа
            return "АрабскиеЧисла: " + input;
        } else {
            try {
                int result = Calc(num[0]);// Пытаемся вычислить результат, если это римские числа
                result = Calc(num[2]);
                return "РимскиеЧисла: " + input;
            } catch (Exception e) {
                throw new IllegalArgumentException("Используются одновременно разные системы счисления " + input);
            }
        }
    }

    public static boolean isNumeric(String str) {
        for (char ch : str.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    public static boolean tenNumber(int[] num) {
        if (num[0]> 10 || num[1] > 10) {
            throw new IllegalArgumentException("Числа должны быть не больше 10");
        } else {
            return true;
        }
    }



    public static void Calculator(String input) throws Exception {
        String [] num = input.toUpperCase().split(" ");
        if(num.length==1){
            throw new IllegalArgumentException("строка не является математической операцией");
        }
        if(num.length != 3){
            throw new IllegalArgumentException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        //tenNumber(input);

        String [] input2 = arab_or_rome(input).split(" ");
        if (input2[0].equalsIgnoreCase("АрабскиеЧисла:")) {
            int[] test = {Integer.parseInt(input2[1]), Integer.parseInt(input2[3])};
            tenNumber(test);
            int res;
            switch (input2[2]) {
                case "+":
                    res = Integer.parseInt(input2[1]) + Integer.parseInt(input2[3]);
                    System.out.println(res);
                    break;
                case "-":
                    res = Integer.parseInt(input2[1]) - Integer.parseInt(input2[3]);
                    System.out.println(res);
                    break;
                case "*":
                    res = Integer.parseInt(input2[1]) * Integer.parseInt(input2[3]);
                    System.out.println(res);
                    break;
                case "/":
                    if (Integer.parseInt(input2[3]) == 0) {
                        throw new IllegalArgumentException("Делить на 0 нельзя");
                    }
                    res = Integer.parseInt(input2[1]) / Integer.parseInt(input2[3]);
                    System.out.println(res);
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный операнд");
            }

        } else if (input2[0].equalsIgnoreCase("РимскиеЧисла:")) {
            int[] test = {Calc(input2[1]), Calc(input2[3])};
            tenNumber(test);
            int res;
            switch (input2[2]) {
                case "+":
                    res = test[0] + test[1];
                    System.out.println(arabicToRoman(res));
                    break;
                case "-":
                    res = test[0] - test[1];
                    if(res<=0){
                        throw new IllegalArgumentException("в римской системе нет отрицательных чисел");
                    }
                    System.out.println(arabicToRoman(res));
                    break;
                case "*":
                    res = test[0] * test[1];
                    System.out.println(arabicToRoman(res));
                    break;
                case "/":
                    if (test[1] == 0) {
                        throw new IllegalArgumentException("Делить на 0 нельзя");
                    }
                    res = test[0] / test[1];
                    if(res==0){
                        throw new IllegalArgumentException("в римской системе нет отрицательных чисел");
                    }
                    System.out.println(arabicToRoman(res));
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный операнд");
            }

        } else {
            throw new IllegalArgumentException("Неизвестная ошибка");
        }
    }
}


