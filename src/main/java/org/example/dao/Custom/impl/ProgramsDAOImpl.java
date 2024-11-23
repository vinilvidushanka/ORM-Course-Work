package org.example.dao.Custom.impl;

import org.example.config.SessionFactoryConfig;
import org.example.dao.Custom.ProgramsDAO;
import org.example.entity.Programs;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.util.List;

public class ProgramsDAOImpl implements ProgramsDAO {
    @Override
    public List<Programs> getAll() throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM programs");
        nativeQuery.addEntity(Programs.class);
        List<Programs> programs = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return programs;
    }

    @Override
    public boolean save(Programs entity) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public void update(Programs entity) {

    }

    @Override
    public boolean delete(String id) {

        return false;
    }

    @Override
    public Programs search(String id) {
        return null;
    }
}
