package io.bitbucket.jimenezjijonpedrorafael.jee07.pojos;

import java.util.Date;

public class Autor {
	private int id;
	private String Nombre;
	private String genero;
	private String descripcion;	
	private Date fechaNacimiento;
	
	public Autor(){}
	
	public Autor(int id, String nombre, String genero, String descripcion, Date fechaNacimiento) {
		super();
		this.id = id;
		Nombre = nombre;
		this.genero = genero;
		this.descripcion = descripcion;
		this.fechaNacimiento = fechaNacimiento;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
}
