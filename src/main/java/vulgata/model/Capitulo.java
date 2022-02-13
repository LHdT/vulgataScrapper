package vulgata.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Capitulo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2386670232420788548L;
	private Integer numero;
	private String titulo;
	private List<String> versiculos;
	
	
	
	

	public Capitulo() {
		super();
		versiculos= new ArrayList<>();
	}

	public Capitulo(Integer numero, String titulo, List<String> versiculos) {
		super();
		this.numero = numero;
		this.titulo = titulo;
		this.versiculos = versiculos;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<String> getVersiculos() {
		return versiculos;
	}

	public void setVersiculos(List<String> versiculos) {
		this.versiculos = versiculos;
	}
	
	
	
}
