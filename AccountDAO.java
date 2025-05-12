package com.bank.dao;
	import org.hibernate.*;
	import com.bank.entity.Account;
	import com.bank.util.HibernateUtil;

	public class AccountDAO {
	    public void openAccount(Account account) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
	        session.save(account);
	        tx.commit();
	        session.close();
	    }

	    public Account getAccount(int accNum) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Account acc = session.get(Account.class, accNum);
	        session.close();
	        return acc;
	    }
	}

