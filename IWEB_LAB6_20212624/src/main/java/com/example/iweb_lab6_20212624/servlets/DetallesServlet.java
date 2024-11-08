package com.example.iweb_lab6_20212624.servlets;


import com.example.iweb_lab6_20212624.beans.Pelicula;
import com.example.iweb_lab6_20212624.daos.PeliculaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DetallesServlet", urlPatterns = {"/Detalles"})
public class DetallesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
        PeliculaDao peliculaDao = new PeliculaDao();
        Pelicula pelicula = peliculaDao.obtenerPeliculaPorId(idPelicula);

        request.setAttribute("pelicula", pelicula);
        request.getRequestDispatcher("viewPelicula.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los datos del formulario
        int peliculaId = Integer.parseInt(request.getParameter("peliculaId"));
        String titulo = request.getParameter("titulo");
        String director = request.getParameter("director");
        int anoPublicacion = Integer.parseInt(request.getParameter("anoPublicacion"));
        double rating = Double.parseDouble(request.getParameter("rating"));

        String boxOfficeStr = request.getParameter("boxOffice").replace(",", ""); // Eliminar comas
        Double boxOffice = Double.parseDouble(boxOfficeStr);


        // Crear el objeto Pelicula y actualizar los datos
        Pelicula pelicula = new Pelicula();
        pelicula.setPeliculaId(peliculaId);
        pelicula.setTitulo(titulo);
        pelicula.setDirector(director);
        pelicula.setAnoPublicacion(anoPublicacion);
        pelicula.setRating(rating);
        pelicula.setBoxOffice(boxOffice);

        // Guardar en la base de datos usando PeliculaDao
        PeliculaDao peliculaDao = new PeliculaDao();
        peliculaDao.actualizarPelicula(pelicula);

        // Redirigir al listado de películas
        response.sendRedirect("Peliculas");
    }


}
