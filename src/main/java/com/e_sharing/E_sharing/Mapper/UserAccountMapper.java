package com.e_sharing.E_sharing.Mapper;

import com.e_sharing.E_sharing.DTO.UserAccountDTO;
import com.e_sharing.E_sharing.Model.UserAccount;

public class UserAccountMapper {

    public static UserAccount toEntity(UserAccountDTO dto) {
        UserAccount entity = new UserAccount();
        entity.setEmail(dto.getEmail());
        entity.setNome(dto.getNome());
        entity.setCognome(dto.getCognome());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public static UserAccountDTO toDto(UserAccount entity) {
        UserAccountDTO dto = new UserAccountDTO();
        dto.setEmail(entity.getEmail());
        dto.setNome(entity.getNome());
        dto.setCognome(entity.getCognome());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
