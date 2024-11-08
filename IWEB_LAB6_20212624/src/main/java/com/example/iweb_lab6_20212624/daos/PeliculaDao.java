package com.example.iweb_lab6_20212624.daos;

import com.example.iweb_lab6_20212624.beans.Genero;
import com.example.iweb_lab6_20212624.beans.Pelicula;

import java.sql.*;
import java.util.ArrayList;

public class PeliculaDao extends DaoBase{


    //Listar pelicula
    public ArrayList<Pelicula> obtenerListaPeliculas() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();


        String query = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, p.idGenero, g.nombre " +
                "FROM Pelicula p " +
                "JOIN Genero g ON p.idGenero = g.idGenero " +
                "ORDER BY p.rating DESC, p.boxOffice DESC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);) {

            while (rs.next()) {
                Pelicula pelicula = fetchPeliculas(rs);
                listaPeliculas.add(pelicula);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPeliculas;

    }

    public ArrayList<Pelicula> buscarPeliculasPorTitulo(String titulo) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        String query = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, p.idGenero, g.nombre " +
                "FROM Pelicula p " +
                "JOIN Genero g ON p.idGenero = g.idGenero " +
                "WHERE p.titulo LIKE ?";



        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {


            // Asegurarse de que el término de búsqueda no sea nulo o vacío
            if (titulo != null && !titulo.trim().isEmpty()) {
                stmt.setString(1, "%" + titulo.trim() + "%");
            } else {
                // Si no hay término de búsqueda, devolver lista vacía o todas las películas
                return peliculas;
            }

            stmt.setString(1, "%" + titulo + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pelicula pelicula = fetchPeliculas(rs);
                peliculas.add(pelicula);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peliculas;


    }
    public Pelicula obtenerPeliculaPorId(int idPelicula) {
        String query = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, p.idGenero, g.nombre " +
                "FROM Pelicula p " +
                "JOIN Genero g ON p.idGenero = g.idGenero " +
                "WHERE p.idPelicula LIKE ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idPelicula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pelicula pelicula = fetchPeliculas(rs);

                return pelicula;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra el género
    }

    public void actualizarPelicula(Pelicula pelicula) {
        String query = "UPDATE Pelicula SET titulo = ?, director = ?, anoPublicacion = ?, rating = ?, boxOffice = ? WHERE idPelicula = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, pelicula.getTitulo());
            pstmt.setString(2, pelicula.getDirector());
            pstmt.setInt(3, pelicula.getAnoPublicacion());
            pstmt.setDouble(4, pelicula.getRating());
            pstmt.setDouble(5, pelicula.getBoxOffice());
            pstmt.setInt(6, pelicula.getPeliculaId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public void eliminarPelicula(int idPelicula) {
        String query = "DELETE FROM Pelicula WHERE idPelicula = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idPelicula);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private Pelicula fetchPeliculas(ResultSet rs) throws SQLException {
        Pelicula pelicula = new Pelicula();
        pelicula.setPeliculaId(rs.getInt("idPelicula"));
        pelicula.setTitulo(rs.getString("titulo"));
        pelicula.setDirector(rs.getString("director"));
        pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
        pelicula.setRating(rs.getDouble("rating"));
        pelicula.setBoxOffice(rs.getInt("boxOffice"));

        Genero genero = new Genero();
        genero.setGeneroID(rs.getInt("idGenero"));
        genero.setNombre(rs.getString("nombre"));

        pelicula.setGenero(genero);

        return pelicula;
    }
}
