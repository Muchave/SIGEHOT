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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

@SuppressWarnings("serial")
@Entity
@Table(name = "ocupacao")
public class Ocupacao implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    @ManyToOne
    private Cliente cliente;
    @Column(nullable = false)
    private String tipoPagamento;
    @Column(nullable = false)
    private double total;
    @SuppressWarnings("deprecation")
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinColumn(name = "id_ocupacao")
    private List<LinhaOcupacao> linhaOcupacaoLista = new ArrayList<LinhaOcupacao>();
    @Column
    private Double valorPago;

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }
    public List<LinhaOcupacao> getLinhaOcupacaoLista() {
        return linhaOcupacaoLista;
    }

    public void setLinhaOcupacaoLista(List<LinhaOcupacao> linhaOcupacaoLista) {
        this.linhaOcupacaoLista = linhaOcupacaoLista;
    }
}
