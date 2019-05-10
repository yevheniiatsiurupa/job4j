package ru.job4j.exam;

public class CoffeeMachine {
    /**
     * Поле coins - массив номиналов монет, которые есть в автомате.
     */
    public int[] coins = new int[] {10, 5, 2, 1};

    /**
     * Метод для выдачи сдачи в автомате.
     * Массив coinsNumber - массив, в котором записывается количество необходимых монет каждого номинала.
     * Массив rst - результирующий массив, куда записываются номиналы монет, в количестве, указанном в coinsNumber.
     * @param value - внесенная сумма.
     * @param price - цена приобретенного товара
     * @return возвращает массив с набором монет для сдачи (минимальное количесвто монет)
     */
    public int[] change(int value, int price) {
        int change = value - price;
        int[] coinsNumber = new int[coins.length];
        for (int i = 0; i < coins.length; i++) {
            if (change >= coins[i]) {
                change -= coins[i];
                coinsNumber[i] += 1;
                i--;
                if (change == 0) {
                    break;
                }
            }
        }
        int size = 0;
        for (int i = 0; i < coinsNumber.length; i++) {
            size += coinsNumber[i];
        }
        int[] rst = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < coinsNumber.length; j++) {
                if (coinsNumber[j] > 0) {
                    rst[i] = coins[j];
                    coinsNumber[j] -=1 ;
                    j--;
                    i++;
                }
            }
            i--;
        }
        return rst;
    }
}