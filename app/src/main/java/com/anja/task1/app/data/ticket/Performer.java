package com.anja.task1.app.data.ticket;

/**
 * Created by Anna on 12.05.2016.
 */
public class Performer {

    private int id;
    private String organization;
    private String person;
    private long deadline;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
