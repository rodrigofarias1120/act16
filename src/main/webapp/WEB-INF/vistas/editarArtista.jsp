<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.app.logica.Artista" %>
<%--
  Vista para editar un artista.

  La arma el Servlet SvArtistaEditar (en su doGet), que antes de hacer forward a esta página tiene que dejar:
    request.setAttribute("artista", <Artista>);

  El formulario manda los datos por POST al mismo SvArtistaEditar.
--%>
<%
    Artista a = (Artista) request.getAttribute("artista");
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Editar artista</title>
  <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<header>
  <h1>Proyecto Música</h1>
  <nav><a href="SvArtistaListar">Artistas</a> <a href="SvCancionListar">Canciones</a></nav>
</header>
<main>

  <h2>Editar artista</h2>
  <% if (a == null) { %>
    <div class="error">No se recibió ningún artista para editar.</div>
    <a class="boton secundario" href="SvArtistaListar">Volver</a>
  <% } else { %>
  <form class="formulario" action="SvArtistaEditar" method="post">
    <input type="hidden" name="id" value="<%= a.getId() %>">
    <label>Nombre <input type="text" name="nombre" value="<%= a.getNombre() %>" required></label>
    <label>Género musical <input type="text" name="generoMusical" value="<%= a.getGeneroMusical() %>" required></label>
    <label>Nacionalidad <input type="text" name="nacionalidad" value="<%= a.getNacionalidad() %>" required></label>
    <label>Edad <input type="number" name="edad" min="1" max="120" value="<%= a.getEdad() %>" required></label>
    <div>
      <button type="submit">Guardar cambios</button>
      <a class="boton secundario" href="SvArtistaListar">Cancelar</a>
    </div>
  </form>
  <% } %>

</main>
<footer>Prof. Sebastián Muñoz — Instituto Técnico Ntra. Sra. de Fátima (A-893)</footer>
</body>
</html>
