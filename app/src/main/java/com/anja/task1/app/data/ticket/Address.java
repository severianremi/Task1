package com.anja.task1.app.data.ticket;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AddressName getDistrict() {
        return district;
    }

    public void setDistrict(AddressName district) {
        this.district = district;
    }

    public AddressName getCity() {
        return city;
    }

    public void setCity(AddressName city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

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
