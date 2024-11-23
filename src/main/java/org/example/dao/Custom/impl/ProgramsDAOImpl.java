package org.example.dao.Custom.impl;

import org.example.config.SessionFactoryConfig;
import org.example.dao.Custom.ProgramsDAO;
import org.example.entity.Programs;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ProgramsDAOImpl implements ProgramsDAO {
    @Override
    public List<Programs> getAll() throws SQLException, ClassNotFoundException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Programs");
        List<Programs> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public boolean save(Programs entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void update(Programs entity) {

    }

    @Override
    public void delete(Programs entity) {

    }

    @Override
    public Programs search(String id) {
        return null;
    }
}
