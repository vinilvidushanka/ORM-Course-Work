package org.example.dao;

import org.example.dao.Custom.impl.AdminDAOImpl;
import org.example.dao.Custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        ADMIN,STUDENT
    }

    public SuperDao getDAO(DAOTypes types) {
        switch (types) {
            case ADMIN:
                return new AdminDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            default:
                return null;
        }
    }
}
