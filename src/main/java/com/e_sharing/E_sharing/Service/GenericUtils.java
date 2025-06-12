package com.e_sharing.E_sharing.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility generica per la conversione di Iterable di entità in lista di DTO tramite ModelMapper.
 * <p>
 * Questa classe è gestita da Spring come componente e consente di mappare facilmente oggetti Entity su oggetti DTO.
 * </p>
 */
@Component // Dico a Spring di gestire questa classe
public class GenericUtils {

    private final ModelMapper modelMapper;

    /**
     * Costruttore con iniezione di ModelMapper.
     * @param modelMapper Mapper di oggetti per la trasformazione Entity -> DTO
     */
    @Autowired
    public GenericUtils(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Converte un {@code Iterable<T>} in una {@code List<R>} di DTO, usando ModelMapper.
     *
     * @param iter Iterable di entità sorgente
     * @param classDTO Classe del DTO di destinazione
     * @param <T> Tipo dell'entità sorgente
     * @param <R> Tipo dell'oggetto DTO di destinazione
     * @return Lista di DTO mappati dalla sorgente
     */
    public <T, R> List<R> iterableToListAndDTO(Iterable<T> iter, Class<R> classDTO) {
        List<T> listaTemporanea = new ArrayList<>();
        for (T t : iter) {
            listaTemporanea.add(t);
        }
        return listaTemporanea.stream()
                .map(entity -> modelMapper.map(entity, classDTO))
                .collect(Collectors.toList());
    }
}