package com.example.proiect_frontend;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.example.proiect_frontend.dto.MovieDto;
import com.example.proiect_frontend.dto.SimpleResponseDto;

import java.util.List;

public class MoviesRestClient {
    private static final String BASE_URI = "http://localhost:8080/proiect_ingineriesoftware-1.0-SNAPSHOT/api/movies";
    private final WebTarget webTarget; // stie sa comunice cu service bazandu-se pe link
    private final Client client;
    public MoviesRestClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI);
    }
    public void close() {
        client.close();
    }
    public List<MovieDto> getMovies() {
        Response resp = webTarget.request(MediaType.APPLICATION_JSON).get(Response.class);
        return resp.readEntity(new GenericType<List<MovieDto>>() {});
    }

    public SimpleResponseDto create(MovieDto entry) {
        Response resp =
                webTarget.request(MediaType.APPLICATION_JSON).post(jakarta.ws.rs.client.Entity.entity(entry,
                        MediaType.APPLICATION_JSON), Response.class);
        SimpleResponseDto ret = resp.readEntity(SimpleResponseDto.class);
        return ret;
    }
    public SimpleResponseDto update(MovieDto entry) {
        Response resp =
                webTarget.request(MediaType.APPLICATION_JSON).put(jakarta.ws.rs.client.Entity.entity(entry,
                        MediaType.APPLICATION_JSON), Response.class);
        SimpleResponseDto ret = resp.readEntity(SimpleResponseDto.class);
        return ret;
    }
    public void delete(int id) throws ClientErrorException {
        String subpath = String.valueOf(id);
        Response resp = webTarget.path(subpath).request(MediaType.APPLICATION_JSON).delete();
        if(resp.getStatus() >= 300) {
            throw new ClientErrorException("Error deleting movie"+ id +": "+ resp.getStatus());
        }
    }

    // ia filmele dupa id se fol in jsp in delete si update
    public MovieDto getMovieById(Integer id) {
        String subpath = "get/"+String.valueOf(id);
        Response resp =
                webTarget.path(subpath).request(MediaType.APPLICATION_JSON).get(Response.class);
        return resp.readEntity(new GenericType<MovieDto>() {});
    }
}
