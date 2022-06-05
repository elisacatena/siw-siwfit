package it.uniroma3.siw.siwfit.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@OneToMany//(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id")
	private List<Corso> corsi;
	
	public Categoria() {
		this.corsi = new ArrayList<Corso>();
	}
	
	public Categoria(String nome, List<Corso> corsi) {
		this.nome = nome;
		this.corsi = corsi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Corso> getCorsi() {
		return corsi;
	}

	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode() + 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return this.getNome().equals(other.getNome());
	}

}
