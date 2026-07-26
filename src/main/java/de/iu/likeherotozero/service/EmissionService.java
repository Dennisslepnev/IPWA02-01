package de.iu.likeherotozero.service;

import java.util.List;

import de.iu.likeherotozero.entity.Country;
import de.iu.likeherotozero.entity.Emission;
import de.iu.likeherotozero.entity.User;
import de.iu.likeherotozero.enums.EmissionStatus;
import de.iu.likeherotozero.util.JpaUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class EmissionService {

    public Emission findLatestApprovedEmissionByCountry(Country country) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT e FROM Emission e "
                    + "WHERE e.country = :country "
                    + "AND e.status = :status "
                    + "ORDER BY e.year DESC",
                    Emission.class)
                    .setParameter("country", country)
                    .setParameter("status", EmissionStatus.APPROVED)
                    .setMaxResults(1)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;

        } finally {
            em.close();
        }
    }

    public void createEmission(
            Long countryId,
            Integer year,
            Double co2Value,
            Long userId) {

        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Country country = em.find(Country.class, countryId);
            User user = em.find(User.class, userId);

            if (country == null) {
                throw new IllegalArgumentException(
                        "Das ausgewählte Land wurde nicht gefunden.");
            }

            if (user == null) {
                throw new IllegalArgumentException(
                        "Der angemeldete Benutzer wurde nicht gefunden.");
            }

            Emission emission = new Emission(
                    country,
                    year,
                    co2Value,
                    EmissionStatus.PENDING,
                    user);

            em.persist(emission);
            transaction.commit();

        } catch (RuntimeException e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }

    public List<Emission> findPendingEmissions() {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT e FROM Emission e "
                    + "WHERE e.status = :status "
                    + "ORDER BY e.year DESC, e.country.name ASC",
                    Emission.class)
                    .setParameter("status", EmissionStatus.PENDING)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public void updateStatus(
            Long emissionId,
            EmissionStatus newStatus) {

        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Emission emission = em.find(Emission.class, emissionId);

            if (emission == null) {
                throw new IllegalArgumentException(
                        "Der Emissionsdatensatz wurde nicht gefunden.");
            }

            emission.setStatus(newStatus);

            transaction.commit();

        } catch (RuntimeException e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }
}