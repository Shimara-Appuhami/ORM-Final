package lk.ijse.orm.bo.custom.impl;

import lk.ijse.orm.bo.custom.ProgramBO;
import lk.ijse.orm.dao.DAOFactory;
import lk.ijse.orm.dao.custom.ProgramDAO;
import lk.ijse.orm.dto.ProgramDTO;
import lk.ijse.orm.entity.Program;
import lk.ijse.orm.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {

    ProgramDAO programDAO= (ProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    @Override
    public boolean addProgram(ProgramDTO dto) throws Exception {
        return programDAO.save(new Program(dto.getProgram_id(),dto.getProgram_name(),dto.getDuration(),dto.getFee()));

    }

    @Override
    public ArrayList<ProgramDTO> getAllPrograms() throws Exception {
        List<ProgramDTO> allPrograms= new ArrayList<>();
        List<Program> all = programDAO.getAll();
        for (Program p : all) {
            allPrograms.add(new ProgramDTO(p.getProgram_id(),p.getProgram_name(),p.getDuration(),p.getFee()));
        }
        return (ArrayList<ProgramDTO>) allPrograms;
    }

    @Override
    public boolean deleteProgram(String id) throws Exception {
        return programDAO.delete(id);
    }

    @Override
    public boolean updateProgram(ProgramDTO dto) throws Exception {
        return programDAO.update(new Program(dto.getProgram_id(),dto.getProgram_name(),dto.getDuration(),dto.getFee()));
    }

    @Override
    public ProgramDTO findByName(String programName) {
        try {
            Program program = programDAO.findByName(programName);
            if (program != null) {
                return new ProgramDTO(program.getProgram_id(), program.getProgram_name(), program.getDuration(), program.getFee());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
