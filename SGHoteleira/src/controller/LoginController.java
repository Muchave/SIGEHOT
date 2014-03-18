package controller;

import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import dao.LoginDAO;
import dao.UtilizadorDAO;
import model.PerfilUtilizador;
import model.Utilizador;

@SuppressWarnings({ "serial", "rawtypes" })
public class LoginController extends GenericForwardComposer {

	private Textbox idusuario;
	private Textbox password;

	public void onClickar(ForwardEvent e) {
		try {
			UtilizadorDAO usuarioDao = new UtilizadorDAO();
			List<Utilizador> utilizador = usuarioDao.listarUtilizadoreDAO(
					idusuario.getText(), password.getText());

			if (!utilizador.isEmpty()) {
				if (utilizador.get(0).getUsername().equals(idusuario.getText())
						&& utilizador.get(0).getPassword()
								.equals(password.getText())) {

					Executions.getCurrent().getDesktop()
					.getSession()
					.setAttribute("actual", utilizador);
					Executions.getCurrent().getDesktop()
					.getSession()
					.setAttribute("user", utilizador.get(0).getUsername());
					
					LoginDAO logdao = new LoginDAO();

					List<PerfilUtilizador> lista = logdao
							.buscarDireitoUtilizadorDAO(String
									.valueOf(utilizador.get(0)
											.getIdUtilizador()));
					if (!lista.isEmpty()) {
						for (int j = 0; j < lista.get(0).getPerfil()
								.getDireitoLista().size(); j++) {
							if (lista.get(0).getPerfil().getDireitoLista()
									.get(j).getDireitos()
									.equals("Administracao")) {
								Executions.getCurrent().getDesktop()
										.getSession()
										.setAttribute("admin", "administracao");
							} else if (lista.get(0).getPerfil()
									.getDireitoLista().get(j).getDireitos()
									.equals("Ocupacao")) {
								Executions.getCurrent().getDesktop()
										.getSession()
										.setAttribute("ocupacao", "ocupacao");
							} else if (lista.get(0).getPerfil()
									.getDireitoLista().get(j).getDireitos()
									.equals("Cliente")) {
								Executions.getCurrent().getDesktop()
										.getSession()
										.setAttribute("cliente", "cliente");
							}
						}
						Executions.getCurrent().sendRedirect("Menu.zul");
					} else {
						Messagebox
								.show("Contacte o Administrador! ('Utilizador sem Direitos!')");
					}

				} else {
					Messagebox.show("Dados inválidos, tente de novo!");
				}
			} else {
				Messagebox.show("Dados inválidos, tente de novo!");
			}
		} catch (java.lang.IndexOutOfBoundsException exc) {
			Messagebox
					.show("Contacte o Administrador! ('Utilizador sem Direitos!')"
							+ exc);
		}
	}
}
