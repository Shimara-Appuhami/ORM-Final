package lk.ijse.orm.bo.custom;

import lk.ijse.orm.bo.SuperBo;
import lk.ijse.orm.dto.ProgramDTO;

import java.util.ArrayList;

public interface ProgramBO extends SuperBo {
    public boolean addProgram(ProgramDTO dto) throws Exception;
    public ArrayList<ProgramDTO> getAllPrograms() throws Exception;
    public boolean deleteProgram(String id) throws Exception;
    public boolean updateProgram(ProgramDTO dto) throws Exception;

}
