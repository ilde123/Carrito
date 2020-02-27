package com.example.carrito;

public class Fruta {
	private String nombre;
	private String descripcion;
	private int imagen;
	private int stock;
	private int precio;
//	private int cantidad;

	public Fruta(String nombre, String descripcion, int imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.stock = 100;
		this.precio = 100;
//		this.cantidad = 100;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

/*	public int getCantidad() {
		return cantidad;
	}*/

/*	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}*/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getImagen() {
		return imagen;
	}

	public void setImagen(int imagen) {
		this.imagen = imagen;
	}
}
