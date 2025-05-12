package com.bank.dao;

import org.hibernate.*;
import com.bank.entity.User;
import com.bank.util.HibernateUtil;

public class UserDAO {

    public void register(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);  // This will auto-generate the ID
        tx.commit();
        session.close();

        // After committing, the user object will have the auto-generated userId
        System.out.println("User registered with ID: " + user.getUserId());
    }

    public User login(int userId, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, userId);
        session.close();
        return user != null && user.getPassword().equals(password) ? user : null;
    }
}


