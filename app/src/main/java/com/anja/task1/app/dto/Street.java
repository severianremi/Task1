package com.anja.task1.app.dto;

/**
 * Created by Anna on 13.05.2016.
 */
public class Street{

    protected int id;
    protected String name;
    protected String ru_name;
    private StreetAddress street_type;

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
