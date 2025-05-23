package com.e_sharing.E_sharing.Service;


import com.e_sharing.E_sharing.DTO.SiteDTO;
import com.e_sharing.E_sharing.Model.Site;
import com.e_sharing.E_sharing.Repositories.SiteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SiteService {

    private final SiteRepository siteRepository;
    private final ModelMapper modelMapper;
    private final GenericUtils genericUtils;

    @Autowired
    public SiteService(SiteRepository siteRepository , ModelMapper modelMapper , GenericUtils genericUtils){
        this.siteRepository = siteRepository;
        this.modelMapper = modelMapper;
        this.genericUtils = genericUtils;
    }


    //get all con generic utils
    public List<SiteDTO> getAllSite(){
        return genericUtils.iterableToListAndDTO(siteRepository.findAll(), SiteDTO.class);
    }


    //get by id
    public Optional<SiteDTO> getSiteById (Long id){
        return siteRepository.findById(id).map(site -> modelMapper.map(site, SiteDTO.class));
    }

    //salva un nuovo site controllando che non sia gia presente
    @Transactional
    public String saveSite(SiteDTO site){
        Site newSite = modelMapper.map(site, Site.class);
        siteRepository.save(newSite);
        return "Site saved";
    }

    //delete by Id
    public void deleteSite(Long id){siteRepository.deleteById(id);}

    //update info
    @Transactional
    public SiteDTO updateSite(Long id ,SiteDTO site){
        return siteRepository.findById(id).map(siteToUpdate -> {
            siteToUpdate.setAddress(site.getAddress());
            siteToUpdate.setCapacity(site.getCapacity());
            siteToUpdate.setCity(site.getCity());
            siteToUpdate.setName(site.getName());

            Site updatedSite = siteRepository.save(siteToUpdate);
            return modelMapper.map(updatedSite, SiteDTO.class);
        }).orElseThrow(() -> new RuntimeException("Site non trovato con id: " + id));
    }

}
