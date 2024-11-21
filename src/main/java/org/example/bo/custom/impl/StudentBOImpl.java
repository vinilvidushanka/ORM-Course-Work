package org.example.bo.custom.impl;

import org.example.bo.custom.StudentBO;
import org.example.dao.Custom.StudentDAO;
import org.example.dao.Custom.impl.StudentDAOImpl;
import org.example.dto.StudentDto;
import org.example.entity.Student;

import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public boolean save(StudentDto studentDto) {
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
    public List<StudentDto> getAllStudents() {
        return null;
    }

    @Override
    public String generateNewId() {
        return null;
    }
}
