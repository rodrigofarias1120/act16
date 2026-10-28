
package com.app.servlets;

import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/SvArtistaEliminar")
public class SvArtistaEliminar extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            control.eliminarArtista(id);

            response.sendRedirect("SvArtistaListar");

        } catch (NumberFormatException e) {
            redirigirConError(response, "El ID del artista no es válido.");
        } catch (Exception e) {
            String mensaje = e.getMessage() == null
                    ? "No se pudo eliminar el artista."
                    : e.getMessage();

            redirigirConError(response, mensaje);
        }
    }

    private void redirigirConError(
            HttpServletResponse response, String mensaje)
            throws IOException {

        String error = URLEncoder.encode(
                mensaje, StandardCharsets.UTF_8);

        response.sendRedirect("SvArtistaListar?error=" + error);
    }
}

