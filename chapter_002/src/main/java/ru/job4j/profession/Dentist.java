package ru.job4j.profession;

public class Dentist extends Doctor {

    public Dentist(String name, String surname, String education, String birthday, String internship) {
        super(name, surname, education, birthday, internship);
    }

    public int countTeeth(Pacient pacient) {
        return 32;
    }
}
