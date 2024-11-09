package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.AdminDto;

public interface AdminBO extends SuperBO {
    boolean saveAdmin(AdminDto admin);

    boolean isAdminExist(AdminDto dto);
}
