package ru.job4j.map;

import java.util.HashMap;
import java.util.List;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 16/05/2019
 */

public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User tmp : list) {
            result.put(tmp.getId(), tmp);
        }
        return result;
    }
}
