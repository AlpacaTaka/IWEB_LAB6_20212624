package com.example.iweb_lab6_20212624.daos;

import com.example.iweb_lab6_20212624.beans.Genero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneroDao extends DaoBase{


    //Metodo para obtener un género por su ID (en este caso no lo usar'e ya que instancia genero desde el pelicula dao

    //de esa manera me ahorro abrir la conexion

    public Genero obtenerGeneroPorId(int idGenero) {
        String query = "SELECT idGenero, nombre FROM Genero WHERE idGenero = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idGenero);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Genero genero = new Genero();
                genero.setGeneroID(rs.getInt("idGenero"));
                genero.setNombre(rs.getString("nombre"));
                return genero;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra el género
    }

}
