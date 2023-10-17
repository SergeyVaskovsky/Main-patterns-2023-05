package ru.otus.project.model;

public class Text {

    private String filename;
    private String commit;
    private int rowNumber;

    public Text(String filename, String commit, int rowNumber) {
        this.filename = filename;
        this.commit = commit;
        this.rowNumber = rowNumber;
    }
}
