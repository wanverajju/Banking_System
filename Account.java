package com.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_number")
    private int accountNumber;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "balance")
    private double balance;

    // Getters and Setters
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
