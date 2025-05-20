package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.DTO.LeadDTO;
import com.e_sharing.E_sharing.DTO.SiteDTO;
import com.e_sharing.E_sharing.Model.Site;
import com.e_sharing.E_sharing.Repositories.SiteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SiteService {

    private final SiteRepository siteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SiteService(SiteRepository siteRepository , ModelMapper modelMapper){
        this.siteRepository = siteRepository;
        this.modelMapper = modelMapper;
    }

    //get all
    public List<SiteDTO> getAllSite(){
        List<SiteDTO> sitesDTO = new ArrayList<>();
        siteRepository.findAll().forEach(site -> sitesDTO.add(modelMapper.map(site, SiteDTO.class)));
        return sitesDTO; //siteRepository.findAll();}
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
    public String result(Long id ,SiteDTO site){
        if (siteRepository.existsById(id)){
            siteRepository.findById(id).map(existingSite -> {
                existingSite.setName(site.getName());
                existingSite.setAddress(site.getAddress());
                existingSite.setCity(site.getCity());
                return siteRepository.save(existingSite);
            });
            return "Site updated";
            }
        else {
            return "Site not found";
        }
    }

}
