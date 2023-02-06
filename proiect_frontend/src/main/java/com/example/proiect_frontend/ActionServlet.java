package com.example.proiect_frontend;

import com.example.proiect_frontend.dto.MovieDto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
//servlet = ia jsp gol si face jsp pe pagina; init variabilele in pag jsp
@WebServlet(name = "ActionServlet", value = "/actionservlet") //marcheaza clasa ca servlet si spune si path-ul
public class ActionServlet extends HttpServlet { //apelezi servlet cu actionservl
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        //primeste param action si in fct de value face op coresp
        String operation = request.getParameter("action");
        switch (operation) {
            case "createMovie":
                createMovie(request);
                break;
            case "updateMovie":
                updateMovie(request);
                break;
            case "deleteMovie":
                deleteMovie(request);
                break;
        }
        String homePath = request.getContextPath() + "/index.jsp";
        response.sendRedirect(homePath);
    }
    private void createMovie(HttpServletRequest request) {
        String title = request.getParameter("title");
        String actor = request.getParameter("actor");
        String year = request.getParameter("year");
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(title);
        movieDto.setActor(actor);
        movieDto.setYear(Integer.parseInt(year));
        MoviesRestClient movierestclient = new MoviesRestClient();
        movierestclient.create(movieDto); // apeleaza o fct din movierestclient
    }
    private void updateMovie(HttpServletRequest request) {
        String idAsString = request.getParameter("id");
        Integer movieId = Integer.parseInt(idAsString);
        String title = request.getParameter("title");
        String actor = request.getParameter("actor");
        String year = request.getParameter("year");
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movieId);
        movieDto.setTitle(title);
        movieDto.setActor(actor);
        movieDto.setYear(Integer.parseInt(year));
        MoviesRestClient movierestclient = new MoviesRestClient();
        movierestclient.update(movieDto);
    }
    private void deleteMovie(HttpServletRequest request) {
        String idAsString = request.getParameter("id");
        Integer movieId = Integer.parseInt(idAsString);
        MoviesRestClient movierestclient = new MoviesRestClient();
        try {
            movierestclient.delete(movieId);
        } catch (ClientErrorException e) {
            throw new RuntimeException(e);
        }
    }
}
