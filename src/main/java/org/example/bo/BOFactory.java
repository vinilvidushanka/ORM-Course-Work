package org.example.bo;

import org.example.bo.custom.impl.UserBOImpl;
import org.example.bo.custom.impl.ProgramsBOImpl;
import org.example.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory bOFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return (bOFactory==null)?bOFactory=new BOFactory():bOFactory;
    }

    public enum BOTypes{
        USER,STUDENT,PROGRAMS
    }

    public SuperBO getBO(BOTypes boTypes){
        switch(boTypes){
            case USER:
                return new UserBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case PROGRAMS:
                return new ProgramsBOImpl();
            default:
                return null;
        }
    }
}
