package org.example;

public class User {
    public String name;
    public String surname;
    public String NIT;

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

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public User(String name, String surname, String NIT){
        this.name = name;
        this.surname = surname;
        this.NIT = NIT;
    }
}