package ru.job4j.exam;

import java.util.*;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 15/06/2019
 */

public class Script {
    /**
     * Метод возвращает список всех скриптов необходимых для запуска входящего скрипта.
     * @param scriptMap контейнер, хранящий пары скрипт - список скриптов для запуска.
     * @param scriptId входящий скрипт.
     * @return возвращает список скриптов для запуска.
     */
    public List<Integer> load(Map<Integer, List<Integer>> scriptMap, Integer scriptId) {
        HashSet<Integer> result = new HashSet<>();
        List<Integer> temp = scriptMap.get(scriptId);
        if (temp.size() != 0) {
            Queue<Integer> queue = new LinkedList<>();
            for (Integer in : temp) {
                queue.offer(in);
            }
            while (!queue.isEmpty()) {
                Integer out = queue.poll();
                result.add(out);
                List<Integer> outList = scriptMap.get(out);
                if (outList.size() != 0) {
                    for (Integer next : outList) {
                        queue.offer(next);
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }
}
