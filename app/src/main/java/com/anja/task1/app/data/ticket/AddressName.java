package com.anja.task1.app.data.ticket;

/**
 * Created by Anna on 13.05.2016.
 */
public class AddressName {

    protected int id;
    protected String name;
    protected String ru_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AddressName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ru_name='" + ru_name + '\'' +
                '}';
    }
}