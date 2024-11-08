<%--
  Created by IntelliJ IDEA.
  User: Daniel
  Date: 7/11/2024
  Time: 04:06 p. m.
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.iweb_lab6_20212624.beans.Pelicula" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="java.text.NumberFormat" %>

<%

    ArrayList<Pelicula> listaPeliculas = (ArrayList) request.getAttribute("listaPeliculas");
    NumberFormat formatter = NumberFormat.getInstance();

%>
<html>
<head>
    <title>Lista Peliculas</title>
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
</head>
<script>
    function confirmDelete(form) {
        var confirmBox = confirm("¿Estás seguro de que quieres eliminar esta película?");
        if (confirmBox) {
            form.submit();
        }
    }
</script>
<body>
<div class='container'>
    <h1 class='mb-3'>Lista de Peliculas</h1>


    <form action="Peliculas" method="post">
        <input type="text" name="search"  placeholder="Buscar película..." class="form-control">
        <button type="submit" class="btn btn-primary mb-2">Buscar</button>
    </form>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Pelicula</th>
            <th>Director</th>
            <th>Año de Publicación</th>
            <th>Rating</th>
            <th>Box-Office</th>
            <th>Género</th>
            <th>Actores</th>
            <th>Accionable</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Pelicula p : listaPeliculas) {
        %>
        <tr>
            <td><a href="Detalles?idPelicula=<%= p.getPeliculaId() %>"><%= p.getTitulo()%></a>
            </td>
            <td><%= p.getDirector()%>
            </td>
            <td><%= p.getAnoPublicacion()%>
            </td>
            <td><%= p.getRating() + "/10"%>
            </td>
            <td><%="$"+formatter.format(p.getBoxOffice())%>
            </td>
            <td><%= p.getGenero().getNombre() %>
            </td>
            <td><a href="Actores?id=<%= p.getPeliculaId() %>" >Actores</a>
            </td>
            <td>
                <form action="Peliculas?" method="POST" onsubmit="event.preventDefault(); confirmDelete(this);">
                    <input type="hidden" name="action" value="borrar">
                    <input type="hidden"  name="idPelicula" value="<%= p.getPeliculaId() %>">
                    <button type="submit" class="btn btn-danger">Eliminar</button>
                </form>

            </td>
        </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
<script>
    function confirmarEliminacion(idPelicula) {
        if (confirm("¿Estás seguro de que deseas eliminar esta película?")) {
            window.location.href = "Peliculas?idPelicula=" + idPelicula;
        }
    }

</script>

</body>
</html>
