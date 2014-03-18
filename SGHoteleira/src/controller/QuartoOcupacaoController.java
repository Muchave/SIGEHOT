package controller;

import java.util.List;

import model.LinhaOcupacao;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import dao.OcupacaoDAO;

@SuppressWarnings({ "rawtypes", "serial" })
public class QuartoOcupacaoController extends GenericForwardComposer {

	private Textbox tbNumeroQuarto;
	private Datebox dbData;
	private Listbox lsQuarto;

	public void onClick$btnprocurar() {
		if ((dbData.getValue() != null)) {
			OcupacaoDAO ocupacao = new OcupacaoDAO();
			List<LinhaOcupacao> lista = ocupacao.listarQuartoOcupadoDAO("%"
					+ tbNumeroQuarto.getText() + "%", dbData.getText());
			lsQuarto.setModel(new ListModelList<LinhaOcupacao>(lista));
		} else {
			Messagebox.show("Escolha uma data!");
		}
	}

	public void onClick$btConfirmar() {
		try {
			if (lsQuarto.getSelectedItem().isSelected()) {
				LinhaOcupacao linh = lsQuarto.getSelectedItem().getValue();
				if (!(linh.getEstado().equals("ocupado"))) {
					LinhaOcupacao linha = new LinhaOcupacao();
					linha.setDataEntrada(linh.getDataEntrada());
					linha.setDataSaida(linh.getDataSaida());
					linha.setEstado("ocupado");
					linha.setHoraEntrada(linh.getHoraEntrada());
					linha.setIdLinha(linh.getIdLinha());
					linha.setOcupacao(linh.getOcupacao());
					linha.setPrecoQuarto(linh.getPrecoQuarto());
					linha.setQuarto(linh.getQuarto());

					OcupacaoDAO dao = new OcupacaoDAO();
					dao.actualizarLinhaOcupacaoDAO(linha);
					List<LinhaOcupacao> lista = dao.listarQuartoOcupadoDAO("%"
							+ tbNumeroQuarto.getText() + "%", dbData.getText());
					lsQuarto.setModel(new ListModelList<LinhaOcupacao>(lista));
					Messagebox.show("Confirmacao efectuada com Sucesso!");
				} else {
					Messagebox
							.show("Seleciona um quarto reservado para confirmar a ocupação!");
				}
			} else {
				Messagebox.show("Selecione uma Ocupação para poder Confirmar!");
			}
		} catch (java.lang.NullPointerException err) {
			Messagebox.show("Selecione uma Ocupação para poder Confirmar!");
		}
	}

	public void onClick$btnActualizar() {

	}
}
