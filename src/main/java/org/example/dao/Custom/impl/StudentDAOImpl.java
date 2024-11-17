package org.example.dao.Custom.impl;

import org.example.dao.Custom.StudentDAO;
import org.example.entity.Student;
import org.hibernate.Session;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public boolean save(Student entity) {
        return false;
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
