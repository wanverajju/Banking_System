package com.bank.app;
import java.util.Scanner;

import com.bank.dao.UserDAO;
import com.bank.dao.AccountDAO;
import com.bank.entity.User;
import com.bank.entity.Account;

public class BankingApp {

    static Scanner sc = new Scanner(System.in);
    static UserDAO userDAO = new UserDAO();
    static AccountDAO accountDAO = new AccountDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose option: ");
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1 : register();
                case 2 : login();
                case 3 :{
                    System.out.println("Exit successful.");
                    System.exit(0);
                }
                default : System.out.println("Invalid choice!");
            }
        }
    }

    static void register() {
        User user = new User();
        System.out.print("Enter ID: ");
        user.setUserId(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter Email: ");
        user.setEmail(sc.nextLine());
        System.out.print("Enter Password: ");
        user.setPassword(sc.nextLine());

        userDAO.register(user);
        System.out.println("Registration complete.");
    }

    static void login() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        User user = userDAO.login(id, pass);
        if (user != null) {
            System.out.println("Login successful! Welcome " + user.getEmail());
            userMenu(user);
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    static void userMenu(User user) {
        while (true) {
            System.out.println("\n1. Open Account\n2. Credit\n3. Debit\n4. Balance\n5. Exit");
            System.out.print("Option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 : openAccount(user);
                case 2 : creditMoney(user);
                case 3 : debitMoney(user);
                case 4 : checkBalance(user);
                case 5 : { return; }
                default : System.out.println("Invalid option!");
            }
        }
    }

    static void openAccount(User user) {
        Account acc = new Account();
        System.out.print("Enter Account Number: ");
        acc.setAccountNumber(sc.nextInt());
        acc.setUserId(user.getUserId());
        acc.setBalance(0.0);
        accountDAO.openAccount(acc);
        System.out.println("Account opened.");
    }

    static void creditMoney(User user) {
        System.out.print("Account Number: ");
        int accNo = sc.nextInt();
        System.out.print("Amount: ");
        double amt = sc.nextDouble();

        Account acc = accountDAO.getAccount(accNo);
        if (acc != null && acc.getUserId() == user.getUserId()) {
            acc.setBalance(acc.getBalance() + amt);
            accountDAO.openAccount(acc);
            System.out.println("Credited.");
        } else {
            System.out.println("Account not found.");
        }
    }

    static void debitMoney(User user) {
        System.out.print("Account Number: ");
        int accNo = sc.nextInt();
        System.out.print("Amount: ");
        double amt = sc.nextDouble();

        Account acc = accountDAO.getAccount(accNo);
        if (acc != null && acc.getUserId() == user.getUserId()) {
            if (amt <= acc.getBalance()) {
                acc.setBalance(acc.getBalance() - amt);
                accountDAO.openAccount(acc);
                System.out.println("Debited.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    static void checkBalance(User user) {
        System.out.print("Account Number: ");
        int accNo = sc.nextInt();

        Account acc = accountDAO.getAccount(accNo);
        if (acc != null && acc.getUserId() == user.getUserId()) {
            System.out.println("Balance: " + acc.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
} 

