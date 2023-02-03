package com.example.taxesmanagementsystem.repository;

import com.example.taxesmanagementsystem.entity.Company;
import com.example.taxesmanagementsystem.entity.RepairingWorkshop;
import com.example.taxesmanagementsystem.entity.Vehicle;
import com.example.taxesmanagementsystem.exceptions.RegNumberException;
import com.example.taxesmanagementsystem.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class VehiclesRepository {

    public void save(Vehicle vehicle) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(vehicle);
        transaction.commit();
        session.close();
    }

    public void createNewVehicle(String regNumber, String name, String setBrand, String emissionLevel, Company company) throws RegNumberException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        //check if new entry not duplicated
        Query query = session.createQuery("from Vehicle where regNumber = :regNumber");
        query.setParameter("regNumber", regNumber);
        if (!query.list().isEmpty()) {
            throw new RegNumberException("Registration number is already in use.");
        }

        Vehicle newVehicle = new Vehicle();
        newVehicle.setRegNumber(regNumber);
        newVehicle.setBrand(name);
        newVehicle.setName(setBrand);
        newVehicle.setCompany(company);
        newVehicle.setEmissionLevel(emissionLevel);
        session.save(newVehicle);
        session.save(newVehicle);

        transaction.commit();
        session.close();
        System.out.println("new vehicle added");
    }


    public Vehicle findByRegNumber(String regNumber) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Vehicle where regNumber =: regNumber")
                .setParameter("regNumber", regNumber);
        Vehicle vehicle = (Vehicle) query.uniqueResult();
        return vehicle;
    }


    public void asignVehileToCompany(Vehicle vehicle, Company company) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("UPDATE Vehicle SET company_id =: company_id where id =: id")
                .setParameter("company_id", company.getId())
                .setParameter("id", vehicle.getId())
                .executeUpdate();

        transaction.commit();
        session.close();
        System.out.println("vehicle asigned=> " + vehicle + "->" + company);
    }

    public void asignVehicleToWorkshop(Vehicle vehicle, RepairingWorkshop repairingWorkshop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("UPDATE RepairingWorkshop SET vehicle_id =: vehicle_id where id =: id")
                .setParameter("vehicle_id", vehicle.getId())
                .setParameter("id", repairingWorkshop.getId())
                .executeUpdate();

        transaction.commit();
        session.close();
        System.out.println("workshop asigned=> " + vehicle + "->" + repairingWorkshop);

    }
}
