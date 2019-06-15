package ru.job4j.exam;

import java.util.*;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 14/06/2019
 */

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();
        Map<Integer, String> prev = new HashMap<>();
        Map<Integer, String> curr = new HashMap<>();
        for (User tmp : previous) {
            prev.put(tmp.id, tmp.name);
        }
        for (User tmp : current) {
            curr.put(tmp.id, tmp.name);
        }

        int added = 0;
        int changed = 0;
        int deleted = 0;

        for (Map.Entry<Integer, String> val : prev.entrySet()) {
            if (!curr.containsKey(val.getKey())) {
                deleted++;
            } else {
                if (!curr.get(val.getKey()).equals(val.getValue())) {
                    changed++;
                }
            }
        }

        for (Map.Entry<Integer, String> val : curr.entrySet()) {
            if (!prev.containsKey(val.getKey())) {
                added++;
            }
        }

        result.deleted = deleted;
        result.changed = changed;
        result.added = added;
        return result;
    }

    public static class User {
        int id;
        String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
    public static class Info {
        /**
         * Количество добавленных пользователей.
         */
        int added;

        /**
         * Количество измененных пользователей (меняется имя).
         */
        int changed;

        /**
         * Количество удаленных пользователей.
         */
        int deleted;
    }
}
