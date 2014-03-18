package dao;

import model.Cliente;
import java.util.List;
import org.hibernate.Session;

public class ClienteDAO {

    public void gravarClienteDAO(Cliente cliente) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            session.save(cliente);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
	public List<Cliente> listarClienteDAO(String procurar) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            List<Cliente> lista = session.createQuery(
                    "FROM model.Cliente where  nome Like concat('%','"
                    + procurar + "','%') or apelido Like concat('%','"
                    + procurar + "','%')").list();
            session.getTransaction().commit();
            session.close();
            return lista;
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public void actualizarClienteDAO(Cliente cliente) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            session.update(cliente);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
