package de.iu.likeherotozero.service;

import de.iu.likeherotozero.entity.User;
import de.iu.likeherotozero.util.JpaUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class UserService {

    public User authenticate(String username, String password) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT u FROM User u "
                    + "WHERE u.username = :username "
                    + "AND u.password = :password",
                    User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;

        } finally {
            em.close();
        }
    }
}