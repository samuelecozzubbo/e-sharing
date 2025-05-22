package com.e_sharing.E_sharing.DTO;

import com.e_sharing.E_sharing.Model.Site;

import java.util.ArrayList;
import java.util.List;

public class SiteDTO {

    private Long id;
    private String name;
    private String address;
    private Integer capacity;
    private String city;


    //Constructor
    public SiteDTO() {}

    public SiteDTO(String name, String address, Integer capacity , String city) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.city = city;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public static List<SiteDTO> siteIterToList(Iterable<Site> sites) {
        List<SiteDTO> siteDTO = new ArrayList<>();
        return siteDTO;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
