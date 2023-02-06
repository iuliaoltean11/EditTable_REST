package com.example.proiect_ingineriesoftware;

import com.example.proiect_ingineriesoftware.jpa.Movies;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

@Stateless
// nu are stare. se comporta independent. pt bean
public class MoviesManager {
    @PersistenceContext(unitName = "thepersistenceunit") //spune la ce baza de date sa se conecteze: conection pools
    //faci cerere la baza de date
    protected EntityManager manager;

    public List<Movies> getMoviesList() {
        List<Movies> moviesList = new ArrayList<>();
        try {
            moviesList = manager.createQuery("SELECT b FROM Movies b").getResultList();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return moviesList;
    }

    public int addMovies(Movies movies) {
        manager.persist(movies); // adauga in baza de date
        manager.flush();
        return movies.getId();
    }

    public void deleteMovies(int id) {
        Movies movies = findMovies(id);
        if (movies != null) {
            manager.remove(movies);
        }else {
            throw new RuntimeException("Not found ID = "+id);
        }
    }

    public void updateMovies(Movies newInfo) {
        if(newInfo == null) {
            throw new RuntimeException("No data provided.");
        }
        Movies movies = findMovies(newInfo.getId());
        if(movies != null) {
            movies.setTitle(newInfo.getTitle());
            movies.setYear(newInfo.getYear());
            movies.setActor(newInfo.getActor());
            //il trimit inapoi prin manager
            manager.merge(movies);

        } else {
            throw new RuntimeException("Not found ID = " + newInfo.getId());
        }
    }

    public Movies findMovies(int  id){
        try {
            Movies movies = (Movies) manager
                    .createQuery("SELECT b FROM Movies b WHERE b.id = :param")
                    .setParameter("param", id)
                    .getSingleResult();
            return movies;
        }catch(NoResultException noResult) {
            return null;
        }
    }
}
