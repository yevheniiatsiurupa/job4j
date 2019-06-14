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
        HashSet<User> tmp = new HashSet<>(previous);
        HashSet<User> tmp2 = new HashSet<>(current);
        List<Integer> delChan = new ArrayList<>();
        List<Integer> chanAdd = new ArrayList<>();

        boolean isAdded = false;
        for (User us : current) {
            isAdded = tmp.add(us);
            if (isAdded) {
                chanAdd.add(us.id);
            }
        }

        for (User us : previous) {
            isAdded = tmp2.add(us);
            if (isAdded) {
                delChan.add(us.id);
            }
        }

        HashSet<Integer> delChanAdd = new HashSet<>(delChan);
        delChanAdd.addAll(chanAdd);

        result.deleted = delChanAdd.size() - chanAdd.size();
        result.changed = delChan.size() + chanAdd.size() - delChanAdd.size();
        result.added = delChanAdd.size() - delChan.size();
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
