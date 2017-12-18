package br.com.netgifx.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gifx_filme")
public class Filme implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "filmeid")
	private int id;
	
	@Column(length = 32, nullable = false)
	private String titulo;
	
	@Column(length = 800, nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private String classificacao;
	
	@Column(nullable = false)
	private String idioma;
	
	@Lob
	private byte[] fileImage;
	
	@Lob
	private byte[] previewImage;
	
	private String fileImagePath;
	
	private String previewImagePath;
	
	@ManyToOne
	@JoinColumn(name = "categoriaid", nullable = false)
	private Categoria categoria;
	
	@ManyToMany(mappedBy = "filmes")
	private Set<Usuario> usuarios = new HashSet<>();
	
	private LocalDate dataCadastro;

	public Filme() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public byte[] getFileImage() {
		return fileImage;
	}

	public void setFileImage(byte[] fileImage) {
		this.fileImage = fileImage;
	}

	public byte[] getPreviewImage() {
		return previewImage;
	}

	public void setPreviewImage(byte[] previewImage) {
		this.previewImage = previewImage;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getFileImagePath() {
		return fileImagePath;
	}

	public void setFileImagePath(String fileImagePath) {
		this.fileImagePath = fileImagePath;
	}

	public String getPreviewImagePath() {
		return previewImagePath;
	}

	public void setPreviewImagePath(String previewImagePath) {
		this.previewImagePath = previewImagePath;
	}

}
