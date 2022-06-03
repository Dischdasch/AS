package com.example.moviegf6;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SavedmovieId implements Serializable {
    private static final long serialVersionUID = 536736101287679347L;
    @Column(name = "movieId", nullable = false)
    private Integer movieId;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SavedmovieId entity = (SavedmovieId) o;
        return Objects.equals(this.movieId, entity.movieId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, userId);
    }

}