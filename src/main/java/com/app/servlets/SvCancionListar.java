
package com.app.servlets;

import java.io.IOException;
import java.util.List;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvCancionListar")
public class SvCancionListar extends HttpServlet {

    private ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Cancion> canciones = controladora.listarCanciones();

        request.setAttribute("canciones", canciones);

        request.getRequestDispatcher(
                "/WEB-INF/vistas/canciones.jsp"
        ).forward(request, response);
    }
}
