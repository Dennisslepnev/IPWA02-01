package de.iu.likeherotozero.bean;

import java.io.Serializable;

import de.iu.likeherotozero.entity.Country;
import de.iu.likeherotozero.entity.Emission;
import de.iu.likeherotozero.service.CountryService;
import de.iu.likeherotozero.service.EmissionService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class EmissionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CountryService countryService;

    @Inject
    private EmissionService emissionService;

    private Long selectedCountryId;

    private Emission latestEmission;

    private boolean searchPerformed;

    public void searchLatestEmission() {

        searchPerformed = true;
        latestEmission = null;

        if (selectedCountryId == null) {
            return;
        }

        Country selectedCountry =
                countryService.findById(selectedCountryId);

        if (selectedCountry != null) {
            latestEmission =
                    emissionService.findLatestApprovedEmissionByCountry(
                            selectedCountry);
        }
    }

    public Long getSelectedCountryId() {
        return selectedCountryId;
    }

    public void setSelectedCountryId(Long selectedCountryId) {
        this.selectedCountryId = selectedCountryId;
    }

    public Emission getLatestEmission() {
        return latestEmission;
    }

    public boolean isSearchPerformed() {
        return searchPerformed;
    }
}