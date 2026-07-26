package de.iu.likeherotozero.bean;

import java.io.Serializable;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import de.iu.likeherotozero.entity.Country;
import de.iu.likeherotozero.service.CountryService;
import de.iu.likeherotozero.service.EmissionService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class EmissionAdminBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CountryService countryService;

    @Inject
    private EmissionService emissionService;

    @Inject
    private LoginBean loginBean;

    private List<Country> countries = new ArrayList<>();

    private Long selectedCountryId;
    private Integer year;
    private Double co2Value;

    private String successMessage;
    private String errorMessage;

    @PostConstruct
    public void init() {
        countries = countryService.findAllCountries();
        year = Year.now().getValue();
    }

    public void saveEmission() {

        successMessage = null;
        errorMessage = null;

        if (!loginBean.isLoggedIn()) {
            errorMessage = "Für diese Funktion ist eine Anmeldung erforderlich.";
            return;
        }

        try {
            emissionService.createEmission(
                    selectedCountryId,
                    year,
                    co2Value,
                    loginBean.getLoggedInUser().getId());

            successMessage =
                    "Der Emissionsdatensatz wurde gespeichert "
                    + "und wartet auf Freigabe.";

            selectedCountryId = null;
            year = Year.now().getValue();
            co2Value = null;

        } catch (RuntimeException e) {
            errorMessage =
                    "Der Emissionsdatensatz konnte nicht gespeichert werden.";
        }
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    
  
    public Double getCo2Value() {
        return co2Value;
    }

    public void setCo2Value(Double co2Value) {
        this.co2Value = co2Value;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    
    public int getCurrentYear() {
        return Year.now().getValue();
    }
}