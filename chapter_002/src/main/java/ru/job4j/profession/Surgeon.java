package ru.job4j.profession;

public class Surgeon extends Doctor {

    public Surgeon(String name, String surname, String education, String birthday, String internship) {
        super(name, surname, education, birthday, internship);
    }

    public boolean findHeart(Pacient first) {
        return true;
    }
}
