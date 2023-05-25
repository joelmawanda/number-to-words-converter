package com.numbertowords;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberToWordsConverter {
    private static final String[] units = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] tens = {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private static final String[] scales = {
            "", "thousand", "million", "billion"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long number = 0;

        try {
            System.out.print("Enter a number (0 - 100000000000): ");
            number = scanner.nextLong();
//            if(number > 100000000000){
//
//            }
            String words = convertNumberToWords(number);
            System.out.println("The entered number " + number + " in words is " + words);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }
        scanner.close();
    }

    public static String convertNumberToWords(long number) {
        try {
            if (number == 0) {
                return "zero";
            }

            StringBuilder words = new StringBuilder();

            for (String scale : scales) {
                if (number % 1000 != 0) {
                    words.insert(0, convertNumber(number % 1000) + " " + scale + " ");
                }
                number /= 1000;
            }
            return words.toString().trim();
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getLocalizedMessage());
        }

        return "The number " + number + " is out of range";
    }

    private static String convertNumber(long number) {
        if (number == 0) {
            return "";
        }

        if (number < 20) {
            return units[(int) number];
        }

        if (number < 100) {
            return tens[(int) (number / 10)] + " " + convertNumber(number % 10);
        }

        return units[(int) (number / 100)] + " hundred " + convertNumber(number % 100);
    }
}
