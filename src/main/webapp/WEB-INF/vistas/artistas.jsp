<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.app.logica.Artista" %>
<%--
  Vista de artistas: lista + formulario de alta.

  La arma el Servlet SvArtistaListar, que antes de hacer forward a esta página tiene que dejar:
    request.setAttribute("artistas", <List<Artista>>);

  Si en la URL viene ?error=mensaje, el mensaje se muestra arriba.
--%>
<%
    List<Artista> artistas = (List<Artista>) request.getAttribute("artistas");
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Artistas</title>
  <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<header>
  <h1>Proyecto Música</h1>
  <nav>
    <a href="${pageContext.request.contextPath}/SvArtistaListar">Artistas</a>
    <a href="${pageContext.request.contextPath}/SvCancionListar">Canciones</a>
</nav></header>
<main>

  <% if (error != null) { %>
    <div class="error"><%= error %></div>
  <% } %>

  <h2>Artistas</h2>
  <table>
    <tr>
      <th>ID</th><th>Nombre</th><th>Género musical</th><th>Nacionalidad</th><th>Edad</th><th>Acciones</th>
    </tr>
    <% if (artistas == null || artistas.isEmpty()) { %>
      <tr><td colspan="6" class="vacio">Todavía no hay artistas cargados.</td></tr>
    <% } else {
         for (Artista a : artistas) { %>
      <tr>
        <td><%= a.getId() %></td>
        <td><%= a.getNombre() %></td>
        <td><%= a.getGeneroMusical() %></td>
        <td><%= a.getNacionalidad() %></td>
        <td><%= a.getEdad() %></td>
        <td class="acciones">
          <a class="boton" href="SvArtistaEditar?id=<%= a.getId() %>">Editar</a>
          <form action="SvArtistaEliminar" method="post" onsubmit="return confirm('¿Eliminar este artista?');">
            <input type="hidden" name="id" value="<%= a.getId() %>">
            <button type="submit" class="peligro">Eliminar</button>
          </form>
        </td>
      </tr>
    <%   }
       } %>
  </table>

  <h2>Nuevo artista</h2>
  <form class="formulario" action="${pageContext.request.contextPath}/SvArtistaCrear" method="post">
    <label>Nombre <input type="text" name="nombre" required></label>
    <label>Género musical <input type="text" name="generoMusical" required></label>
    <label>Nacionalidad <input type="text" name="nacionalidad" required></label>
    <label>Edad <input type="number" name="edad" min="1" max="120" required></label>
    <button type="submit">Guardar</button>
  </form>

</main>
<footer>Prof. Sebastián Muñoz — Instituto Técnico Ntra. Sra. de Fátima (A-893)</footer>
</body>
</html>
