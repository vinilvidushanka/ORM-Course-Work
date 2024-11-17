package org.example.dao.Custom;

import org.example.dao.CrudDAO;
import org.example.entity.Admin;
import org.hibernate.Session;

public interface AdminDAO extends CrudDAO<Admin> {
    int updateAdminUsername(String userName,String oldUsername);

    void setSession(Session session);
}
