package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "direito")
public class Direito implements Serializable {
	@Id
	@GeneratedValue
	private int idDireito;
	public int getIdDireito() {
		return idDireito;
	}
	public void setIdDireito(int idDireito) {
		this.idDireito = idDireito;
	}
	public String getDireitos() {
		return direitos;
	}
	public void setDireitos(String direitos) {
		this.direitos = direitos;
	}
	@Column(nullable=false)
	private String direitos;
}