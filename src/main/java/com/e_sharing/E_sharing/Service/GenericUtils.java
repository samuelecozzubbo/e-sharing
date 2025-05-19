package com.e_sharing.E_sharing.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component; // O @Service, a seconda del ruolo

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component // Dico a Spring di gestire questa classe
public class GenericUtils {

    private final ModelMapper modelMapper; // Ora è un campo d'istanza

    @Autowired // Inietta ModelMapper tramite il costruttore
    public GenericUtils(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Metodo statico originale per conversione da Iterable a List (senza StreamSupport)
    /*public static <T> List<T> iterableToList(Iterable<T> iter) {
        List<T> list = new ArrayList<>();
        for (T t : iter) {
            list.add(t);
        }
        return list;
    }*/

    // Metodo d'istanza per conversione e mappatura DTO (senza StreamSupport iniziale)
    public <T,R> List<R> iterableToListAndDTO(Iterable<T> iter, Class<R> classDTO) {
        // Converti Iterable in List usando il loop
        List<T> listaTemporanea = new ArrayList<>();
        for (T t : iter) {
            listaTemporanea.add(t);
        }

        // Ora usa lo stream sulla lista per la mappatura
        return listaTemporanea.stream()
                .map(entity -> modelMapper.map(entity, classDTO))
                .collect(Collectors.toList());
    }
}