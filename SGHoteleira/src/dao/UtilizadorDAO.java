package dao;

import model.Utilizador;
import java.util.List;
import org.hibernate.Session;

import dao.Conexao;

public class UtilizadorDAO {

    public void gravarUtilizadorDAO(Utilizador utilizador) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            session.save(utilizador);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
	public List<Utilizador> listarUtilizadorDAO(String procurar) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            List<Utilizador> lista = session.createQuery(
                    "FROM model.Utilizador where  username Like concat('%','"
                    + procurar + "','%')").list();
            session.getTransaction().commit();
            session.close();
            return lista;
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

	@SuppressWarnings("unchecked")
	public List<Utilizador> listarUtilizadoreDAO(String nome, String password) {
		Session session = Conexao.getSession();
		session.beginTransaction();

		List<Utilizador> usuario = session.createQuery(
				"FROM model.Utilizador where username='" + nome
						+ "' and password='" + password + "'").list();

		session.getTransaction().commit();
		session.close();
		return usuario;

	}
    
    public void actualizarUtilizadorDAO(Utilizador utilizador) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            session.update(utilizador);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
