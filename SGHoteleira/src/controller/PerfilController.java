package controller;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import dao.PerfilDAO;
import dao.UtilizadorDAO;

import model.Direito;
import model.Perfil;
import model.PerfilUtilizador;
import model.Utilizador;

@SuppressWarnings({ "rawtypes", "serial" })
public class PerfilController extends GenericForwardComposer {
	private Textbox idNomePerfil, idProcurarUsuario, idProcurarPerfil;
	private Checkbox cliente;
	private Checkbox administracao;
	private Checkbox ocupacao;
	private Listbox listaUtilizador, listaPerfil;

	public void onCriar(ForwardEvent e) {
		Perfil perf = new Perfil();

		perf.setNomePerfil(idNomePerfil.getText());
		PerfilDAO preDAO = new PerfilDAO();

		List<Direito> item = new ArrayList<Direito>();

		if (cliente.isChecked()) {
			Direito dir = new Direito();
			dir.setDireitos("Cliente");
			item.add(dir);
		}
		if (ocupacao.isChecked()) {
			Direito dir = new Direito();
			dir.setDireitos("Ocupacao");
			item.add(dir);
		}
		if (administracao.isChecked()) {
			Direito dir = new Direito();
			dir.setDireitos("Administracao");
			item.add(dir);
		}
		perf.setDireitoLista(item);
		preDAO.gravarPerfilDAO(perf);
		Messagebox.show("Perfil Criado com Sucesso!");
	}

	public void onProcurarUsuario(ForwardEvent e) {
		UtilizadorDAO perf = new UtilizadorDAO();
		List<Utilizador> listarUtilizador = perf
				.listarUtilizadorDAO(idProcurarUsuario.getText());
		listaUtilizador
				.setModel(new ListModelList<Utilizador>(listarUtilizador));
	}

	public void onProcurarPerfil(ForwardEvent e) {
		PerfilDAO perf = new PerfilDAO();
		List<Perfil> listarPerfil = perf.listarPerfilDAO(idProcurarPerfil
				.getText());
		listaPerfil.setModel(new ListModelList<Perfil>(listarPerfil));
	}

	public void onAlocar(ForwardEvent e) {
		try {
			if (!(Executions.getCurrent().getDesktop().getSession()
					.getAttribute("clicarUTilizador").equals(null))
					&& !(Executions.getCurrent().getDesktop().getSession()
							.getAttribute("clicarPerfil").equals(null))) {
				Utilizador listaUtil = (Utilizador) Executions.getCurrent()
						.getDesktop().getSession()
						.getAttribute("clicarUTilizador");
				Perfil listaPerf = (Perfil) Executions.getCurrent()
						.getDesktop().getSession().getAttribute("clicarPerfil");
				PerfilUtilizador perfUti = new PerfilUtilizador();
				perfUti.setPerfil(listaPerf);
				perfUti.setUtilizador(listaUtil);
				PerfilDAO perDAO = new PerfilDAO();
				perDAO.alocarPerfilUtilizador(perfUti);
				Executions.getCurrent().getDesktop().getSession()
						.removeAttribute("clicarUTilizador");
				Executions.getCurrent().getDesktop().getSession()
						.removeAttribute("clicarPerfil");
				Messagebox.show("Utilizador Alocar Com Sucesso!");
			} else {
				Messagebox.show("Selecione Um Utilizador e Um Perfil!");
			}
		} catch (java.lang.NullPointerException eun) {
			Messagebox.show("Selecione Um Utilizador e Um Perfil!");
		}
	}

	public void onSelectPerfil(ForwardEvent e) {
		try{
		Perfil perfil = listaPerfil.getSelectedItem().getValue();
		Executions.getCurrent().getDesktop().getSession()
				.setAttribute("clicarPerfil", perfil);
		}catch(java.lang.NullPointerException ess){
			
		}
	}

	public void onSelectUtilizador(ForwardEvent e) {
		try{
		Utilizador usuario = listaUtilizador.getSelectedItem().getValue();
		Executions.getCurrent().getDesktop().getSession()
				.setAttribute("clicarUTilizador", usuario);
		}catch(java.lang.NullPointerException ess){
			
		}
	}
}
