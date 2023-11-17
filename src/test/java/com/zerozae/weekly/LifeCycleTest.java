package com.zerozae.weekly;

import static org.assertj.core.api.Assertions.*;

import com.zerozae.weekly.domain.customer.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LifeCycleTest {

    @Autowired
    EntityManagerFactory emf;

    @Test
    void 영속_상태() {
        // Given
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        // When
        transaction.begin();
        Customer customer = new Customer("hello123@gmail.com", "백둥이", "01012345678");
        em.persist(customer);
        transaction.commit();

        // Then
        assertThat(em.contains(customer)).isEqualTo(true);
    }

    @Test
    void 준영속_상태() {
        // Given
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        // When
        transaction.begin();
        Customer customer = new Customer("hello123@gmail.com", "백둥이", "01012345678");
        em.persist(customer);
        transaction.commit();
        em.detach(customer);

        // Then
        assertThat(em.contains(customer)).isEqualTo(false);
    }

    @Test
    void 비영속_상태() {
        // Given
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        // When
        transaction.begin();
        Customer customer = new Customer("hello123@gmail.com", "백둥이", "01012345678");
        transaction.commit();

        // Then
        assertThat(em.contains(customer)).isEqualTo(false);
    }

    @Test
    void 삭제_상태() {
        // Given
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        // When
        transaction.begin();
        Customer customer = new Customer("hello123@gmail.com", "백둥이", "01012345678");
        transaction.commit();
        em.remove(customer);

        // Then
        assertThat(em.contains(customer)).isEqualTo(false);
    }
}
