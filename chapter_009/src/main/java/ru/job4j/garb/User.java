package ru.job4j.garb;

public class User {
    private String name;
    private int age;
    private long[] time;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.time = new long[20000];
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }

    public static void main(String[] args) {
        System.out.println("start");
        User u1 = new User("user1", 19);
        System.out.println(u1);
        u1 = null;
        User u2 = new User("user2", 19);
        System.out.println(u2);
        u2 = null;
        User u3 = new User("user3", 19);
        System.out.println(u3);
        u3 = null;

        System.out.println("finish");
    }
}
