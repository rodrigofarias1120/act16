package com.app.logica;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Artista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    private String nombre;
    private String generoMusical;
    private String nacionalidad;
    private int edad;
   
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    

    public Artista() { }

    public Artista(String nombre, String generoMusical, String nacionalidad, int edad, Date fechaNacimiento ) {
        this.nombre = nombre;
        this.generoMusical = generoMusical;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
    }

    // getters/setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getGeneroMusical() { return generoMusical; }
    public void setGeneroMusical(String generoMusical) { this.generoMusical = generoMusical; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

	@Override
	public String toString() {
		return "Artista [id=" + id + ", nombre=" + nombre + ", generoMusical=" + generoMusical + ", nacionalidad="
				+ nacionalidad + ", edad=" + edad + ", fechaNacimiento=" + fechaNacimiento + ", getId()=" + getId()
				+ ", getNombre()=" + getNombre() + ", getGeneroMusical()=" + getGeneroMusical() + ", getNacionalidad()="
				+ getNacionalidad() + ", getEdad()=" + getEdad() + ", getFechaNacimiento()=" + getFechaNacimiento()
				+ "]";
	}

	


   





}
