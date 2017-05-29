package io.bitbucket.jimenezjijonpedrorafael.jee07.pojos;

import java.util.Date;

public class Libro {
	private int id;
	private String autor;
	private String genero;
	private String descripcion;
	private boolean disponible;
	private double precio;
	private Date fechaPublicacion;
	
	public Libro() {
	}

	public Libro(int id,
			String autor,
			String genero,
			String descripcion,
			boolean disponible,
			double precio,
			Date fechaPublicacion) {
		
		this.id = id;
		this.autor = autor;
		this.genero = genero;
		this.descripcion = descripcion;
		this.disponible = disponible;
		this.precio = precio;
		this.fechaPublicacion = fechaPublicacion;
	}

	public int getId() {
		return id;
	}

	public String getAutor() {
		return autor;
	}

	public String getGenero() {
		return genero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public double getPrecio() {
		return precio;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	
}
