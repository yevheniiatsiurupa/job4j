package ru.job4j.profession;

public class Programmer extends Engineer {
    private String programmingLanguage;

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public Program writeCode() {
        return new Program();
    }
}
