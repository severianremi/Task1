package com.anja.task1.app.data.ticket;

/**
 * Created by Anna on 12.05.2016.
 */
public class User {

    private int id;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String email;
    private int birthday;
    private String phone;
    private Address address;
    private int fb_registered;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", fb_registered=" + fb_registered +
                '}';
    }
}
