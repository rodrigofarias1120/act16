
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

@WebServlet("/SvCancionCrear")
public class SvCancionCrear extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String titulo = request.getParameter("titulo");
            float duracion = Float.parseFloat(
                    request.getParameter("duracion"));
            int anio = Integer.parseInt(
                    request.getParameter("anio"));

            Cancion cancion = new Cancion(titulo, duracion, anio);

            control.crearCancion(cancion);

            response.sendRedirect("SvCancionListar");

        } catch (NumberFormatException e) {
            redirigirConError(response,
                    "La duración y el año deben ser números válidos.");

        } catch (Exception e) {
            String mensaje = e.getMessage();

            if (mensaje == null || mensaje.isBlank()) {
                mensaje = "No se pudo crear la canción.";
            }

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
