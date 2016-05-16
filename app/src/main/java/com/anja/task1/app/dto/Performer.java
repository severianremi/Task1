package com.anja.task1.app.dto;

/**
 * Created by Anna on 12.05.2016.
 */
public class Performer {

    private int id;
    private String organization;
    private String person;
    private long deadline;

    @Override
    public String toString() {
        return "Performer{" +
                "id=" + id +
                ", organization='" + organization + '\'' +
                ", person='" + person + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
