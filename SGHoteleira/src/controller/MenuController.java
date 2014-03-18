package controller;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import org.zkoss.zul.Include;

@SuppressWarnings({ "serial", "rawtypes" })
public class MenuController extends GenericForwardComposer {
	private Include layout;

	public void onClick(ForwardEvent e) {
		layout.setSrc("cliente.zul");
	}

	public void onQuartoOcupados(ForwardEvent e) {
		layout.setSrc("quartoOcupacao.zul");
	}

	public void onSair(ForwardEvent e) {
		Executions.getCurrent().sendRedirect("Login.zul");
		Executions.getCurrent().getDesktop().getSession().removeAttribute("actual");
		Executions.getCurrent().getDesktop().getSession().removeAttribute("user");
	}

	public void onOcuparQuartos(ForwardEvent e) {
		layout.setSrc("oucuparqurto.zul");
	}

	public void onRegistarQuartos(ForwardEvent e) {
		layout.setSrc("quartos.zul");
	}

	public void onCriarUtilizador(ForwardEvent e) {
		layout.setSrc("CriarUsuario.zul");
	}

	public void onCriarPerfil(ForwardEvent e) {
		layout.setSrc("CriarPerfil.zul");
	}

	public void onConfiguracao(ForwardEvent e) {
		layout.setSrc("alterarsenha.zul");
	}

	public void onServico(ForwardEvent e) {
		layout.setSrc("Servico.zul");
	}

	public void onBuscasRapidas(ForwardEvent e) {
		layout.setSrc("BuscasRapidas.zul");
	}
}
