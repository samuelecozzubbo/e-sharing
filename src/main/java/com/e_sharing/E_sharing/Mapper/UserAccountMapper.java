package com.e_sharing.E_sharing.Mapper;

import com.e_sharing.E_sharing.DTO.UserAccountDTO;
import com.e_sharing.E_sharing.Model.UserAccount;

/**
 * Classe di utilità per la conversione tra {@link UserAccountDTO} e {@link UserAccount}.
 * <p>
 * Fornisce metodi statici per trasformare oggetti di dominio in DTO e viceversa,
 * facilitando la separazione tra i livelli dell'applicazione.
 * </p>
 */
public class UserAccountMapper {

    /**
     * Converte un oggetto {@link UserAccountDTO} in un oggetto {@link UserAccount} (entity).
     *
     * @param dto oggetto UserAccountDTO da convertire
     * @return oggetto UserAccount convertito
     */
    public static UserAccount toEntity(UserAccountDTO dto) {
        UserAccount entity = new UserAccount();
        entity.setEmail(dto.getEmail());
        entity.setNome(dto.getNome());
        entity.setCognome(dto.getCognome());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    /**
     * Converte un oggetto {@link UserAccount} (entity) in un oggetto {@link UserAccountDTO}.
     *
     * @param entity oggetto UserAccount da convertire
     * @return oggetto UserAccountDTO convertito
     */
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