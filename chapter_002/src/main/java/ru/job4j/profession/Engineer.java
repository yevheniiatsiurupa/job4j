package ru.job4j.profession;

public class Engineer extends Profession {
    private String phdDegree;

    public Engineer(String name, String surname, String education, String birthday, String phdDegree) {
        super(name, surname, education, birthday);
        this.phdDegree = phdDegree;
    }

    public String getPhdDegree() {
        return this.phdDegree;
    }

    public boolean knowMath() {
        return true;
    }

    public void getMad(Client one) {
    }
}
