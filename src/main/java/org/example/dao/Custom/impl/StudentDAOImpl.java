package org.example.dao.Custom.impl;

import org.example.config.SessionFactoryConfig;
import org.example.dao.Custom.StudentDAO;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public boolean save(Student object) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public void delete(Student entity) {

    }

    @Override
    public Student search(String id) {
        return null;
    }


}
