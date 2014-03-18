package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@SuppressWarnings("serial")
@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {
	@Id
	@GeneratedValue
	private int idPerfil;
	public int getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getNomePerfil() {
		return nomePerfil;
	}
	public void setNomePerfil(String direitosPerfil) {
		this.nomePerfil = direitosPerfil;
	}
	@Column(nullable=false)
	private String nomePerfil;
	
	@SuppressWarnings("deprecation")
	@OneToMany (cascade = {CascadeType.ALL},fetch = FetchType.EAGER	)
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@JoinColumn (name = "id_perfill")
	private List<Direito> direitoLista=new ArrayList<Direito>();
	
	public List<Direito> getDireitoLista() {
		return direitoLista;
	}
	
	public void setDireitoLista(List<Direito> direitoLista) {
		this.direitoLista = direitoLista;
	}
}
