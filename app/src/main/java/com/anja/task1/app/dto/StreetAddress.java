package com.anja.task1.app.dto;

/**
 * Created by Anna on 13.05.2016.
 */
public class StreetAddress{

    protected int id;
    protected String name;
    private String short_name;

    @Override
    public String toString() {
        return "StreetAddress{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", short_name='" + short_name + '\'' +
                '}';
    }
}
