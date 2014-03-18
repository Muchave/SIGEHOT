package controller;

import java.util.List;

import model.Quarto;

import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import dao.QuartoDAO;

@SuppressWarnings({ "serial", "rawtypes" })
public class QuartosController extends GenericForwardComposer {
	private Textbox idNumQuarto, idPreco, idTipoQuarto, idDiscricaoQuarto,
			idProcurar;
	private Listbox listaQuarto;

	public void onSalvar(ForwardEvent e) {
		try {
			Quarto quarto = new Quarto();
			quarto.setDiscricaoQuarto(idDiscricaoQuarto.getText());
			quarto.setNumeroQuarto(Integer.parseInt(idNumQuarto.getText()));
			quarto.setPrecoQuarto(Double.parseDouble(idPreco.getText()));
			quarto.setTipoQuarto(idTipoQuarto.getText());
			QuartoDAO quadao = new QuartoDAO();
			quadao.gravarQuartoDAO(quarto);
			onNovo(e);
			onProcurar(e);
			Messagebox.show("Gravado com Sucesso!");
		} catch (Exception ex) {
			Messagebox.show("Ocorreu um erro ao gravar!");
		}
	}

	public void onActualizar(ForwardEvent e) {
		try {
			if (listaQuarto.getSelectedItem().isSelected()) {
				Quarto quart = listaQuarto.getSelectedItem().getValue();
				try {
					Quarto qua = new Quarto();
					qua.setDiscricaoQuarto(idDiscricaoQuarto.getText());
					qua.setNumeroQuarto(Integer.parseInt(idNumQuarto.getText()));
					qua.setPrecoQuarto(Double.parseDouble(idPreco.getText()));
					qua.setTipoQuarto(idTipoQuarto.getText());
					qua.setIdQuarto(quart.getIdQuarto());

					QuartoDAO clidao = new QuartoDAO();
					clidao.actualizarQuartoDAO(qua);
					onNovo(e);
					Messagebox.show("Actualizado com Sucesso!");
				} catch (Exception ex) {
					Messagebox.show("Ocorreu um erro ao Actualizar!");
				}
			} else {
				Messagebox.show("Selecione um Quarto para poder Actualizar!");
			}
		} catch (java.lang.NullPointerException err) {
			Messagebox.show("Selecione um Quarto para poder Actualizar!");
		}
	}

	public void onNovo(ForwardEvent e) {
		idNumQuarto.setText(null);
		idPreco.setText(null);
		idTipoQuarto.setText(null);
		idDiscricaoQuarto.setText(null);
		idProcurar.setText(null);
		onProcurar(e);
	}

	public void onProcurar(ForwardEvent e) {
		QuartoDAO quarto = new QuartoDAO();
		List<Quarto> lista = quarto.listarQuartoDAO("%" + idProcurar.getText()
				+ "%");
		listaQuarto.setModel(new ListModelList<Quarto>(lista));
	}

	public void onSelect(ForwardEvent e) {
		Quarto listaSelecionada = listaQuarto.getSelectedItem().getValue();
		idNumQuarto.setText(String.valueOf(listaSelecionada.getNumeroQuarto()));
		idPreco.setText(String.valueOf(listaSelecionada.getPrecoQuarto()));
		idTipoQuarto.setText(listaSelecionada.getTipoQuarto());
		idDiscricaoQuarto.setText(listaSelecionada.getDiscricaoQuarto());
	}
}
