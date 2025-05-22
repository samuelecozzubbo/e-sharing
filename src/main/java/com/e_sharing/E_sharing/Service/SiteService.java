package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.DTO.SiteDTO;
import com.e_sharing.E_sharing.Model.Site;
import com.e_sharing.E_sharing.Repositories.SiteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    /*public List<SiteDTO> getAllSite(){
        return ; //siteRepository.findAll();}
    }*/

    //get by id
    public Optional<Site> getSiteById (Long id){return siteRepository.findById(id);}

    //salva un nuovo site controllando che non sia gia presente
    public Site saveSite(SiteDTO site){
        return siteRepository.save(modelMapper.map(site , Site.class));
    }

    //delete by Id
    public void deleteSite(Long id){siteRepository.deleteById(id);}

    //update info
    public String result(Long id ,SiteDTO site){
        if (siteRepository.existsById(id)){
            Site siteUpdate = modelMapper.map(site , Site.class);
            siteRepository.findById(id).map(existingSite -> {
                existingSite.setName(siteUpdate.getName());
                existingSite.setAddress(siteUpdate.getAddress());
                existingSite.setCity(siteUpdate.getCity());
                return siteRepository.save(existingSite);
            });
            return "Site updated";
            }
        else {
            return "Site not found";
        }
    }

}
