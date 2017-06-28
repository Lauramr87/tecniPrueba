package edu.tecnologica.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.tecnologica.entity.Registro;
import edu.tecnologica.entity.Registrosec;

public class SecuenciaStrategy {

	public static void main(String[] args){
		SessionFactory sf = null;
		try{
			sf = HibernateFactory.getSessionFactory();
			Session sesion = sf.openSession();
			Transaction tx = null;
			try{
				//"Transaccion"
				tx = sesion.beginTransaction();
				Registrosec sec = new Registrosec();
				Registrosec sec1 = new Registrosec();
				Registrosec sec2 = new Registrosec();
				sesion.save(sec);
				sesion.save(sec1);
				sesion.save(sec2);
				tx.commit();
			}
			catch(Exception e){
				tx.rollback();
				e.printStackTrace();
			}finally{
				sesion.close();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}finally{
			sf.close();
		}
	}
}
