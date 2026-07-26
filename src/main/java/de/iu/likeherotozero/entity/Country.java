package de.iu.likeherotozero.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String isoCode;
    private String continent;

    @OneToMany(mappedBy = "country")
    private List<Emission> emissions = new ArrayList<>();

    public Country() {
    }

    public Country(String name, String isoCode, String continent) {
        this.name = name;
        this.isoCode = isoCode;
        this.continent = continent;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public String getContinent() {
        return continent;
    }

    public List<Emission> getEmissions() {
        return emissions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setEmissions(List<Emission> emissions) {
        this.emissions = emissions;
    }
}