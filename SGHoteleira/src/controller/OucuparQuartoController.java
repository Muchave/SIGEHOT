package controller;


import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import model.Cliente;
import model.LinhaOcupacao;
import model.Ocupacao;
import model.Quarto;

import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;

import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import dao.ClienteDAO;
import dao.OcupacaoDAO;
import dao.QuartoDAO;

@SuppressWarnings({ "serial", "rawtypes" })
public class OucuparQuartoController extends GenericForwardComposer {
	private Textbox idProcurarCliente, idProcurarQuarto;
	private Listbox listaCliente, listaQuarto;
	private Label lbNomeCliente, lbApelido, lbHoraEntrada, lbTipoQuarto,
			lbNumeroDias, lbValorTotal;
	private Datebox idData;
	private Combobox cbxTipoPagamento;
	private Datebox dbDataEntrada, dbDataSaida;
	private Include layout;
	
	public void onProcurar(ForwardEvent e) {
		ClienteDAO cli = new ClienteDAO();
		List<Cliente> lista = cli.listarClienteDAO(idProcurarCliente.getText());
		listaCliente.setModel(new ListModelList<Cliente>(lista));
		limpar();
	}

	public void limpar() {
		lbNomeCliente.setValue(null);
		lbApelido.setValue(null);
		lbHoraEntrada.setValue(null);
		lbTipoQuarto.setValue(null);
		lbNumeroDias.setValue(null);
		lbValorTotal.setValue(null);
		idData.setValue(null);
		cbxTipoPagamento.setSelectedIndex(-1);
	}

	public void onSelect(ForwardEvent e) {
		Cliente listaSelecionada = listaCliente.getSelectedItem().getValue();
		lbNomeCliente.setValue(listaSelecionada.getNome());
		lbApelido.setValue(listaSelecionada.getApelido());
		OcupacaoDAO ocupadao = new OcupacaoDAO();
		lbHoraEntrada.setValue(ocupadao.buscarHoraDAO().toString());
		idData.setValue(Date.valueOf(String.valueOf(ocupadao.buscarDataDAO()
				.get(0))));
	}

	public void onProcurarQuarto(ForwardEvent e) {
		if (!(dbDataEntrada.getText().isEmpty())) {
			if (!(dbDataSaida.getText().isEmpty())) {
				QuartoDAO qua = new QuartoDAO();
				List<Quarto> lista = qua.listarQuartoLivreDAO(
						idProcurarQuarto.getText(), dbDataEntrada.getText(),
						dbDataSaida.getText());
				listaQuarto.setModel(new ListModelList<Quarto>(lista));
				limpar();
			} else {
				Messagebox.show("Selecione uma data de saida!");
			}
		} else {
			Messagebox.show("Selecione uma data de entreda!");
		}
	}

	public void onSelecionar(ForwardEvent e) {
		if (!(dbDataSaida.getText().isEmpty())) {
			if (!(dbDataEntrada.getText().isEmpty())) {
				Quarto listaSelecionada = listaQuarto.getSelectedItem()
						.getValue();
				OcupacaoDAO ocudao = new OcupacaoDAO();
				Double noites = Double.valueOf(ocudao
						.diferencaDeNoitesOcupacao(dbDataSaida.getText(),
								dbDataEntrada.getText()));
				lbTipoQuarto.setValue(String.valueOf(listaSelecionada
						.getNumeroQuarto()));
				lbNumeroDias.setValue(String.valueOf(noites));
				lbValorTotal.setValue(String.valueOf(noites
						* listaSelecionada.getPrecoQuarto()));
			} else {
				Messagebox.show("Selecione uma data de Entrada!");
			}
		} else {
			Messagebox.show("Selecione uma data de Saida!");
		}
	}

	public void onOcupacao(ForwardEvent e) {
		if (!(cbxTipoPagamento.getValue().isEmpty())) {
			OcupacaoDAO dao = new OcupacaoDAO();

			if (idData.getText().equals(dbDataEntrada.getText())) {
				if (dbDataSaida.getValue().compareTo(dbDataEntrada.getValue()) != -1) {
					List<LinhaOcupacao> linh = new ArrayList<LinhaOcupacao>();
					Quarto linhaSlecionada = listaQuarto.getSelectedItem()
							.getValue();
					Cliente linhss = listaCliente.getSelectedItem().getValue();
					Quarto quart = new Quarto();
					LinhaOcupacao linha = new LinhaOcupacao();
					linha.setPrecoQuarto(linhaSlecionada.getPrecoQuarto());
					linha.setEstado("ocupado");
					linha.setDataSaida(dbDataSaida.getValue());
					linha.setHoraEntrada(Time.valueOf(String.valueOf(dao
							.buscarHoraDAO().get(0))));
					linha.setDataEntrada(dbDataSaida.getValue());
					quart.setIdQuarto(linhaSlecionada.getIdQuarto());
					linha.setQuarto(quart);
					linh.add(linha);
					Cliente cli = new Cliente();
					Quarto qua = new Quarto();
					Ocupacao ocu = new Ocupacao();
					qua.setIdQuarto(linhaSlecionada.getIdQuarto());
					cli.setIdCliente(linhss.getIdCliente());
					ocu.setCliente(cli);
					ocu.setLinhaOcupacaoLista(linh);
					ocu.setTipoPagamento(String.valueOf(cbxTipoPagamento
							.getSelectedItem()));
					ocu.setTotal(Double.valueOf(lbValorTotal.getValue()));
					ocu.setValorPago(Double.valueOf(lbValorTotal.getValue()));

					OcupacaoDAO ocudao = new OcupacaoDAO();
					ocudao.gravarOcupacaoDAO(ocu);
					Messagebox
							.show("Ocupacao do quarto efectuado com Sucesso!");
					onProcurarQuarto(e);
				} else {
					Messagebox
							.show("A data de entrada é superior a data de saída!");
				}
			} else {
				Messagebox
						.show("A data de Entrada e diferente da data de hoje!");
			}
		} else {
			Messagebox.show("Selecione um tipo de pagamento!");
		}
	}

	public void onReserva(ForwardEvent e) throws SQLException, IOException {	
		layout.setSrc("recibo.zul");
	}

	public void onNovo(ForwardEvent e) {
		limpar();
		dbDataEntrada.setValue(null);
		dbDataSaida.setValue(null);
		idProcurarCliente.setText(null);
		idProcurarQuarto.setText(null);
	}
}
