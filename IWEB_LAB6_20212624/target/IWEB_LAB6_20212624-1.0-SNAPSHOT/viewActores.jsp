<%@ page import="com.example.iweb_lab6_20212624.beans.Pelicula" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.iweb_lab6_20212624.beans.Actor" %><%--
  Created by IntelliJ IDEA.
  User: Daniel
  Date: 7/11/2024
  Time: 04:07 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Pelicula pelicula = (Pelicula) request.getAttribute("pelicula");
    ArrayList<Actor> listaActores = (ArrayList) request.getAttribute("listaActores");

%>
<html>
<head>
    <title>Pelicula N° <%=pelicula.getTitulo()%></title>
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
</head>
<body>
<div class="container mt-5">
    <h2><%=pelicula.getTitulo()%></h2>

    <!-- Tabla de detalles de la película -->
    <form action="Actores" method="POST">

        <table class="table table-striped">
            <thead>
            <tr>
                <th>idActor</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Año de Nacimiento</th>
                <th>Ganador Premio Oscar</th>

            </tr>
            </thead>
            <tbody>
            <%
                int i = 1;
                for (Actor a : listaActores) {
            %>
            <tr>
                <td><%= a.getActorId()%>
                </td>
                <td><%= a.getActorNombre()%>
                </td>
                <td><%= a.getActorApellido()%>
                </td>
                <td><%= a.getAnoNacimiento()%>
                </td>
                <td><%=a.isPremioOscar()%>
                </td>


            </tr>
            <%
                    i++;
                }
            %>
            </tbody>
        </table>
        <button type="submit" class="btn btn-primary">Crear Actor</button>
    </form>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
