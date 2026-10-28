package com.app.logica;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

// Agregar anotación
   @Entity
   public class Cancion implements Serializable {

   
// Agregar anotación
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


// Agregar anotación
	@Basic
    private String titulo;
    private float duracion; 
    private int anio;

    public Cancion() { }

    public Cancion(String titulo, float duracion, int anio) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.anio = anio;
    }

    // getters/setters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public float getDuracion() { return duracion; }
    public void setDuracion(float duracion) { this.duracion = duracion; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
}


