package de.iu.likeherotozero.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.iu.likeherotozero.entity.Country;
import de.iu.likeherotozero.service.CountryService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CountryBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CountryService countryService;

    private List<Country> countries = new ArrayList<>();

    private Long selectedCountryId;

    @PostConstruct
    public void init() {
        countries = countryService.findAllCountries();
    }

    public List<Country> getCountries() {
        return countries;
    }

    public Long getSelectedCountryId() {
        return selectedCountryId;
    }

    public void setSelectedCountryId(Long selectedCountryId) {
        this.selectedCountryId = selectedCountryId;
    }
}