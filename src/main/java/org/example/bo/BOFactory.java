package org.example.bo;

import org.example.bo.custom.impl.AdminBOImpl;

public class BOFactory {
    private static BOFactory bOFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return (bOFactory==null)?bOFactory=new BOFactory():bOFactory;
    }

    public enum BOTypes{
        ADMIN
    }

    public SuperBO getBO(BOTypes boTypes){
        switch(boTypes){
            case ADMIN:
                return new AdminBOImpl();
            default:
                return null;
        }
    }
}
