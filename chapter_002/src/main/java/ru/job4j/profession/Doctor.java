package ru.job4j.profession;

public class Doctor extends Profession {
    private String internship;
    private int magicPowersLevel;

    public Doctor(String name, String surname, String education, String birthday, String internship) {
        super(name, surname, education, birthday);
        this.internship = internship;
    }

    public String getInternship() {
        return this.internship;
    }

    public int getMagicPowersLevel() {
        return this.magicPowersLevel;
    }

    public Diagnose heal(Pacient first) {
        return new Diagnose();
    }

    public void getMad(Pacient first) {
    }

    public void setMagicPowersLevel(int level) {
        this.magicPowersLevel = level;
    }
}
