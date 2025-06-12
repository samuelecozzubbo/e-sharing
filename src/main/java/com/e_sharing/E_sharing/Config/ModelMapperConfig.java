package com.e_sharing.E_sharing.Config;

import com.e_sharing.E_sharing.DTO.LeadDTO;
import com.e_sharing.E_sharing.Model.Lead;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe di configurazione per il bean {@link ModelMapper} utilizzato nell'applicazione.
 * <p>
 * Questa configurazione definisce le mappature personalizzate tra l'entità {@link Lead}
 * e il relativo Data Transfer Object ({@link LeadDTO}), gestendo le logiche di mapping
 * per i campi complessi e saltando quelli che non possono essere mappati direttamente.
 * </p>
 *
 * <ul>
 *   <li>
 *     <b>Lead → LeadDTO:</b> Mappa l'email dell'utente dall'oggetto UserAccount annidato in {@link Lead}
 *     al campo {@code userEmail} di {@link LeadDTO}.
 *   </li>
 *   <li>
 *     <b>LeadDTO → Lead:</b> Salta il mapping diretto della proprietà {@code userAccount} di {@link Lead},
 *     poiché non può essere impostata direttamente dal DTO.
 *   </li>
 * </ul>
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Crea e configura il bean {@link ModelMapper} con le mappature personalizzate
     * per Lead e LeadDTO.
     *
     * @return un'istanza configurata di {@link ModelMapper}
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Da Lead a LeadDTO
        modelMapper.typeMap(Lead.class, LeadDTO.class).addMappings(mapper ->
                mapper.map(src -> src.getUserAccount().getEmail(), LeadDTO::setUserEmail)
        );

        // Da LeadDTO a Lead
        modelMapper.typeMap(LeadDTO.class, Lead.class).addMappings(mapper ->
                mapper.skip(Lead::setUserAccount) // Non possiamo settare direttamente
        );

        return modelMapper;
    }
}