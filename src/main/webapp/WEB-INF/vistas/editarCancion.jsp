<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.app.logica.Cancion" %>
<%--
  Vista para editar una canción.

  La arma el Servlet SvCancionEditar (en su doGet), que antes de hacer forward a esta página tiene que dejar:
    request.setAttribute("cancion", <Cancion>);

  El formulario manda los datos por POST al mismo SvCancionEditar.
--%>
<%
    Cancion c = (Cancion) request.getAttribute("cancion");
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Editar canción</title>
  <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<header>
  <h1>Proyecto Música</h1>
  <nav><a href="SvArtistaListar">Artistas</a> <a href="SvCancionListar">Canciones</a></nav>
</header>
<main>

  <h2>Editar canción</h2>
  <% if (c == null) { %>
    <div class="error">No se recibió ninguna canción para editar.</div>
    <a class="boton secundario" href="SvCancionListar">Volver</a>
  <% } else { %>
  <form class="formulario" action="SvCancionEditar" method="post">
    <input type="hidden" name="id" value="<%= c.getId() %>">
    <label>Título <input type="text" name="titulo" value="<%= c.getTitulo() %>" required></label>
    <label>Duración (minutos, con punto: 3.52) <input type="number" name="duracion" step="0.01" min="0.01" value="<%= c.getDuracion() %>" required></label>
    <label>Año <input type="number" name="anio" min="1900" max="2100" value="<%= c.getAnio() %>" required></label>
    <div>
      <button type="submit">Guardar cambios</button>
      <a class="boton secundario" href="SvCancionListar">Cancelar</a>
    </div>
  </form>
  <% } %>

</main>
<footer>Prof. Sebastián Muñoz — Instituto Técnico Ntra. Sra. de Fátima (A-893)</footer>
</body>
</html>
