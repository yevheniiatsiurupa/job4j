package ru.job4j.profession;

public class Programmer extends Engineer {
    private String programmingLanguage;

    public Programmer(String name, String surname, String education, String birthday, String phdDegree) {
        super(name, surname, education, birthday, phdDegree);
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String language) {
        this.programmingLanguage = language;
    }

    public Program writeCode() {
        return new Program();
    }
}
