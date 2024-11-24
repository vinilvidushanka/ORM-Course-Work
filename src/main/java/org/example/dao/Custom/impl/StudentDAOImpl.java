package org.example.dao.Custom.impl;

import org.example.config.SessionFactoryConfig;
import org.example.dao.Custom.StudentDAO;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException{
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM student");
        nativeQuery.addEntity(Student.class);
        List<Student> students = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return students;
    }





    @Override
    public boolean save(Student object) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DELETE FROM student WHERE student_id = :id";
        NativeQuery<Student> nativeQuery = session.createNativeQuery(sql);
        nativeQuery.setParameter("id",id);
        nativeQuery.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public Student search(String id) {
        return null;
    }


}
