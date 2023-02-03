package com.example.taxesmanagementsystem.repository;

import com.example.taxesmanagementsystem.entity.RepairingWorkshop;
import com.example.taxesmanagementsystem.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RepairWorkshopRepository {

    public void save(RepairingWorkshop repairingWorkshop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(repairingWorkshop);
        transaction.commit();
        session.close();
    }


    public RepairingWorkshop findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from RepairingWorkshop where name =: name")
                .setParameter("name", name);
        RepairingWorkshop repairingWorkshop = (RepairingWorkshop) query.uniqueResult();
        return repairingWorkshop;
    }
}
