package com.example.taxesmanagementsystem.repository;

import com.example.taxesmanagementsystem.entity.TruckDriver;
import com.example.taxesmanagementsystem.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DriverRepository {

    public void save(TruckDriver truckDriver) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(truckDriver);
        transaction.commit();
        session.close();
    }
}
