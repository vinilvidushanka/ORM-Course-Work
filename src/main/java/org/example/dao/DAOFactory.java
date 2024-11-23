package org.example.dao;

import org.example.dao.Custom.impl.UserDAOImpl;
import org.example.dao.Custom.impl.ProgramsDAOImpl;
import org.example.dao.Custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER,STUDENT,PROGRAMS
    }

    public SuperDao getDAO(DAOTypes types) {
        switch (types) {
            case USER:
                return new UserDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case PROGRAMS:
                return new ProgramsDAOImpl();
            default:
                return null;
        }
    }
}
