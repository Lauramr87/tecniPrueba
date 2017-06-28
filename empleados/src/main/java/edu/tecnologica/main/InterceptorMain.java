package edu.tecnologica.main;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.tecnologica.entity.Employees;
import edu.tecnologica.entity.Registro;

public class InterceptorMain {
	public static void main(String[] args){
		SessionFactory sf = null;
		try{
			sf = HibernateFactory.getSessionFactory();
			
			//Activa el listener MyInterceptor
			Session sesion = sf.withOptions().interceptor(new MyInterceptor()).openSession();
			Transaction tx = null;
			
			try{ //"Transaccion"
				tx=sesion.beginTransaction();
				
				Registro reg = new Registro();
				sesion.save(reg);
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
