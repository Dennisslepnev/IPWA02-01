package de.iu.likeherotozero.service;

import java.util.List;

import de.iu.likeherotozero.entity.Country;
import de.iu.likeherotozero.util.JpaUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class CountryService {

    public List<Country> findAllCountries() {

        EntityManager em = JpaUtil.getEntityManager();

        List<Country> countries = em.createQuery(
                "SELECT c FROM Country c ORDER BY c.name",
                Country.class)
                .getResultList();

        em.close();

        return countries;
    }

    public Country findById(Long id) {

        EntityManager em = JpaUtil.getEntityManager();

        Country country = em.find(Country.class, id);

        em.close();

        return country;
    }
}