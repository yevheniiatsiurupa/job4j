package ru.job4j.threads.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.
 * @since 31/07/2019.
 * @author Evgeniya Tsiurupa
 */

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private List<User> storage = new ArrayList<>();

    public synchronized boolean add(User user) {
        return this.storage.add(user);
    }

    public synchronized boolean delete(User user) {
        return this.storage.remove(user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = null;
        User to = null;
        boolean result = false;
        for (User tmp : storage) {
            if (tmp.getId() ==  fromId) {
                from = tmp;
                break;
            }
        }
        for (User tmp : storage) {
            if (tmp.getId() ==  toId) {
                to = tmp;
                break;
            }
        }
        if (from != null && to != null) {
            result = true;
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
        }
        return result;
    }
}
