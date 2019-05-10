package ru.job4j.profession;

public class Doctor extends Profession {
    private String internship;
    private int magicPowersLevel;

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
}
