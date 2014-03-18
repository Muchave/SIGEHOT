package dao;

import model.Quarto;
import org.hibernate.Session;
import java.util.List;

public class QuartoDAO {

    public void gravarQuartoDAO(Quarto quarto) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            session.save(quarto);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    
	@SuppressWarnings("unchecked")
	public List<Quarto> listarQuartoDAO(String procurar) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            List<Quarto> lista = session.createQuery(
                    "FROM model.Quarto where  numeroQuarto Like concat('%','"
                    + procurar + "','%') or tipoQuarto Like concat('%','"
                    + procurar + "','%')").list();
            session.getTransaction().commit();
            session.close();
            return lista;
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    @SuppressWarnings("unchecked")
	public List<Quarto> listarQuartoLivreDAO(String procurar, String dataE,String dataS) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            List<Quarto> lista = session.createQuery("FROM model.Quarto as qua WHERE qua.numeroQuarto like concat('%','"  + procurar + "','%') and qua.idQuarto not in (SELECT new list(linha.quarto.idQuarto) from model.LinhaOcupacao as linha where (estado='ocupado'or estado='reservado') and ((linha.dataEntrada>='" + dataE + "' and linha.dataEntrada<='" + dataS + "') or (linha.dataSaida>='" + dataE + "' and linha.dataSaida<='" + dataS + "')))").list();

            session.getTransaction().commit();
            session.close();
            return lista;
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public void actualizarQuartoDAO(Quarto quarto) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            session.update(quarto);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
