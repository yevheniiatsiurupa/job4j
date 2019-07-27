package ru.job4j.garb;

public class User {
    private String name;
    private String surname;
    private String nickname;
    private int age;
//    private long[] time;

    public User(String name, String surname, String nickname, int age) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.age = age;
//        this.time = new long[200];
    }


    @Override
    public void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }

    public static void main(String[] args) {
        System.out.println("start");
        for (int i = 0; i < 200; i++) {
            User u1 = new User("User1", "surname1", "nickname", 16);
            System.out.println("user" + i);
            u1 = null;
        }
        System.out.println("finish");
        System.out.println("finish");
    }
}
