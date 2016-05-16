package com.anja.task1.app.dto;

/**
 * Created by Anna on 13.05.2016.
 */
public class AddressName {

    protected int id;
    protected String name;
    protected String ru_name;

    public String getRu_name() {
        return ru_name;
    }

    public void setRu_name(String ru_name) {
        this.ru_name = ru_name;
    }

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

    @Override
    public String toString() {
        return "AddressName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ru_name='" + ru_name + '\'' +
                '}';
    }
}
