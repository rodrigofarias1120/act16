<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.app.logica.Cancion" %>
<%--
  Vista de canciones: lista + formulario de alta.

  La arma el Servlet SvCancionListar, que antes de hacer forward a esta página tiene que dejar:
    request.setAttribute("canciones", <List<Cancion>>);

  Si en la URL viene ?error=mensaje, el mensaje se muestra arriba.
--%>
<%
    List<Cancion> canciones = (List<Cancion>) request.getAttribute("canciones");
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Canciones</title>
  <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<header>
  <h1>Proyecto Música</h1>
  <nav><a href="SvArtistaListar">Artistas</a> <a href="SvCancionListar">Canciones</a></nav>
</header>
<main>

  <% if (error != null) { %>
    <div class="error"><%= error %></div>
  <% } %>

  <h2>Canciones</h2>
  <table>
    <tr>
      <th>ID</th><th>Título</th><th>Duración</th><th>Año</th><th>Acciones</th>
    </tr>
    <% if (canciones == null || canciones.isEmpty()) { %>
      <tr><td colspan="5" class="vacio">Todavía no hay canciones cargadas.</td></tr>
    <% } else {
         for (Cancion c : canciones) { %>
      <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getTitulo() %></td>
        <td><%= c.getDuracion() %></td>
        <td><%= c.getAnio() %></td>
        <td class="acciones">
          <a class="boton" href="SvCancionEditar?id=<%= c.getId() %>">Editar</a>
          <form action="SvCancionEliminar" method="post" onsubmit="return confirm('¿Eliminar esta canción?');">
            <input type="hidden" name="id" value="<%= c.getId() %>">
            <button type="submit" class="peligro">Eliminar</button>
          </form>
        </td>
      </tr>
    <%   }
       } %>
  </table>

  <h2>Nueva canción</h2>
  <form class="formulario" action="SvCancionCrear" method="post">
    <label>Título <input type="text" name="titulo" required></label>
    <label>Duración (minutos, con punto: 3.52) <input type="number" name="duracion" step="0.01" min="0.01" required></label>
    <label>Año <input type="number" name="anio" min="1900" max="2100" required></label>
    <button type="submit">Guardar</button>
  </form>

</main>
<footer>Prof. Sebastián Muñoz — Instituto Técnico Ntra. Sra. de Fátima (A-893)</footer>
</body>
</html>
