package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.EnrollmentDto;

import java.sql.SQLException;
import java.util.List;

public interface EnrollmentBO extends SuperBO {
    List<EnrollmentDto> getAllEnrollment() throws SQLException, ClassNotFoundException;

    boolean EnrollmentIdExists(String id) throws SQLException, ClassNotFoundException;

    boolean isStudentEnrolledInCourse(String sid, String cid) throws Exception;

    boolean saveEnrollment(EnrollmentDto enrollmentDto) throws Exception;

    String generateNewEnrollmentID() throws Exception;
}
