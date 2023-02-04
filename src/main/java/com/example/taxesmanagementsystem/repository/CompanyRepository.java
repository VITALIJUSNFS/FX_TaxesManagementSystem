package com.example.taxesmanagementsystem.repository;

import com.example.taxesmanagementsystem.entity.Company;
import com.example.taxesmanagementsystem.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.example.taxesmanagementsystem.utils.DatabaseUtils.dbConnection;

public class CompanyRepository {



    public List<Company> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Company> allCompanies = session.createQuery("SELECT * FROM company", Company.class).list();
//        for (Company allCompany : allCompanies) {
//            System.out.println("\t" + allCompany.getId() + "\t" + allCompany.getName() + "\t" + allCompany.getCountry());
//        }
        session.close();
        return allCompanies;
    }

    public void save(Company company) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(company);
        transaction.commit();
        session.close();
    }

    public void addNewCompany(String name, String country, Integer budget) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Company company = new Company();
        company.setName(name);
        company.setCountry(country);
        company.setBudget(budget);
        session.save(company);

        transaction.commit();
        session.close();
        System.out.println("new company created");
    }


    public Company findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Company where name= :name");
        query.setParameter("name", name);
        Company company = (Company) query.uniqueResult();
        return company;
    }

    public void update(String name, String country, Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("UPDATE Company SET name = :name, country = :country where id= :id")
                .setParameter("name", name)
                .setParameter("country", country)
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("Company data modified");
    }

    public void delete(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE from Company where name= :name")
                .setParameter("name", name)
                .executeUpdate();
        System.out.println("Company with name " + name + " deleted");
        transaction.commit();
        session.close();
    }

    //JDBC findAllCompanies
    public static List<Company> constructCompaniesList(ResultSet resultSet) throws SQLException {
        List<Company> companies = new ArrayList<>();
        while (resultSet.next()) {
            Company company = new Company();
            company.setId(resultSet.getLong("id"));
            company.setName(resultSet.getString("name"));
            company.setCountry(resultSet.getString("country"));
            company.setBudget(resultSet.getInt("budget"));
            companies.add(company);
        }
        return companies;
    }

    public List<Company> findAllCompanies() {
        Statement statement = null;
        List<Company> companies = null;
        try {
            statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM company");
            companies = constructCompaniesList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    //Find company by Name
    public List<Company> findCompanyByName(String name) throws SQLException {
        Statement statement = null;
        List<Company> companyListByName = new ArrayList<>();
        try {
            statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM company WHERE name = '%s';", name));
            companyListByName = constructCompaniesList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyListByName;
    }

    // - delete company by name,
    public void deleteCompany(String name) {
        Statement statement;
        try {
            dbConnection.createStatement().executeUpdate(String.format("DELETE FROM company WHERE name = '%s';", name));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // - save new company
    public void saveNewCompany(Integer budget, String country, String name) {
        String sql = "INSERT INTO company(budget, country, name) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, budget);
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Visi laukai turi but ivesti");
            e.printStackTrace();
        }
    }
}
