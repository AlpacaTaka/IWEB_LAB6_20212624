<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.example.iweb_lab6_20212624.beans.Pelicula" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Daniel
  Date: 7/11/2024
  Time: 04:07 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    Pelicula pelicula = (Pelicula) request.getAttribute("pelicula");
    NumberFormat formatter = NumberFormat.getInstance();

%>
<html>
<head>
    <title>Pelicula N° <%=pelicula.getPeliculaId()%></title>
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
</head>
<body>
<div class="container mt-5">
    <h2>Pelicula N° <%=pelicula.getPeliculaId()%></h2>

    <!-- Tabla de detalles de la película -->
    <form action="Detalles" method="POST">
        <button type="submit" class="btn btn-primary">Guardar Pelicula</button>
        <table class="table table-bordered mt-4">
            <tbody>

            <tr>
                <th scope="row">Título</th>
                <td><input name="titulo" value="<%=pelicula.getTitulo()%>"></td>
            </tr>
            <tr>
                <th scope="row">Director</th>
                <td><input name="director" value="<%=pelicula.getDirector()%>"></td>
            </tr>
            <tr>
                <th scope="row">Año de Publicación</th>
                <td><input name="anoPublicacion" value="<%=pelicula.getAnoPublicacion()%>"></td>
            </tr>
            <tr>
                <th scope="row">Rating</th>
                <td><input name="rating" value="<%=pelicula.getRating()%>"></td>
            </tr>
            <tr>
                <th scope="row">Box Office ($)</th>
                <td><input name="boxOffice" value="<%=formatter.format(pelicula.getBoxOffice())%>"></td>
            </tr>
            <tr>
                <th scope="row">Actores</th>
                <td><a href="Actores?id=<%= pelicula.getPeliculaId() %>" > VerActores</a></td>

            </tr>
            <input type="hidden" name="peliculaId" value="<%=pelicula.getPeliculaId()%>">
            </tbody>
        </table>
    </form>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
