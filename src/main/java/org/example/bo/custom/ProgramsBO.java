package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.ProgramsDto;
import org.example.entity.Programs;

import java.sql.SQLException;
import java.util.List;

public interface ProgramsBO extends SuperBO {
    boolean save(ProgramsDto programDto);
    boolean update(ProgramsDto programDto);
    boolean delete(ProgramsDto programDto);
    Programs search(String programCode) throws SQLException;
    List<ProgramsDto> getAllPrograms() throws SQLException, ClassNotFoundException;
}
