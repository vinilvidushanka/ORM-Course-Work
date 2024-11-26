package org.example.bo.custom.impl;

import org.example.bo.custom.EnrollmentBO;
import org.example.dao.Custom.EnrollmentDAO;
import org.example.dao.Custom.ProgramsDAO;
import org.example.dao.Custom.StudentDAO;
import org.example.dao.DAOFactory;
import org.example.dto.EnrollmentDto;
import org.example.entity.Enrollment;
import org.example.entity.Programs;
import org.example.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentBOImpl implements EnrollmentBO {

    EnrollmentDAO enrollmentDAO = (EnrollmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ENROLLMENT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    ProgramsDAO programsDAO = (ProgramsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PROGRAMS);

    @Override
    public List<EnrollmentDto> getAllEnrollment() throws SQLException, ClassNotFoundException {
        List<Enrollment> enrollments = enrollmentDAO.getAll();
        List<EnrollmentDto> dtos = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            String studentId = enrollment.getStudent() != null ? enrollment.getStudent().getId() : null;
            String studentName = enrollment.getStudent() != null ? enrollment.getStudent().getName() : null;
            String programId = enrollment.getPrograms() != null ? enrollment.getPrograms().getId() : null;
            String programName = enrollment.getPrograms() != null ? enrollment.getPrograms().getName() : null;
            dtos.add(new EnrollmentDto(enrollment.getEid(),studentId,studentName,programId,programName,enrollment.getDate(),enrollment.getUpfrontpayment(),enrollment.getRemainingfee()));
        }
        return dtos;
    }

    @Override
    public boolean EnrollmentIdExists(String enrollmentId) throws SQLException, ClassNotFoundException {
        return enrollmentDAO.IdExists(enrollmentId);
    }

    @Override
    public boolean isStudentEnrolledInCourse(String studentId, String courseId) throws Exception {
        return enrollmentDAO.isStudentEnrolledInCourse(studentId,courseId);
    }

    @Override
    public boolean saveEnrollment(EnrollmentDto enrollmentDto) throws Exception {
        Student student = studentDAO.getStudentById(enrollmentDto.getSid());
        Programs programs = programsDAO.getProgramById(enrollmentDto.getCid());


        if (student == null || programs == null) {
            throw new Exception("Student or Course not found.");
        }

        Enrollment enrollment = new Enrollment(
                enrollmentDto.getEid(),
                student,
                programs,
                enrollmentDto.getDate(),
                enrollmentDto.getUpfrontpayment(),
                enrollmentDto.getRemainingfee()
        );

        return enrollmentDAO.save(enrollment);
    }

    @Override
    public String generateNewEnrollmentID() throws Exception {
        return enrollmentDAO.generateNewID();
    }
}