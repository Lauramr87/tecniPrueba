package edu.tecnologica.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.tecnologica.entity.Registro;

public class UUIDStrategy {

	public static void main(String[] args){
		SessionFactory sf = null;
		try{
			sf = HibernateFactory.getSessionFactory();
			Session sesion = sf.openSession();
			Transaction tx = null;
			try{
				//"Transaccion"
				tx = sesion.beginTransaction();
				Registro reg1 = new Registro();
				Registro reg2 = new Registro();
				Registro reg3 = new Registro();
				sesion.save(reg1);
				sesion.save(reg2);
				sesion.save(reg3);
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
