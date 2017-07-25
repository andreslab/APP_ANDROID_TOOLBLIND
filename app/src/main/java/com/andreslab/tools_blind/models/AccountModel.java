package com.andreslab.tools_blind.models;

/**
 * Created by macbookpro on 7/25/17.
 */

public class AccountModel {
    private int id;
    private String email;
    private String pass;

    public AccountModel(int id, String email, String pass){
        this.id = id;
        this.email = email;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
