package com.example.iweb_lab6_20212624.servlets;


import com.example.iweb_lab6_20212624.beans.Pelicula;
import com.example.iweb_lab6_20212624.daos.PeliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PeliculaServlet", urlPatterns = {"/Peliculas"})
public class PeliculaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Verificar si hay un parámetro "id" en la solicitud
        String idPelicula = request.getParameter("idPelicula");
        String action = request.getParameter("action");

        if (idPelicula != null) {
            // Si se proporciona "id", se ejecuta la eliminación
            eliminarPelicula(Integer.parseInt(idPelicula));
        }


        // Luego, se lista las películas actualizadas
        listarPeliculas(request, response);






    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificar si se trata de una búsqueda
        String searchQuery = request.getParameter("search");

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            // Si hay un término de búsqueda, se realiza la búsqueda
            buscarPeliculas(request, response, searchQuery);
        } else {
            // Si no se realiza ninguna búsqueda, simplemente mostrar todas las películas
            listarPeliculas(request, response);
        }
    }
    private void eliminarPelicula(int idPelicula) {



        PeliculaDao peliculaDao = new PeliculaDao();
        peliculaDao.eliminarPelicula(idPelicula);
    }

    private void listarPeliculas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PeliculaDao peliculaDao = new PeliculaDao();
        ArrayList<Pelicula> listaPeliculas = peliculaDao.obtenerListaPeliculas();

        request.setAttribute("listaPeliculas", listaPeliculas);

        request.getRequestDispatcher("listaPeliculas.jsp").forward(request, response);
    }
    private void buscarPeliculas(HttpServletRequest request, HttpServletResponse response, String searchQuery) throws ServletException, IOException {
        PeliculaDao peliculaDao = new PeliculaDao();
        ArrayList<Pelicula> listaPeliculas = peliculaDao.buscarPeliculasPorTitulo(searchQuery);

        request.setAttribute("listaPeliculas", listaPeliculas);
        request.getRequestDispatcher("listaPeliculas.jsp").forward(request, response);
    }



}
