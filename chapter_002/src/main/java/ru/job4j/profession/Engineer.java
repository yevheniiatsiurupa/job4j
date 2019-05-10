package ru.job4j.profession;

public class Engineer extends Profession {
    private String phdDegree;

    public String getPhdDegree() {
        return this.phdDegree;
    }

    public boolean knowMath() {
        return true;
    }

    public void getMad(Client one) {
    }
}
