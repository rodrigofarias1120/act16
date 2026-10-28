
package com.app.servlets;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/SvArtistaEditar")
public class SvArtistaEditar extends HttpServlet {

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

            Artista artista = control.buscarUnArtista(id);

            if (artista == null) {
                response.sendRedirect("SvArtistaListar?error="
                        + codificar("No existe el artista."));
                return;
            }

            request.setAttribute("artista", artista);

            request.getRequestDispatcher(
                    "/WEB-INF/vistas/editarArtista.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("SvArtistaListar?error="
                    + codificar("El ID del artista no es válido."));
        } catch (Exception e) {
            response.sendRedirect("SvArtistaListar?error="
                    + codificar("No se pudo abrir la edición."));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int edad = Integer.parseInt(request.getParameter("edad"));

            Artista artista = control.buscarUnArtista(id);

            if (artista == null) {
                response.sendRedirect("SvArtistaListar?error="
                        + codificar("No existe el artista."));
                return;
            }

            artista.setNombre(request.getParameter("nombre"));
            artista.setGeneroMusical(
                    request.getParameter("generoMusical"));
            artista.setNacionalidad(
                    request.getParameter("nacionalidad"));
            artista.setEdad(edad);

            control.editarArtista(artista);

            response.sendRedirect("SvArtistaListar");

        } catch (NumberFormatException e) {
            response.sendRedirect("SvArtistaListar?error="
                    + codificar("El ID y la edad deben ser números."));
        } catch (Exception e) {
            String mensaje = e.getMessage() == null
                    ? "No se pudo editar el artista."
                    : e.getMessage();

            response.sendRedirect("SvArtistaListar?error="
                    + codificar(mensaje));
        }
    }
}
