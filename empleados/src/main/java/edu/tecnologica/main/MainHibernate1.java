package edu.tecnologica.main;


import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.tecnologica.entity.Regions;

public class MainHibernate1 {

	public static void main(String[] args){
		SessionFactory sf = null;
		try{
			sf = HibernateFactory.getSessionFactory();
			Session sesion = sf.openSession();
			Transaction tx = null;
			
			try{ //"Transaccion"
				tx=sesion.beginTransaction();
				Regions rg = sesion.get(Regions.class, new BigDecimal(1));
				System.out.println("Region nombre="+rg.getRegionName());
				System.out.println("Region id="+rg.getRegionId());
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
