package controller;

import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import dao.UtilizadorDAO;

import model.Utilizador;

@SuppressWarnings({ "serial", "rawtypes" })
public class CriarUsuarioController extends GenericForwardComposer {
	private Textbox idNomeUsuario;
	private Textbox idEmail;
	private Textbox passwordUsuario;
	private Textbox confirmarUsuario;

	public void onClick(ForwardEvent e) {
		try {
			Utilizador usuario = new Utilizador();
			usuario.setUsername(idNomeUsuario.getText());
			usuario.setEmail(idEmail.getText());
			usuario.setPassword(passwordUsuario.getText());
			usuario.setConfirmar(confirmarUsuario.getText());
			
			UtilizadorDAO usuarioDao=new UtilizadorDAO();
			usuarioDao.gravarUtilizadorDAO(usuario);
			Messagebox.show("Usuario Criado com Sucesso!");
		} catch (Exception erro) {
			Messagebox.show("Ocorreu um erro ao Criar Usuario!");			
		}
	}
}
