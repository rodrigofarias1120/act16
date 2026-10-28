
package com.app.servlets;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/SvCancionEditar")
public class SvCancionEditar extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje, StandardCharsets.UTF_8);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Cancion cancion = control.buscarUnaCancion(id);

            if (cancion == null) {
                response.sendRedirect("SvCancionListar?error="
                        + codificar("No existe la canción."));
                return;
            }

            request.setAttribute("cancion", cancion);

            request.getRequestDispatcher(
                    "/WEB-INF/vistas/editarCancion.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("SvCancionListar?error="
                    + codificar("El ID de la canción no es válido."));
        } catch (Exception e) {
            response.sendRedirect("SvCancionListar?error="
                    + codificar("No se pudo abrir la edición."));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            float duracion = Float.parseFloat(
                    request.getParameter("duracion"));
            int anio = Integer.parseInt(request.getParameter("anio"));

            Cancion cancion = control.buscarUnaCancion(id);

            if (cancion == null) {
                response.sendRedirect("SvCancionListar?error="
                        + codificar("No existe la canción."));
                return;
            }

            cancion.setTitulo(request.getParameter("titulo"));
            cancion.setDuracion(duracion);
            cancion.setAnio(anio);

            control.editarCancion(cancion);

            response.sendRedirect("SvCancionListar");

        } catch (NumberFormatException e) {
            response.sendRedirect("SvCancionListar?error="
                    + codificar("El ID, la duración y el año deben ser válidos."));
        } catch (Exception e) {
            String mensaje = e.getMessage() == null
                    ? "No se pudo editar la canción."
                    : e.getMessage();

            response.sendRedirect("SvCancionListar?error="
                    + codificar(mensaje));
        }
    }
}
