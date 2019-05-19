package ru.job4j.bank;

import java.util.Objects;

/**
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 19/05/2019
 */

public class User implements Comparable<User> {
    private String name;
    private String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return passport.equals(user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }

    @Override
    public int compareTo(User o) {
        return this.passport.compareTo(o.passport);
    }
}
