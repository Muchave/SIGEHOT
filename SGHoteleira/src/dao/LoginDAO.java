package dao;

import model.PerfilUtilizador;
import model.Utilizador;
import java.util.List;
import org.hibernate.Session;

public class LoginDAO {

    @SuppressWarnings("unchecked")
	public List<Utilizador> listarUtilizadorDAO(Utilizador util) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            List<Utilizador> lista = session.createQuery(
                    "FROM model.Utilizador where username='" + util.getUsername() + "' and password='" + util.getPassword() + "'").list();
            session.getTransaction().commit();
            session.close();
            return lista;
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    @SuppressWarnings("unchecked")
	public List<Utilizador> popularUtilizadorDAO() {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            List<Utilizador> lista = session.createQuery(
                    "FROM model.Utilizador").list();
            session.getTransaction().commit();
            session.close();
            return lista;
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public List<PerfilUtilizador> buscarDireitoUtilizadorDAO(String util) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            @SuppressWarnings("unchecked")
			List<PerfilUtilizador> lista = session.createQuery("FROM model.PerfilUtilizador Where utilizador_idUtilizador='" + util + "'").list();
            for (int i = 0; i < lista.size(); i++) {
                lista.get(i).getPerfil().getDireitoLista().get(i).getDireitos();
            }

            session.getTransaction().commit();
            session.close();
            return lista;
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }
}
