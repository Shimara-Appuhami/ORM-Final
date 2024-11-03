package lk.ijse.orm.bo;

import lk.ijse.orm.bo.custom.impl.ProgramBOImpl;
import lk.ijse.orm.bo.custom.impl.StudentBOImpl;
import lk.ijse.orm.bo.custom.impl.StudentProgramBOImpl;

public class BoFactory {
    public static BoFactory boFactory;
    private BoFactory(){
    }
    public static BoFactory getBoFactory(){
        return boFactory==null? new BoFactory():boFactory;
    }
    public enum BoTypes{
        STUDENT,PROGRAM,STUDENTPROGRAMDETAILS  ;
    }
    public SuperBo getBo(BoTypes boTypes){
        switch (boTypes){
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

