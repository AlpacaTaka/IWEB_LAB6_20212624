package com.example.iweb_lab6_20212624.servlets;

import com.example.iweb_lab6_20212624.beans.Actor;
import com.example.iweb_lab6_20212624.beans.Pelicula;
import com.example.iweb_lab6_20212624.daos.ActorDao;
import com.example.iweb_lab6_20212624.daos.PeliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ActorServlet", urlPatterns = {"/Actores"})
public class ActorServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        int idPelicula = Integer.parseInt(request.getParameter("id"));
        ActorDao actorDao = new ActorDao();
        ArrayList<Actor> listaActores = actorDao.obtenerActoresPorPelicula(idPelicula);
        request.setAttribute("listaActores", listaActores);

        PeliculaDao peliculaDao = new PeliculaDao();
        ArrayList<Pelicula> listaPeliculas = peliculaDao.obtenerListaPeliculas();
        Pelicula pelicula = (Pelicula) listaPeliculas.get(idPelicula-1);
        request.setAttribute("pelicula", pelicula);

        RequestDispatcher view = request.getRequestDispatcher("viewActores.jsp");
        view.forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
