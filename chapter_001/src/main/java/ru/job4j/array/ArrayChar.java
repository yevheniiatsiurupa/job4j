package ru.job4j.array;
/**
 * Программа проверяет,что строка начинается с определенных символов.
 * @author Evgeniya Tsiurupa.
 * @version 1.0.
 * @since 14/04/19.
 */
public class ArrayChar {
    private char[] data;
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет, что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинается с префикса.
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        //делаем массив char из строки префикса.
        char[] value = prefix.toCharArray();
        //проходим все элементы массива value.
        for (int i = 0; i != value.length; i++) {
            //сравнение элементов value и data.
            if (value[i] != data[i]) {
                result = false;
            }
        }
        return result;
    }
}
