package br.com.netgifx.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoriaGrupo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Categoria categoria;
	private List<Filme> filmes = new ArrayList<Filme>();
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Filme> getFilmes() {
		return filmes;
	}
	public void setFilmes(Filme f) {
		this.filmes.add(f);
	}

}
