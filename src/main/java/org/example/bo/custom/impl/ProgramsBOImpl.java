package org.example.bo.custom.impl;

import org.example.bo.custom.ProgramsBO;
import org.example.dao.Custom.ProgramsDAO;
import org.example.dao.Custom.impl.ProgramsDAOImpl;
import org.example.dto.ProgramsDto;
import org.example.entity.Programs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramsBOImpl implements ProgramsBO {

    ProgramsDAO programsDAO=new ProgramsDAOImpl();
    @Override
    public boolean save(ProgramsDto programDto) {
        return false;
    }

    @Override
    public boolean update(ProgramsDto programDto) {
        return false;
    }

    @Override
    public boolean delete(ProgramsDto programDto) {
        return false;
    }

    @Override
    public Programs search(String programCode) throws SQLException {
        return null;
    }

    @Override
    public List<ProgramsDto> getAllPrograms() throws SQLException, ClassNotFoundException {
        List<Programs> all = programsDAO.getAll();
        List<ProgramsDto> allPrograms = new ArrayList<>();
        for (Programs p : all) {
            ProgramsDto programsDto = new ProgramsDto(p.getId(), p.getName(), p.getDuration(), p.getFee());
            allPrograms.add(programsDto);
        }
        return allPrograms;
    }
}
