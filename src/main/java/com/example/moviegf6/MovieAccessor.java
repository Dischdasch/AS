package com.example.moviegf6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

import java.util.List;

public class MovieAccessor {
    @PersistenceUnit
    private static EntityManagerFactory emf;
    public Movie getMovie(int userId) {
        EntityManager em = emf.createEntityManager();
        Movie movie = em.find(Movie.class, userId);
        return movie;
    }

    public void setMovie(String namePassed, String descriptionPassed) {
        Movie movie = new Movie();
        movie.setName(namePassed);
        movie.setDescription(descriptionPassed);
        EntityManager em = emf.createEntityManager();
        em.persist(movie);
    }


    public List<Movie> findAll() {

        String queryString = "select e from Movie e";
        EntityManager em = emf.createEntityManager();
        return em.createQuery(queryString, Movie.class).getResultList();
    }
}
