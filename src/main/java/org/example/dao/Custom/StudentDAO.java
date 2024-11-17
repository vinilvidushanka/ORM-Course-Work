package org.example.dao.Custom;

import org.example.dao.CrudDAO;
import org.example.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    List<Student> getAll();

}
