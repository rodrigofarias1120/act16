
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

@WebServlet("/SvArtistaCrear")
public class SvArtistaCrear extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String nombre = request.getParameter("nombre");
            String genero = request.getParameter("generoMusical");
            String nacionalidad = request.getParameter("nacionalidad");

            int edad = Integer.parseInt(request.getParameter("edad"));

            Artista artista = new Artista();
            artista.setNombre(nombre);
            artista.setGeneroMusical(genero);
            artista.setNacionalidad(nacionalidad);
            artista.setEdad(edad);

            control.crearArtista(artista);

            response.sendRedirect("SvArtistaListar");

        } catch (NumberFormatException e) {
            redirigirConError(response,
                    "La edad debe ser un número entero.");

        } catch (Exception e) {
            String mensaje = e.getMessage();

            if (mensaje == null || mensaje.isBlank()) {
                mensaje = "No se pudo crear el artista.";
            }

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
