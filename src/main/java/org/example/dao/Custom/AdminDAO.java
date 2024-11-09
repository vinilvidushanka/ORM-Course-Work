package org.example.dao.Custom;

import org.example.dao.CrudDAO;
import org.example.entity.Admin;

public interface AdminDAO extends CrudDAO<Admin> {
    int updateAdminUsername(String userName,String oldUsername);
}
