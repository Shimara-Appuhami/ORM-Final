package lk.ijse.orm.dao;

import lk.ijse.orm.dao.custom.impl.ProgramDAOImpl;
import lk.ijse.orm.dao.custom.impl.StudentDAOImpl;
import lk.ijse.orm.dao.custom.impl.StudentProgramDAOImpl;
import lk.ijse.orm.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return daoFactory==null? new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        USER,STUDENT,PROGRAM,STUDENT_PROGRAM_DETAILS;

    }
    public SuperDAO getDAO(DAOTypes daoTypes){

        switch (daoTypes){
            case USER:
                return new UserDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();

            case PROGRAM:
                return new ProgramDAOImpl();

            case STUDENT_PROGRAM_DETAILS:
                return new StudentProgramDAOImpl();

            default:
                return null;
        }


    }
}
