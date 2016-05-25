package com.anja.task1.app.data.ticket;

/**
 * Created by Anna on 13.05.2016.
 */
public class Street{

    protected int id;
    protected String name;
    protected String ru_name;
    private StreetAddress street_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRu_name() {
        return ru_name;
    }

    public void setRu_name(String ru_name) {
        this.ru_name = ru_name;
    }

    public StreetAddress getStreet_type() {
        return street_type;
    }

    public void setStreet_type(StreetAddress street_type) {
        this.street_type = street_type;
    }

    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ru_name='" + ru_name + '\'' +
                ", street_type=" + street_type +
                '}';
    }
}
