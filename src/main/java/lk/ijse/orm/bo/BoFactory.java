package lk.ijse.orm.bo;

import lk.ijse.orm.bo.custom.impl.ProgramBOImpl;
import lk.ijse.orm.bo.custom.impl.StudentBOImpl;
import lk.ijse.orm.bo.custom.impl.StudentProgramBOImpl;
import lk.ijse.orm.bo.custom.impl.UserBOImpl;

public class BoFactory {
    public static BoFactory boFactory;
    private BoFactory(){
    }
    public static BoFactory getBoFactory(){
        return boFactory==null? new BoFactory():boFactory;
    }
    public enum BoTypes{
        USER,STUDENT,PROGRAM,STUDENTPROGRAMDETAILS  ;
    }
    public SuperBo getBo(BoTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case PROGRAM:
                return new ProgramBOImpl();
            case STUDENTPROGRAMDETAILS:
                return new StudentProgramBOImpl();

            default:
                return null;
        }
    }
}

