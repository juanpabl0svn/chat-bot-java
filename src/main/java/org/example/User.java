package org.example;

import com.mysql.cj.xdevapi.Client;

public class User {
    public String email;
    public String nit;
    public String name;
    public String surname;


    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String nit, String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.nit = nit;
        this.email = email;
    }

    public static void main(String[] args){

    }

}