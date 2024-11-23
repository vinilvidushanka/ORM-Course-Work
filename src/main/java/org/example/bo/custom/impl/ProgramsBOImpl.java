package org.example.bo.custom.impl;

import org.example.bo.custom.ProgramsBO;
import org.example.dao.Custom.ProgramsDAO;
import org.example.dao.Custom.UserDAO;
import org.example.dao.Custom.impl.ProgramsDAOImpl;
import org.example.dao.DAOFactory;
import org.example.dto.ProgramsDto;
import org.example.dto.UserDto;
import org.example.entity.Programs;
import org.example.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramsBOImpl implements ProgramsBO {

    ProgramsDAO programsDAO = (ProgramsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PROGRAMS);
    @Override
    public boolean save(ProgramsDto programDto) throws SQLException, ClassNotFoundException {
        return programsDAO.save(new Programs(programDto.getId(), programDto.getName(),programDto.getDuration(),programDto.getFee()));
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
        List<ProgramsDto> allPrograms= new ArrayList<>();
        List<Programs> all = programsDAO.getAll();
        for (Programs programs : all) {
            allPrograms.add(new ProgramsDto(programs.getId(), programs.getName(), programs.getDuration(), programs.getFee()));
        }
        return allPrograms;
    }
}
