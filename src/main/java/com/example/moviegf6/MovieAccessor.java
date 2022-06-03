package com.example.moviegf6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

import java.util.List;

public class MovieAccessor {
    @PersistenceContext
    private static EntityManager em;
    public MovieEntity getMovie(int userId) {
        MovieEntity movie = em.find(MovieEntity.class, userId);
        return movie;
    }

    public void setMovie(String namePassed, String descriptionPassed) {
        MovieEntity movie = new MovieEntity();
        movie.setName(namePassed);
        movie.setDescription(descriptionPassed);
        em.persist(movie);
    }


    public List<MovieEntity> findAll() {
        String queryString = "select e from MovieEntity e order by name";
        return em.createQuery(queryString, MovieEntity.class).getResultList();
    }

    public void update(MovieEntity movie) {
        em.merge(movie);
    }

    public void delete(MovieEntity movie) {
        em.remove(em.contains(movie) ? movie : em.merge(movie));
    }
}