package com.anja.task1.app.data.ticket;

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
    private List<ImageFiles> files;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getType() {
        return type;
    }

    public void setType(Category type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(long create_date) {
        this.create_date = create_date;
    }

    public long getStart_date() {
        return start_date;
    }

    public void setStart_date(long start_date) {
        this.start_date = start_date;
    }

    public Category getState() {
        return state;
    }

    public void setState(Category state) {
        this.state = state;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public List<ImageFiles> getFiles() {
        return files;
    }

    public void setFiles(List<ImageFiles> files) {
        this.files = files;
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public int getLikes_counter() {
        return likes_counter;
    }

    public void setLikes_counter(int likes_counter) {
        this.likes_counter = likes_counter;
    }
}
