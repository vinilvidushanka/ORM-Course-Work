package org.example.dao.Custom.impl;

import org.example.config.SessionFactoryConfig;
import org.example.dao.Custom.UserDAO;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM user");
        nativeQuery.addEntity(User.class);
        List<User> users = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public User search(String id) {
        return null;
    }

    @Override
    public boolean checkPassword(String username, String password) throws IOException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT password FROM user WHERE user_name = :username");
        nativeQuery.setParameter("username",username);

        String pass = nativeQuery.uniqueResult();

        transaction.commit();
        session.close();

        if (password.equalsIgnoreCase(pass)) {
            return true;
        }else {
            return false;
        }
    }
}
