package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.config.SessionFactoryConfig;
import org.example.dao.Custom.AdminDAO;
import org.example.dao.Custom.impl.AdminDAOImpl;
import org.example.dao.DAOFactory;
import org.example.dto.AdminDto;
import org.example.entity.Admin;
import org.hibernate.Session;

public class AdminBOImpl implements AdminBO {
    public static Admin loggedAdmin;
    private Session session;
    AdminDAO adminDAO =(AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ADMIN);
    @Override
    public boolean saveAdmin(AdminDto admin) {
        AdminDAO adminDAO = new AdminDAOImpl();
        return adminDAO.save(new Admin(admin.getUserId(), admin.getUserName(),admin.getPassword()));
    }

    @Override
    public boolean isAdminExist(AdminDto dto) {
        session= SessionFactoryConfig.getInstance().getSession();
        adminDAO.setSession(session);
        try {
            Admin search = adminDAO.search(dto.getUserName());
            if (search != null) {
                if (search.getPassword().equals(dto.getPassword())) {
                    loggedAdmin = search;
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }
}

