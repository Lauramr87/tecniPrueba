package edu.tecnologica.main;


import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.tecnologica.entity.Regions;

public class MainHibernateCicloVida {

	public static void main(String[] args){
		SessionFactory sf = null;
		try{
			sf = HibernateFactory.getSessionFactory();
			Session sesion = sf.openSession();
			Transaction tx = null;
			
			try{ //"Transaccion"
				
				//Estado Transient, no tiene acceso a la base de datos
				Regions rg = new Regions();
				rg.setRegionId(new BigDecimal(33));
				rg.setRegionName("Chiquitistan");
				
				tx=sesion.beginTransaction();
				
				sesion.save(rg); //GUARDAR EN BASE DE DATOS Pasa de estado Transient -> Persistent 
				
				rg.setRegionName("Azkoitia");
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
