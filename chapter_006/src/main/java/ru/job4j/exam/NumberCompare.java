package ru.job4j.exam;

import java.util.Scanner;

public class NumberCompare {
    public String sum(String first, String second) {
        if (first.isEmpty()) {
            return second;
        }
        if (second.isEmpty()) {
            return first;
        }
        int length1 = first.length();
        int length2 = second.length();
        int minLength = length1 < length2 ? length1 : length2;
        int maxLength = length1 > length2 ? length1 : length2;
        int extraAdd = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            int one = Character.getNumericValue(first.charAt(length1 - i - 1));
            int two = Character.getNumericValue(second.charAt(length2 - i - 1));
            int sum = extraAdd == 0 ? one + two : one + two + extraAdd;
            extraAdd = sum > 9 ? 1 : 0;
            sb.append(sum % 10);
        }
        if (length1 != length2) {
            String max = length1 > length2 ? first : second;
            for (int i = maxLength - minLength; i > 0; i--) {
                int one = Character.getNumericValue(max.charAt(i - 1));
                int sum = extraAdd == 0 ? one : one + extraAdd;
                extraAdd = sum > 9 ? 1 : 0;
                sb.append(sum % 10);
            }
        }
        if (extraAdd == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    public String findMin(String first, String second) {
        if (first.equals(second)) {
            return first;
        }
        int length1 = first.length();
        int length2 = second.length();
        if (length1 > length2) {
            return second;
        }
        if (length1 < length2) {
            return first;
        }
        return first.compareTo(second) < 0 ? first : second;
    }

    public static void main(String[] args) {
        NumberCompare nc = new NumberCompare();
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String str = scan.next();
        String sum = "0";
        String sum2 = "0";
        for (int i = n / 2; i < n - 1; i++) {
            if (str.charAt(i + 1) != '0') {
                String first = str.substring(0, i + 1);
                String second = str.substring(i + 1);
                sum = nc.sum(first, second);
                break;
            }
        }
        if (sum.equals("0")) {
            sum = str;
        }
        for (int i = n / 2 - 1; i >= 0; i--) {
            if (str.charAt(i + 1) != '0') {
                String first = str.substring(0, i + 1);
                String second = str.substring(i + 1);
                sum2 = nc.sum(first, second);
                break;
            }
        }
        if (sum2.equals("0")) {
            sum2 = str;
        }
        String result = nc.findMin(sum, sum2);
        System.out.println(result);
    }
}
