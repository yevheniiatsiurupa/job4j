package ru.job4j.exam;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 21/06/2019
 */

public class Args {
    /**
     * Поле хранит массив аргументов.
     */
    private String[] arguments;

    public Args(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Метод возвращает строку, следующую после -d.
     */
    public String directory() {
        int dir = -1;
        for (int i = 0; i < this.arguments.length; i++) {
            if (arguments[i].equals("-d")) {
                dir = i;
                break;
            }
        }
        if (dir == -1) {
            throw new InvalidInputException("Не найден ключ -d.");
        }
        return arguments[dir + 1];
    }

    /**
     * Метод находит строку, следующую после -n.
     */
    public String name() {
        int nam = -1;
        for (int i = 0; i < this.arguments.length; i++) {
            if (arguments[i].equals("-n")) {
                nam = i;
                break;
            }
        }
        if (nam == -1) {
            throw new InvalidInputException("Не найден ключ -n.");
        }
        return arguments[nam + 1];
    }

    /**
     * Метод находит строку -m или -f.
     */
    public String filterType() {
        int filt = -1;
        for (int i = 0; i < this.arguments.length; i++) {
            if (arguments[i].equals("-m") || arguments[i].equals("-f")) {
                filt = i;
                break;
            }
        }
        if (filt == -1) {
            throw new InvalidInputException("Не найден ключ -f / -m.");
        }
        return arguments[filt];
    }


    /**
     * Метод возвращает строку, следующую после -o
     */
    public String output() {
        int out = -1;
        for (int i = 0; i < this.arguments.length; i++) {
            if (arguments[i].equals("-o")) {
                out = i;
                break;
            }
        }
        if (out == -1) {
            throw new InvalidInputException("Не найден ключ -o.");
        }
        return arguments[out + 1];
    }
}
