package ru.job4j.exam;

import java.util.*;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 24/05/2019
 */

public class Departments {
    /**
     * Статический внутренний класс,
     * реализует интерфейс Comparable.
     * Объект данного класса - это отдельное подразделение.
     * Например, подразделение K1\SK1.
     *
     */
    public static final class Org implements Comparable<Org> {
        /**
         * Поле хранит список строк, которые обозначают иерархические уровни
         * данного подразделения.
         * Хранит уже разделенные строки (K1, SK1).
         */
        private final List<String> deps;

        /**
         * Конструктор.
         * @param deps полученный разделенный список (без "\").
         */
        public Org(List<String> deps) {
            this.deps = deps;
        }

        /**
         * Метод реализует аналогичный метод интерфейса Comparable.
         * @param o объект, с которым производится сравнение.
         * @return возвращает результат сравнения (1, 0, -1).
         */
        @Override
        public int compareTo(Org o) {
            int result = 0;
            int size = this.deps.size() < o.deps.size() ? this.deps.size() : o.deps.size();
            for (int i = 0; i < size; i++) {
                if (!(this.deps.get(i).equals(o.deps.get(i)))) {
                    result = this.deps.get(i).compareTo(o.deps.get(i));
                    break;
                }
            }
            if (result == 0) {
                result = this.deps.size() > o.deps.size() ? 1 : -1;
            }
             return result;
        }

        /**
         * Метод реализует аналогичный метод класса Object.
         * Используется для представления объекта в виде строки.
         * @return возвращает код подразделения в виде одной строки, разделенной "\".
         */
        @Override
        public String toString() {
            return String.join("/", this.deps);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Org org = (Org) o;
            return Objects.equals(deps, org.deps);
        }

        @Override
        public int hashCode() {
            return Objects.hash(deps);
        }
    }

    /**
     * Метод добавляет пропущенные строки в иерархии, если таковые имеются.
     * @param deps исходный список строк (с разделителями).
     * @return возвращает список строк с полной иерархией.
     */
    public List<Org> convert(List<String> deps) {
        List<Org> result = new ArrayList<>();
        for (String tmp : deps) {
            result.add(new Departments.Org(Arrays.asList(tmp.split("/"))));
        }
        List<Org> listTmp = new ArrayList<>();
        for (Org tmp : result) {
            if (tmp.deps.size() != 1) {
                listTmp.add(new Departments.Org(tmp.deps.subList(0, tmp.deps.size() - 1)));
            }
        }
        for (Org tmp : listTmp) {
            if (!(result.contains(tmp))) {
                result.add(tmp);
            }
        }
        return result;
    }


    /**
     * Метод для сортировки по возрастанию.
     * Использует компаратор отделов в естественном порядке.
     * @param orgs список отделов.
     * @return возвращает список, отсортированный по возрастанию.
     */
    public List<Org> sortAsc(List<Org> orgs) {
        orgs.sort(new Comparator<>() {
            @Override
            public int compare(Org o1, Org o2) {
                return o1.compareTo(o2);
            }
        });
        return orgs;
    }

    /**
     * Метод для сортировки по убыванию.
     * Сначала сравнивается превый элемент в иерархии по убыванию.
     * После этого элементы идут в обычном порядке.
     * @param orgs список отделов.
     * @return возвращает список, отсортированный по убыванию.
     */
    public List<Org> sortDesc(List<Org> orgs) {
        orgs.sort(new Comparator<>() {
            @Override
            public int compare(Org o1, Org o2) {
                int result;
                if (!(o1.deps.get(0).equals(o2.deps.get(0)))) {
                    result = o2.deps.get(0).compareTo(o1.deps.get(0));
                } else {
                    result = o1.compareTo(o2);
                }
                return result;
            }
        });
        return orgs;
    }
}
