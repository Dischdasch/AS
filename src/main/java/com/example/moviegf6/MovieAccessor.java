package com.example.moviegf6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

import java.util.List;

public class MovieAccessor {
    @PersistenceUnit
    private static EntityManagerFactory emf;
    public MovieEntity getMovie(int userId) {
        EntityManager em = emf.createEntityManager();
        MovieEntity movie = em.find(MovieEntity.class, userId);
        return movie;
    }

    public void setMovie(String namePassed, String descriptionPassed) {
        MovieEntity movie = new MovieEntity();
        movie.setName(namePassed);
        movie.setDescription(descriptionPassed);
        EntityManager em = emf.createEntityManager();
        em.persist(movie);
    }


    public List<MovieEntity> findAll() {

        String queryString = "select e from MovieEntity e";
        EntityManager em = emf.createEntityManager();
        return em.createQuery(queryString, MovieEntity.class).getResultList();
    }
}
