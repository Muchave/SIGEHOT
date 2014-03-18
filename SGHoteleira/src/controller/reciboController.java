package controller;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Iframe;

import dao.Conexao;

import model.Utilizador;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@SuppressWarnings({ "serial", "rawtypes" })
public class reciboController extends GenericForwardComposer{
	
	private Iframe layoutt;
	private Session session;
	InputStream is = null;
	byte[] buf ;
	 
	public reciboController() {
		session=Conexao.getSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	    
        try {
    		List<Utilizador> util = (List<Utilizador>) Executions.getCurrent()
    				.getDesktop().getSession().getAttribute("actual");
        	final Execution exec = Executions.getCurrent();
        	final Map<String, Object> parametros = new HashMap<String, Object>();
        	is=exec.getDesktop().getWebApp().getResourceAsStream(exec.toAbsoluteURI("/WEB-INF/relatorio/clienteReport.jasper", false));
    		parametros.put("cliente", "2");
    		parametros.put("dataEntrada", "2013-09-15");
    		parametros.put("dataSaida", "2013-09-28");
    		parametros.put("user", String.valueOf(util.get(0).getIdUtilizador())); 

            Transaction tx = session.beginTransaction();
            session.doWork(new Work(){

				@Override
				public void execute(Connection arg0) {
					try {
						buf =  JasperRunManager.runReportToPdf(is, parametros,arg0);
					} catch (JRException e) {
						e.printStackTrace();
					}
				}
            });
            
            tx.commit();
            final InputStream mediais = new ByteArrayInputStream(buf);
            final AMedia amedia = 
            		new AMedia("Recibo de Cliente_"+(int)(Math.random()*1000)+".pdf", "pdf", "application/pdf", mediais);
            layoutt.setContent(amedia);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
	
}