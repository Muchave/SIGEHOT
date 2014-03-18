package controller;

import java.util.List;

import model.Cliente;

import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import dao.ClienteDAO;

@SuppressWarnings({ "serial", "rawtypes" })
public class ClienteController extends GenericForwardComposer {

	private Textbox idApelido, idNome, idNumeroDoc, idNacionalidade, idEmail,
			idProcurar, idEndereco, idTelefone;
	private Combobox idDocumento;
	private Datebox idDataNasc;
	private Listbox listaCliente;

	public void onSalvar(ForwardEvent e) {
		Cliente cli = new Cliente();
		cli.setApelido(idApelido.getText());
		cli.setNome(idNome.getText());
		cli.setNacionalidade(idNacionalidade.getText());
		cli.setEmail(idEmail.getText());
		cli.setMorada(idEndereco.getText());
		cli.setNumeroDocumento(idNumeroDoc.getText());
		cli.setTelefone(Integer.valueOf(idTelefone.getText()));
		cli.setTipoDocumento(idDocumento.getValue());
		cli.setDataNascimento(idDataNasc.getValue());

		ClienteDAO clidao = new ClienteDAO();
		clidao.gravarClienteDAO(cli);
		Limpar();
		Messagebox.show("cliente gravado com sucesso!");
	}

	public void onActualizar(ForwardEvent e) {
		try {
			if (listaCliente.getSelectedItem().isSelected()) {
				Cliente listaSelecionada = listaCliente.getSelectedItem()
						.getValue();
				Cliente cli = new Cliente();
				cli.setApelido(idApelido.getText());
				cli.setNome(idNome.getText());
				cli.setNacionalidade(idNacionalidade.getText());
				cli.setEmail(idEmail.getText());
				cli.setMorada(idEndereco.getText());
				cli.setNumeroDocumento(idNumeroDoc.getText());
				cli.setTelefone(Integer.valueOf(idTelefone.getText()));
				cli.setTipoDocumento(idDocumento.getValue());
				cli.setDataNascimento(idDataNasc.getValue());
				cli.setIdCliente(listaSelecionada.getIdCliente());

				ClienteDAO clidao = new ClienteDAO();
				clidao.actualizarClienteDAO(cli);
				List<Cliente> lista = clidao.listarClienteDAO(idProcurar
						.getText());
				listaCliente.setModel(new ListModelList<Cliente>(lista));
				Limpar();
				Messagebox.show("Actualizado com Sucesso!");
			}
		} catch (java.lang.NullPointerException error) {
			Messagebox
					.show("Seleciona um cliente na Lista a baixo para poder actualizar!");
		} catch (Exception err) {
			Messagebox.show("Ocorreu um erro ao actualizar!");
		}
	}

	public void Limpar() {
		idApelido.setText(null);
		idNome.setText(null);
		idNumeroDoc.setText(null);
		idNacionalidade.setText(null);
		idEmail.setText(null);
		idEndereco.setText(null);
		idTelefone.setText(null);
		idDocumento.setSelectedIndex(-1);
		idDataNasc.setValue(null);
	}

	public void onNovo(ForwardEvent e) {
		Limpar();
	}

	public void onSelect(ForwardEvent e) {
		try {
			Cliente listaSelecionada = listaCliente.getSelectedItem()
					.getValue();
			idNome.setText(listaSelecionada.getNome());
			idApelido.setText(listaSelecionada.getApelido());
			idDataNasc.setValue(listaSelecionada.getDataNascimento());
			idEmail.setText(listaSelecionada.getEmail());
			idTelefone.setText(String.valueOf(listaSelecionada.getTelefone()));
			idNacionalidade.setText(listaSelecionada.getNacionalidade());
			idEndereco.setText(listaSelecionada.getMorada());
			idNumeroDoc.setText(listaSelecionada.getNumeroDocumento());
			idDocumento.setValue(listaSelecionada.getTipoDocumento());
		} catch (java.lang.NullPointerException re) {

		}
	}

	public void onProcurar(ForwardEvent e) {
		ClienteDAO func = new ClienteDAO();
		List<Cliente> lista = func.listarClienteDAO(idProcurar.getText());
		listaCliente.setModel(new ListModelList<Cliente>(lista));
	}
}
