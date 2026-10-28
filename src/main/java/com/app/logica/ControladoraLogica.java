package com.app.logica;

import java.util.List;

import com.app.persistencia.ControladoraPersistencia;

public class ControladoraLogica {

	// La controladora de persistencia se encarga de hablar con la BD.
	// Desde aquí la usamos como "puente" para guardar datos.
	ControladoraPersistencia controlPersis = new ControladoraPersistencia();
//

	// ----- ARTISTA -----
	// Método de la capa lógica para crear un artista.
	// Antes de guardarlo en la BD, se hacen validaciones.
	public void crearArtista(Artista art) throws Exception {
		// Validación: el artista debe tener un nombre
		if (art.getNombre() == null || art.getNombre().isEmpty()) {
			throw new Exception("El artista debe tener un nombre.");
		}
		// Validación: el artista debe tener un género musical
		if (art.getGeneroMusical() == null || art.getGeneroMusical().isEmpty()) {
			throw new Exception("El artista debe tener un género musical.");
		}
		// Validación: la edad debe ser positiva
		if (art.getEdad() <= 0) {
			throw new Exception("La edad del artista debe ser mayor a 0.");
		}

		// AGREGAR AL MENOS UNA VALIDACIÓN MAS

		// Si pasa todas las validaciones, recién ahí se manda a la persistencia
		controlPersis.crearArtista(art);
		// se manda el artista a controladora de persostencia
	}

	// ----- CANCIÓN -----
	// Método de la capa lógica para crear una canción.
	// Se valida que tenga datos coherentes antes de persistirla.
	public void crearCancion(Cancion can) throws Exception {
		// Validación: el título no puede estar vacío
		if (can.getTitulo() == null || can.getTitulo().isEmpty()) {
			throw new Exception("La canción debe tener un título.");
		}
		// Validación: la duración debe ser mayor a cero
		if (can.getDuracion() <= 0) {
			throw new Exception("La duración debe ser mayor a 0 segundos.");
		}
		// Validación: el año debe tener sentido
		if (can.getAnio() < 1900) {
			throw new Exception("El año de la canción debe ser válido.");
		}

		// AGREGAR AL MENOS UNA VALIDACIÓN MAS

		// Si pasa todas las validaciones, se guarda en la base de datos
		controlPersis.crearCancion(can);
	}

	// BUSCAR UN ARTISTA por su id
	public Artista buscarUnArtista(int id) {
		return controlPersis.buscarArtista(id);
	}

	// LISTAR TODOS LOS ARTISTAS
	public List<Artista> listarArtistas() {
		return controlPersis.buscarTodosLosArtistas();
	}

	// BUSCAR UN ARTISTA por su id
	public Cancion buscarUnaCancion(int id) {
		return controlPersis.buscarCancion(id);
	}

	// LISTAR TODOS LOS ARTISTAS
	public List<Cancion> listarCanciones() {
		return controlPersis.buscarTodasLasCanciones();
	}

    // EDITAR ARTISTA 

    public void editarArtista(Artista art) throws Exception {

        if (art == null || art.getId() <= 0) {
            throw new Exception("El ID del artista no es válido.");
        }

        Artista existente = controlPersis.buscarArtista(art.getId());

        if (existente == null) {
            throw new Exception("No existe el artista que querés editar.");
        }

        // Aplicar las mismas validaciones que en el alta.
        if (art.getNombre() == null || art.getNombre().trim().isEmpty()) {
            throw new Exception("El artista debe tener un nombre.");
        }

        if (art.getGeneroMusical() == null
                || art.getGeneroMusical().trim().isEmpty()) {
            throw new Exception("El artista debe tener un género musical.");
        }

        if (art.getEdad() <= 0) {
            throw new Exception("La edad del artista debe ser mayor a 0.");
        }

        controlPersis.editarArtista(art);
    }

    // ELIMINAR ARTISTA 

    public void eliminarArtista(int id) throws Exception {

        if (id <= 0) {
            throw new Exception("El ID del artista no es válido.");
        }

        if (controlPersis.buscarArtista(id) == null) {
            throw new Exception("No existe el artista que querés eliminar.");
        }

        controlPersis.eliminarArtista(id);
    }

    // EDITAR CANCIÓN 

    public void editarCancion(Cancion can) throws Exception {

        if (can == null || can.getId() <= 0) {
            throw new Exception("El ID de la canción no es válido.");
        }

        Cancion existente = controlPersis.buscarCancion(can.getId());

        if (existente == null) {
            throw new Exception("No existe la canción que querés editar.");
        }

        // Aplicar las mismas validaciones que en el alta.
        if (can.getTitulo() == null || can.getTitulo().trim().isEmpty()) {
            throw new Exception("La canción debe tener un título.");
        }

        if (can.getDuracion() <= 0) {
            throw new Exception("La duración debe ser mayor a 0.");
        }

        if (can.getAnio() < 1900) {
            throw new Exception("El año de la canción debe ser válido.");
        }

        controlPersis.editarCancion(can);
    }

    // ELIMINAR CANCIÓN 

    public void eliminarCancion(int id) throws Exception {

        if (id <= 0) {
            throw new Exception("El ID de la canción no es válido.");
        }

        if (controlPersis.buscarCancion(id) == null) {
            throw new Exception("No existe la canción que querés eliminar.");
        }

        controlPersis.eliminarCancion(id);
    }

}
