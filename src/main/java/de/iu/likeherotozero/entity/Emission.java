package de.iu.likeherotozero.entity;

import java.io.Serializable;

import de.iu.likeherotozero.enums.EmissionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Emission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Country country;

    private Integer year;

    private Double co2Value;

    @Enumerated(EnumType.STRING)
    private EmissionStatus status;

    @ManyToOne
    private User createdBy;

    public Emission() {
    }

    public Emission(
            Country country,
            Integer year,
            Double co2Value,
            EmissionStatus status,
            User createdBy) {

        this.country = country;
        this.year = year;
        this.co2Value = co2Value;
        this.status = status;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }

    public Integer getYear() {
        return year;
    }

    public Double getCo2Value() {
        return co2Value;
    }

    public EmissionStatus getStatus() {
        return status;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setCo2Value(Double co2Value) {
        this.co2Value = co2Value;
    }

    public void setStatus(EmissionStatus status) {
        this.status = status;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}