package dao;

import model.LinhaOcupacao;
import model.Ocupacao;
import java.util.Date;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class OcupacaoDAO {

    public void gravarOcupacaoDAO(Ocupacao ocupacao) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            session.save(ocupacao);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
	public List<String> buscarHoraDAO() {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            List<String> lista = session.createSQLQuery(
                    "select Time(now())").list();
            session.getTransaction().commit();
            session.close();
            return lista;
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    @SuppressWarnings("unchecked")
	public List<Date> buscarDataDAO() {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            List<Date> lista = session.createSQLQuery(
                    "select Date(now())").list();
            session.getTransaction().commit();
            session.close();
            return lista;
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    @SuppressWarnings("unchecked")
	public List<LinhaOcupacao> listarQuartoOcupadoDAO(String procurar, String data) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            List<LinhaOcupacao> lista = session.createQuery("from model.LinhaOcupacao as linha where (linha.estado='ocupado' or linha.estado='reservado') and linha.dataEntrada<='" + data + "' and linha.dataSaida>='" + data + "' and linha.quarto.idQuarto in (Select new list(quar.idQuarto) from model.Quarto as quar where quar.numeroQuarto Like concat('%','" + procurar + "','%'))").list();

            session.getTransaction().commit();
            session.close();
            return lista;
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public void actualizarLinhaOcupacaoDAO(LinhaOcupacao linha) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            session.update(linha);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public int diferencaDeNoitesOcupacao(String dataSaida, String dataEntrada) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("Select to_days(:dat) - to_days(:data)");
            query.setParameter("dat", dataSaida);
            query.setParameter("data", dataEntrada);
            Object obj = query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return Integer.valueOf(obj.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
