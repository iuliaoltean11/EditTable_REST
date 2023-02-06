package com.example.proiect_ingineriesoftware.jpa;

import jakarta.persistence.*;

@Entity
//ofera fact model pt baza de date
@Table(name = "movies")
public class Movies {
    //tip primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "actors")
    private String actor;
    @Column(name = "movie_year")
    private int year;

    // aduc din obiectul movies = variabila
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

