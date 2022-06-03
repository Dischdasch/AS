package com.example.moviegf6;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "savedmovies")
public class Savedmovie {
    @EmbeddedId
    private SavedmovieId id;

    public SavedmovieId getId() {
        return id;
    }

    public void setId(SavedmovieId id) {
        this.id = id;
    }

//TODO [JPA Buddy] generate columns from DB
}