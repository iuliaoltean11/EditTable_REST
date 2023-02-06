package com.example.proiect_ingineriesoftware;

import com.example.proiect_ingineriesoftware.jpa.Movies;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/movies")
public class MoviesService {
    @EJB // spune ca e un java bean; face o legatura intre baza de date si obiect java de folosit
    private MoviesManager moviesManager; // face legatura cu clasa manager

    @GET // request
    @Produces(MediaType.APPLICATION_JSON) //produce un rasp de tip json (de tip cheie-val etc)
    public Response list() {
        List<Movies> moviesList = moviesManager.getMoviesList();
        return Response.ok().entity(moviesList).build();
    }


    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies(@PathParam("id") int id) {
        Movies movie = moviesManager.findMovies(id); // aduce filmul cu id resp
        return Response.ok()
                .entity(movie)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)// in plus primeste o instanta(cheie-val)
    public Response addNewMovie(Movies instanceMovie) { // ideea e sa add un rand nou
        if (instanceMovie != null && moviesManager.findMovies(instanceMovie.getId()) == null) {
            int newId = moviesManager.addMovies(instanceMovie);
            String txt = "{\"Message\": \"Movie added: id="+newId+"\"}";
            return Response.ok().entity(txt).build();
        }

        String errorMsg = "";
        if (instanceMovie == null) {
            errorMsg = "Cannot read data";
        } else {
            errorMsg = "Error: movie already added";
        }
        String txt = "{ \"Message\": \""+errorMsg+"\"}";
        return Response.status(400)
                .entity(txt)
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editMovie(Movies instanceMovies) {

        if (instanceMovies != null) {
            try {
                moviesManager.updateMovies(instanceMovies);
                String txt = "{\"Message\": \"Movie modified\"}";
                return Response.ok().entity(txt).build();
            }catch(RuntimeException ex) {
                String txt = "{\"Message\": \"Internal error: "+ex.getMessage()+"\"}";
                return Response.status(500)
                        .entity(txt)
                        .build();
            }
        } else {
            String txt = "{\"Message\": \"\"Cannot read data\"}";
            return Response.status(400)
                    .entity(txt)
                    .build();
        }

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMovie(@PathParam("id") int id) {
        if (id > 0) {
            try{
                moviesManager.deleteMovies(id);
                String txt = "{\"Message\": \"Movie deleted\"}";
                return Response.ok().entity(txt).build();
            }catch(RuntimeException ex) {
                String txt = "{\"Message\": \"Internal error: "+ex.getMessage()+"\"}";
                return Response.status(500)
                        .entity(txt)
                        .build();
            }
        }
        String txt = "{\"Message\": \"\"Wrong id " + id +"\"}";
        return Response.status(400)
                .entity(txt)
                .build();
    }
}
