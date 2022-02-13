package vulgata.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Biblia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8879174997184212210L;
	String titulo;
	List<Libro> capitulos;
	String idioma;
	public Biblia(String titulo, List<Libro> capitulos, String idioma) {
		super();
		this.titulo = titulo;
		this.capitulos = capitulos;
		this.idioma = idioma;
	}
	public Biblia() {
		super();
		capitulos= new ArrayList<>();
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<Libro> getCapitulos() {
		return capitulos;
	}
	public void setCapitulos(List<Libro> capitulos) {
		this.capitulos = capitulos;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	
	
	
	
	

}
