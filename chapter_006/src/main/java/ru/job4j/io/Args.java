package ru.job4j.io;

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
        return arguments[dir + 1];
    }

    /**
     * Метод находит строку, следующую после -e.
     * @return возвращает подстроку, начиная после символа ".".
     */
    public String exclude() {
        int excl = -1;
        for (int i = 0; i < this.arguments.length; i++) {
            if (arguments[i].equals("-e")) {
                excl = i;
                break;
            }
        }
        String extension = arguments[excl + 1];
        return extension.substring(extension.indexOf("."));
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
        return arguments[out + 1];
    }
}
