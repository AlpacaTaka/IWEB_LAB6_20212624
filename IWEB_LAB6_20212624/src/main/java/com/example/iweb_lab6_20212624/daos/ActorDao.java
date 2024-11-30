package com.example.iweb_lab6_20212624.daos;

import com.example.iweb_lab6_20212624.beans.Actor;
import com.example.iweb_lab6_20212624.beans.Genero;
import com.example.iweb_lab6_20212624.beans.Pelicula;

import java.sql.*;
import java.util.ArrayList;

public class ActorDao extends DaoBase{

    public ArrayList<Actor> obtenerActoresPorPelicula(int peliculaId) {
        ArrayList<Actor> listaActores = new ArrayList<>();
        String query = "SELECT a.idActor, a.nombre, a.apellido, a.anoNacimiento, a.premioOscar " +
                "FROM Actor a " +
                "JOIN Pelicula_Actor pa ON a.idActor = pa.idActor " +
                "WHERE pa.idPelicula = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, peliculaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Actor actor = new Actor();
                actor.setActorId(rs.getInt("idActor"));
                actor.setActorNombre(rs.getString("nombre"));
                actor.setActorApellido(rs.getString("apellido"));
                actor.setAnoNacimiento(rs.getInt("anoNacimiento"));
                actor.setPremioOscar(rs.getBoolean("premioOscar"));
                listaActores.add(actor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaActores;
    }
}
