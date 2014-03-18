package dao;

//import model.Direito;
import model.Perfil;
import model.PerfilUtilizador;
import model.Utilizador;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class PerfilDAO {

    public void gravarPerfilDAO(Perfil perfil) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            session.save(perfil);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
	public List<Perfil> listarPerfilDAO(String procurar) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            //List<Perfil> per = new ArrayList<Perfil>();
            //List<Direito> dire = new ArrayList<Direito>();

            List<Perfil> lista = session.createQuery(
                    "FROM model.Perfil where  nomePerfil Like concat('%','"
                    + procurar + "','%')").list();

            /*for (int i = 0; i < lista.size(); i++) {
                List<Direito> dir = session.createQuery(
                        "FROM model.Direito where  id_perfill='" + lista.get(i).getIdPerfil() + "'").list();
                for (int j = 0; j < dir.size(); j++) {
                    Perfil perfil = new Perfil();
                    perfil.setNomePerfil(lista.get(i).getNomePerfil());
                    perfil.setIdPerfil(lista.get(i).getIdPerfil());
                    Direito dr = new Direito();
                    dr.setDireitos(dir.get(j).getDireitos());
                    dire.add(dr);
                    perfil.setDireitoLista(dire);
                    per.add(perfil);
                }
            }*/

            session.getTransaction().commit();
            session.close();
            return lista;
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    @SuppressWarnings("unchecked")
	public List<PerfilUtilizador> listarPerfilUtilizadorDAO(String procurar) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            List<Utilizador> list = session.createQuery("FROM model.UtilizadorModel Where username Like concat('%','" + procurar + "','%')").list();
            List<PerfilUtilizador> lista = new ArrayList<PerfilUtilizador>();

            for (int i = 0; i < list.size(); i++) {
                List<PerfilUtilizador> listar = session.createQuery("FROM model.PerfilUtilizador Where utilizador_idUtilizador='" + list.get(i).getIdUtilizador() + "'").list();
                if (!listar.isEmpty()) {
                    PerfilUtilizador preuti = new PerfilUtilizador();
                    Perfil pre = new Perfil();
                    Utilizador uti = new Utilizador();
                    pre.setIdPerfil(listar.get(0).getPerfil().getIdPerfil());
                    pre.setNomePerfil(listar.get(0).getPerfil().getNomePerfil());
                    uti.setIdUtilizador(listar.get(0).getUtilizador().getIdUtilizador());
                    uti.setUsername(listar.get(0).getUtilizador().getUsername());
                    preuti.setPerfil(pre);
                    preuti.setUtilizador(uti);
                    lista.add(preuti);
                }
            }
            session.getTransaction().commit();
            session.close();
            return lista;
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }

    public void actualizarPerfilDAO(Perfil perfil) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            session.update(perfil);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void alocarPerfilUtilizador(PerfilUtilizador perUti) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();
            session.save(perUti);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void desabiltarUtilizadorDAO(PerfilUtilizador peruti) {
        try {
            Session session = Conexao.getSession();
            session.beginTransaction();

            session.delete(peruti);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
