package vulgata.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Libro implements Serializable{
	List<Capitulo> capitulos;
	String Titulo;
	
	public Libro() {
		super();
		capitulos=new ArrayList<>();
	}
	
	
	public Libro(List<Capitulo> capitulos, String titulo) {
		super();
		this.capitulos = capitulos;
		Titulo = titulo;
	}


	public List<Capitulo> getCapitulos() {
		return capitulos;
	}
	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	
	

}
