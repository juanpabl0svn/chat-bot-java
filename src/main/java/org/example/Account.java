package org.example;

import java.text.NumberFormat;

public class Account {

    String number;
    String owner;
    float balance;
    float debt;
    String username;

    static NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public float getBalance() {
        System.out.println("Su saldo actual es de " + currencyInstance.format(balance));
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getDebt() {
        System.out.println("Su credito actual es de " + currencyInstance.format(debt));
        return debt;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Account(String number, String owner, String username, float balance, float debt){
        this.number = number;
        this.username = username;
        this.owner = owner;
        this.balance = balance;
        this.debt = debt;

    }
}
