package com.app.presentacion;

import com.app.logica.Artista;
import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		// INSTANCIAR DOS ARTISTAS

		// INSTANCIAR DOS CANCIONES

		ControladoraLogica conLog = new ControladoraLogica();
		try {
			// CREAR AL MENOS DOS ARTISTAS, Y DOS CANCIONES

			Calendar cal1 = new GregorianCalendar(1972, Calendar.AUGUST, 9);
			Date fechamilei = cal1.getTime();

			Calendar cal2 = new GregorianCalendar(2009, Calendar.DECEMBER, 11);
			Date fechaChepe = cal2.getTime();

			Artista a1 = new Artista("milei", "rock", "argentina", 60, fechamilei);
			conLog.crearArtista(a1);

			Artista a2 = new Artista("chepe", "de todo", "argentina", 17, fechaChepe);
			conLog.crearArtista(a2);
			// INSTANCIAR DOS CANCIONES
			Cancion c1 = new Cancion("tusa", 3.25f, 2002);
			conLog.crearCancion(c1);
			Cancion c2 = new Cancion("hola", 2.25f, 2022);
			conLog.crearCancion(c2);
		} catch (Exception e) {
			System.out.println("No se pudo crear el elemento: " + e.getMessage());
		}

		// ----- LECTURA DE ARTISTAS -----
		System.out.println("=== TODOS LOS ARTISTAS ===");
		List<Artista> artistas = conLog.listarArtistas();
		for (Artista a : artistas) {
			System.out.println(a.toString());
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 1 ===");
		Artista encontrado = conLog.buscarUnArtista(1);
		if (encontrado != null) {
			System.out.println(encontrado);
		} else {
			System.out.println("No existe un artista con ese id");
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 999 ===");
		Artista noExiste = conLog.buscarUnArtista(999);
		if (noExiste != null) {
			System.out.println(noExiste);
		} else {
			System.out.println("No existe un artista con ese id");
		}

	}
}
