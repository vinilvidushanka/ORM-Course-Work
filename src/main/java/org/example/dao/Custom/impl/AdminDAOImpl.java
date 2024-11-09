package org.example.dao.Custom.impl;

import org.example.dao.Custom.AdminDAO;
import org.example.entity.Admin;
import org.hibernate.Session;

import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public List<Admin> getAll() {
        return null;
    }

    private Session session;
//    @Override
//    public void setSession(Session session) {
//        this.session = session;
//    }
    @Override
    public boolean save(Admin entity) {
        session.save(entity);
        return false;
    }

    @Override
    public void update(Admin entity) {

    }

    @Override
    public void delete(Admin entity) {

    }

    @Override
    public Admin search(String id) {
        return null;
    }

    @Override
    public int updateAdminUsername(String userName, String oldUsername) {
        return 0;
    }

    @Override
    public void setSession(Session session) {

    }
}
