package it.uniroma3.siw.siwfit.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Corso {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String data;
	
	private String difficolta;
	
	private String durata;
	
	@Column(columnDefinition="TEXT")
	private String descrizione;
	
	private String sala;
	
	private int numeroMaxPersone;
	
	@ManyToOne
	private Trainer trainer;
	
	@ManyToOne
	private Categoria categoria;
	
	@ManyToMany
	private List<User> iscritti;
	
	private String img;
	
	public Corso() {
		this.iscritti = new ArrayList<User>();
	}

	public Corso(String nome, String data, String difficolta, String durata, String descrizione, String sala,
			int numeroMaxPersone, Trainer trainer, Categoria categoria, String img) {
		this.nome = nome;
		this.data = data;
		this.difficolta = difficolta;
		this.durata = durata;
		this.descrizione = descrizione;
		this.sala = sala;
		this.numeroMaxPersone = numeroMaxPersone;
		this.trainer = trainer;
		this.img = img;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDifficolta() {
		return difficolta;
	}
	public void setDifficolta(String difficolta) {
		this.difficolta = difficolta;
	}
	public String getDurata() {
		return durata;
	}
	public void setDurata(String durata) {
		this.durata = durata;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	
	public int getNumeroMaxPersone() {
		return numeroMaxPersone;
	}

	public void setNumeroMaxPersone(int numeroMaxPersone) {
		this.numeroMaxPersone = numeroMaxPersone;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<User> getIscritti() {
		return iscritti;
	}

	public void setIscritti(List<User> iscritti) {
		this.iscritti = iscritti;
	}
	

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public int hashCode() {
		return this.getData().hashCode() + this.getNome().hashCode() + 31;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		return this.getNome().equals(other.getNome()) && this.getData() == other.getData();
	}	
	
}
