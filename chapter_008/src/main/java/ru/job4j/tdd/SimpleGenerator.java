package ru.job4j.tdd;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 03/07/2019
 */

public class SimpleGenerator {
    /**
     * Поле хранит строку с регулярным выражением для поиска.
     * \\$ поиск символа $ (экранированный)
     * \\} поиск символа } (экранированный)
     * (.+?) внутренняя группа, поиск любых символов (один и более), ленивый режим.
     */
    private final String defaultPattern = "\\$\\{(.+?)\\}";

    /**
     * Компилируем строку defaultPattern в шаблон KEYS.
     */
    private final Pattern pattern = Pattern.compile(defaultPattern);

    /**
     * Поле хранит список ключей, найденных в строке.
     */
    private Set<String> setKeys = new TreeSet<>();

    /**
     * Метод получает строку и список значений ключей.
     * Порядок действий.
     * Проверяем, что полученный список содержит ключи, если нет - выбрасываем исключение.
     * Создаем объект Matcher, передаем ему исходную строку,
     * в которой необходимо найти совпадения со стандартным регулярным выражением.
     * Производим поиск matcher.find().
     * В каждом найденном совпадении берем группу 1 (текст ключ) и добавляем в список setKeys.
     * Получаем список с ключами, найденными в исходном тексте.
     * Проверяем, что полученная карта map содержит все ключи из setKeys и не содержит лишних, если нет - исключение.
     * Проходим по всем ключам setKeys, вставляем их в новое регулярное выражение.
     * Производим замену всех совпадений нового выражения на соответствующее значение из карты.
     *
     * Пример template "I am ${name}, Who are ${subject}? "
     * Пример map name -> "Petr", subject -> "you"
     * Результат "I am Petr, Who are you?"
     *
     * @param template строка с исходным текстом.
     * @param map список значений, которыми необходимо заменить ключи.
     * @return возвращает строку template с замененными ключами.
     */
    public String generate(String template, Map<String, String> map) {
        if (map.isEmpty()) {
            throw new NoKeysException("Keys are not found.");
        }
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            this.setKeys.add(matcher.group(1));
        }
        if (map.keySet().size() > this.setKeys.size()) {
            throw new ExtraKeysException("Too many keys are found.");
        }
        if (!map.keySet().containsAll(this.setKeys)) {
            throw new NotEnoughKeysException("There are not enough keys.");
        }
        String result = template;
        for (String tmp : setKeys) {
            String repl = "\\$\\{" + tmp + "\\}";
            result = result.replaceAll(repl, map.get(tmp));
        }
        return result;
    }

    public static void main(String[] args) {
        String input = "My name is ${name}, you are ${subject}";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Jane");
        map.put("subject", "Alex");
        SimpleGenerator gen = new SimpleGenerator();
        System.out.println(gen.generate(input, map));
    }
}
