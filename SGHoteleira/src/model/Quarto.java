package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="quarto")
public class Quarto implements Serializable {

    public String getDiscricaoQuarto() {
        return discricaoQuarto;
    }

    public void setDiscricaoQuarto(String discricaoQuarto) {
        this.discricaoQuarto = discricaoQuarto;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public double getPrecoQuarto() {
        return precoQuarto;
    }

    public void setPrecoQuarto(double precoQuarto) {
        this.precoQuarto = precoQuarto;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }
    @Id
    @GeneratedValue
    private int idQuarto;
    @Column(nullable=false)
    private int numeroQuarto;
    @Column(nullable=false)
    private String discricaoQuarto;
    @Column(nullable=false)
    private double precoQuarto;
    @Column(nullable=false)
    private String tipoQuarto;
}
