package com.andreslab.tools_blind.models;

/**
 * Created by macbookpro on 7/25/17.
 */

public class ContactsModel {

    private int id;
    private String name;
    private String phone;
    private String email;


    public ContactsModel(int id, String name,  String phone, String email){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
