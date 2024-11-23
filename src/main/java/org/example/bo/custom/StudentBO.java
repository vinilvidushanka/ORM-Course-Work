package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.StudentDto;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean save(StudentDto studentDto) throws SQLException, ClassNotFoundException;
    public boolean update(StudentDto studentDto);
    public boolean delete(StudentDto studentDto);
    public boolean search(StudentDto studentDto);
    StudentDto searchStudent(String studentId);

    public List<StudentDto> getAllStudents() throws SQLException, ClassNotFoundException;

    String generateNewId();
}
