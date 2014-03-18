package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.zkoss.zul.Messagebox;

@SuppressWarnings("deprecation")
public class Conexao {

    private static SessionFactory novaSessao;

    static {
        try {
            novaSessao = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Exception e) {
            Messagebox.show("Erro na Sessao!" + e);
            novaSessao = null;
        }
    }

    public static Session getSession() {
        return novaSessao.openSession();
    }
}
