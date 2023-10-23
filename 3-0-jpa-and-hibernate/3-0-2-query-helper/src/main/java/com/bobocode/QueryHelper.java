package com.bobocode;

import com.bobocode.exception.QueryHelperException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.Collection;
import java.util.function.Function;

/**
 * {@link QueryHelper} provides an util method that allows to perform read operations in the scope of transaction
 */
public class QueryHelper {
    private EntityManagerFactory entityManagerFactory;

    public QueryHelper(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    public <T> T readWithinTx(Function<EntityManager, T> entityManagerConsumer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.unwrap(Session.class).setDefaultReadOnly(true);
        entityManager.getTransaction().begin();
        try {
            T result = entityManagerConsumer.apply(entityManager);
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new QueryHelperException("Transaction is rolled back.", e);
        } finally {
            entityManager.close();
        }
    }
}