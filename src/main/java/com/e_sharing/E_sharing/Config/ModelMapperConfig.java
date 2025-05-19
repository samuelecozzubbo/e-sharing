package com.e_sharing.E_sharing.Config;

import com.e_sharing.E_sharing.DTO.LeadDTO;
import com.e_sharing.E_sharing.Model.Lead;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // From Lead → LeadDTO
        modelMapper.typeMap(Lead.class, LeadDTO.class).addMappings(mapper ->
                mapper.map(src -> src.getUserAccount().getEmail(), LeadDTO::setUserEmail)
        );

        // From LeadDTO → Lead
        modelMapper.typeMap(LeadDTO.class, Lead.class).addMappings(mapper ->
                mapper.skip(Lead::setUserAccount) //Non possiamo settare direttamente
        );



        return modelMapper;
    }
}
