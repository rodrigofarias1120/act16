package com.app.persistencia;

import com.app.logica.Cancion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;


public class CancionJpaController {

	// "Fábrica" de conexiones con la base de datos.
	// Nos permite crear EntityManager, que son los que realmente hacen el trabajo.
	private EntityManagerFactory emf = null;

	// En el constructor indicamos qué unidad de persistencia usar.
	// El nombre "ejemplojpa" debe coincidir con el que está en persistence.xml.
	public CancionJpaController() {
		emf = Persistence.createEntityManagerFactory("mi-unidad-jpa");
	}

	// Método que devuelve un nuevo EntityManager (una conexión lista para
	// trabajar).
	// Podés pensarlo como: "dame un empleado que se conecte a la BD".
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// BUSCAR UNO: busca un artista por su id.
	// No hace falta transacción porque no modifica nada en la base.
	public Cancion buscarUno(int id) {
		EntityManager em = getEntityManager();
		try {
			// find busca por clave primaria. Si no existe, devuelve null.
			return em.find(Cancion.class, id);
		} finally {
			em.close();
		}
	}

	// BUSCAR TODOS: devuelve una lista con todos los artistas.
	public List<Cancion> buscarTodos() {
		EntityManager em = getEntityManager();
		try {
			// Consulta JPQL: "traeme todos los objetos Artista"
			return em.createQuery("SELECT a FROM Cancion a", Cancion.class).getResultList();
		} finally {
			em.close();
		}
	}

	// Método para crear (persistir) una Canción en la base de datos.
	public void crear(Cancion canc) {
		EntityManager em = null;
		try {
			// Abrimos la conexión (traemos al empleado).
			em = getEntityManager();

			// Iniciamos la transacción (arranca la promesa de que se hará todo o nada).
			em.getTransaction().begin();

			// Guardamos el objeto Cancion en la base (todavía no confirmado).
			em.persist(canc);

			// Confirmamos la transacción: ahora sí, el dato queda guardado en la BD.
			em.getTransaction().commit();
		} finally {
			// Cerramos la conexión si estaba abierta (el empleado termina su turno).
			if (em != null) {
				em.close();
			}
		}
	}
	
	// editar
    public void editar(Cancion canc) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(canc);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // eliminar por id
    public void eliminar(int id) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            Cancion canc = em.find(Cancion.class, id);

            if (canc == null) {
                throw new IllegalArgumentException(
                    "No existe la canción con ID " + id
                );
            }

            em.remove(canc);
            em.getTransaction().commit();

        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }




	
	
	
	
}
