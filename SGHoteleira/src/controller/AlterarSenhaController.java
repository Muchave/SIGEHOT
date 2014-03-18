package controller;

import java.util.List;

import model.Utilizador;

import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zk.ui.Executions;

import dao.UtilizadorDAO;

@SuppressWarnings({ "serial", "rawtypes" })
public class AlterarSenhaController extends GenericForwardComposer {
	private Textbox idPassword, idConfirmar;

	@SuppressWarnings("unchecked")
	public void onAlterar(ForwardEvent e) {
		String senha = "";
		try {
			if (!idPassword.getText().equals(senha)) {
				if (idConfirmar.getText().equals(idPassword.getText())) {
					List<Utilizador> util = (List<Utilizador>) Executions.getCurrent()
							.getDesktop().getSession().getAttribute("actual");
					Utilizador uti = new Utilizador();
					uti.setUsername(util.get(0).getUsername());
					uti.setEmail(util.get(0).getEmail());
					uti.setPassword(idPassword.getText());
					uti.setConfirmar(idConfirmar.getText());
					uti.setIdUtilizador(util.get(0).getIdUtilizador());

					UtilizadorDAO utildao = new UtilizadorDAO();
					utildao.actualizarUtilizadorDAO(uti);
					Messagebox.show("Password Alterado com Sucesso!");
				} else {
					idPassword.setText(null);
					idConfirmar.setText(null);
					Messagebox
							.show("PassWord e Confirmar PassWord não são iguais!");
				}
			} else {
				Messagebox.show("Introduza Password!");
			}
		} catch (Exception ex) {
			Messagebox.show("Ocorreu um erro ao Actualizar!");
		}
	}
}
