package org.example.dao;

import org.example.dao.Custom.impl.AdminDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        ADMIN
    }

    public AdminDAOImpl getDAO(DAOTypes types) {
        switch (types) {
            case ADMIN:
                return new AdminDAOImpl();
            default:
                return null;
        }
    }
}
