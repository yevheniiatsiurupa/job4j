package ru.job4j.servlets.models;

import java.util.Collection;

public class UsersJson {
    private Collection<UserJson> users;

    public UsersJson(Collection<UserJson> users) {
        this.users = users;
    }

    public UsersJson() {
    }

    public Collection<UserJson> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserJson> users) {
        this.users = users;
    }
}
