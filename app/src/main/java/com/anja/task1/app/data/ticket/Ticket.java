package com.anja.task1.app.data.ticket;

import java.io.File;
import java.util.List;

/**
 * Created by Anna on 12.05.2016.
 */
public class Ticket {

    private int id;
    private User user;
    private Category category;
    private Category type;
    private String title;
    private String body;
    private long create_date;
    private long start_date;
    private Category state;
    private String ticket_id;
    private List<File> files;
    private List<Performer> performers;
    private long deadline;
    private int likes_counter;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user=" + user +
                ", category=" + category +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", create_date=" + create_date +
                ", start_date=" + start_date +
                ", state=" + state +
                ", ticket_id='" + ticket_id + '\'' +
                ", files=" + files +
                ", performers=" + performers +
                ", deadline=" + deadline +
                ", likes_counter=" + likes_counter +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
