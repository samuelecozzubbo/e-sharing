package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.DTO.SiteDTO;
import com.e_sharing.E_sharing.Model.Site;
import com.e_sharing.E_sharing.Repositories.SiteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service per la gestione delle operazioni sui Site.
 */
@Service
public class SiteService {

    private final SiteRepository siteRepository;
    private final ModelMapper modelMapper;
    private final GenericUtils genericUtils;

    @Autowired
    public SiteService(SiteRepository siteRepository, ModelMapper modelMapper, GenericUtils genericUtils) {
        this.siteRepository = siteRepository;
        this.modelMapper = modelMapper;
        this.genericUtils = genericUtils;
    }

    /**
     * Restituisce tutti i Site come lista di SiteDTO.
     */
    public List<SiteDTO> getAllSite() {
        return genericUtils.iterableToListAndDTO(siteRepository.findAll(), SiteDTO.class);
    }

    /**
     * Restituisce un SiteDTO a partire dall'id, se esiste.
     */
    public Optional<SiteDTO> getSiteById(Long id) {
        return siteRepository.findById(id).map(site -> modelMapper.map(site, SiteDTO.class));
    }

    /**
     * Salva un nuovo Site nel database.
     * @param site SiteDTO da salvare
     * @return messaggio di esito
     */
    @Transactional
    public String saveSite(SiteDTO site) {
        Site newSite = modelMapper.map(site, Site.class);
        siteRepository.save(newSite);
        return "Site saved";
    }

    /**
     * Elimina un Site dato l'id.
     * @param id id del sito da eliminare
     * @return messaggio di esito
     */
    public String deleteSite(Long id) {
        siteRepository.deleteById(id);
        return "Site eliminato con id: " + id;
    }

    /**
     * Aggiorna le informazioni di un Site esistente.
     * @param id id del sito da aggiornare
     * @param site nuovo oggetto SiteDTO con i dati aggiornati
     * @return SiteDTO aggiornato
     */
    @Transactional
    public SiteDTO updateSite(Long id, SiteDTO site) {
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