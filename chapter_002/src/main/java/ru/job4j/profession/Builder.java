package ru.job4j.profession;

public class Builder extends Engineer {

    public Builder(String name, String surname, String education, String birthday, String phdDegree) {
        super(name, surname, education, birthday, phdDegree);
    }

    public Drawing makeDrawing() {
        return new Drawing();
    }
}
