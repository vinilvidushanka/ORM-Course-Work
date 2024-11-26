package org.example.dao.Custom.impl;

import org.example.config.SessionFactoryConfig;
import org.example.dao.Custom.EnrollmentDAO;
import org.example.entity.Enrollment;
import org.example.entity.Programs;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {
    @Override
    public List<Enrollment> getAll() throws SQLException, ClassNotFoundException {
        List<Enrollment> all = new ArrayList<>();
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        all = session.createQuery("from Enrollment", Enrollment.class).list();
        transaction.commit();
        session.close();
        return all;
    }

    @Override
    public boolean save(Enrollment entity) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);

        Student student = entity.getStudent();
        Programs programs = entity.getPrograms();
        if (student != null) {
            student.addEnrollment(entity);
        }
        if (programs != null) {
            programs.addEnrollment(entity);
        }

        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Enrollment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Enrollment search(String id) {
        return null;
    }

    @Override
    public boolean IdExists(String id) throws SQLException, ClassNotFoundException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT COUNT(e.eid) FROM Enrollment e WHERE e.eid = :enrollment_id";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("enrollment_id", id);
            Long count = query.uniqueResult();
            return count != null && count > 0;
        }
    }

    @Override
    public boolean isStudentEnrolledInCourse(String studentId, String courseId) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT COUNT(e) FROM Enrollment e WHERE e.student.id = :studentId AND e.programs.id = :courseId";
            Long count = (Long) session.createQuery(hql)
                    .setParameter("studentId", studentId)
                    .setParameter("courseId", courseId)
                    .uniqueResult();
            return count > 0; // true if the student is already enrolled
        }
    }

    @Override
    public String generateNewID()throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String lastID = (String) session.createQuery("SELECT e.eid FROM Enrollment e ORDER BY e.eid DESC")
                    .setMaxResults(1)
                    .uniqueResult();

            if (lastID != null) {
                int id = Integer.parseInt(lastID.replace("E", "")) + 1;
                return "E" + String.format("%03d", id);
            } else {
                return "E001";
            }
        }
    }
}
