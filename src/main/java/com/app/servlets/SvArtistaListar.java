
package com.app.servlets;

import java.io.IOException;
import java.util.List;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvArtistaListar")
public class SvArtistaListar extends HttpServlet {

    private ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Artista> artistas = controladora.listarArtistas();

        request.setAttribute("artistas", artistas);

        request.getRequestDispatcher(
        	    "/WEB-INF/vistas/artistas.jsp"
        	).forward(request, response);
    }
}
