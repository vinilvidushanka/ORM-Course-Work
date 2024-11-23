package org.example.bo.custom.impl;

import org.example.bo.custom.StudentBO;
import org.example.dao.Custom.StudentDAO;
import org.example.dao.Custom.impl.StudentDAOImpl;
import org.example.dao.DAOFactory;
import org.example.dto.StudentDto;
import org.example.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
//    StudentDAO studentDAO = new StudentDAOImpl();
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean save(StudentDto studentDto) throws SQLException, ClassNotFoundException {
        return studentDAO.save(new Student(studentDto.getId(), studentDto.getName(), studentDto.getAddress(), studentDto.getContact(), studentDto.getBirthDay(), studentDto.getGender()));
    }

    @Override
    public boolean update(StudentDto studentDto) {
        return false;
    }

    @Override
    public boolean delete(StudentDto studentDto) {
        return false;
    }

    @Override
    public boolean search(StudentDto studentDto) {
        return false;
    }

    @Override
    public StudentDto searchStudent(String studentId) {
        return null;
    }

    @Override
    public List<StudentDto> getAllStudents() throws SQLException, ClassNotFoundException {
        List<Student> all=studentDAO.getAll();
        List<StudentDto> list = new ArrayList<>();

        if (all == null) {
            return null;
        } else {
            for (Student student: all){
                list.add(new StudentDto(student.getId(),student.getName(),student.getAddress(),student.getContact(),student.getBirthDay(),student.getGender()));
            }
            return list;
        }
    }

    @Override
    public String generateNewId() {
        return null;
    }
}
