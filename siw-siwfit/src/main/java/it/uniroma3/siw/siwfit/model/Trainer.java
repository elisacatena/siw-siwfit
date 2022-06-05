package it.uniroma3.siw.siwfit.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Trainer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	private String descrizione;
	
	@OneToMany
	@JoinColumn(name = "trainer_id")
	private List<Corso> corsi;
	
	public Trainer() {
		this.corsi = new ArrayList<Corso>();
	}
	
	public Trainer(String nome, String cognome, String descrizione, List<Corso> corsi) {
		this.nome = nome;
		this.cognome = cognome;
		this.descrizione = descrizione;
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
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public List<Corso> getCorsi() {
		return corsi;
	}
	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}

	@Override
	public int hashCode() {
		return this.getId().hashCode() + 31;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainer other = (Trainer) obj;
		return this.getId() == other.getId();
	}

}
