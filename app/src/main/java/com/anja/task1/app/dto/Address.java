package com.anja.task1.app.dto;

import java.util.Dictionary;

/**
 * Created by Anna on 13.05.2016.
 */
public class Address{

    private int id;
    private AddressName district;
    private AddressName city;
    private Street street;
    private House house;
    private String flat;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", district=" + district +
                ", city=" + city +
                ", street=" + street +
                ", house=" + house +
                ", flat='" + flat + '\'' +
                '}';
    }
}
