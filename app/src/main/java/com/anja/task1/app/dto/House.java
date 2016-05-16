package com.anja.task1.app.dto;

/**
 * Created by Anna on 13.05.2016.
 */
public class House {

    private int id;
    private String name;

   @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
