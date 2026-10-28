
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

@WebServlet("/SvCancionEliminar")
public class SvCancionEliminar extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            control.eliminarCancion(id);

            response.sendRedirect("SvCancionListar");

        } catch (NumberFormatException e) {
            redirigirConError(response, "El ID de la canción no es válido.");
        } catch (Exception e) {
            String mensaje = e.getMessage() == null
                    ? "No se pudo eliminar la canción."
                    : e.getMessage();

            redirigirConError(response, mensaje);
        }
    }

    private void redirigirConError(
            HttpServletResponse response, String mensaje)
            throws IOException {

        String error = URLEncoder.encode(
                mensaje, StandardCharsets.UTF_8);

        response.sendRedirect("SvCancionListar?error=" + error);
    }
}
