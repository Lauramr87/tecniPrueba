package edu.tecnologica.main;

import java.math.BigDecimal;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import edu.tecnologica.entity.Regions;

/*
 * Se usa API JPA pero por debajo invocamos a Hibernate
 * */

public class MainLoadListener {

	public static void main(String[] args){
		EntityManagerFactory emf = null;
		try{
			emf = JpaFactory.getEntityManagerFactory();
			Transaction tx = null;
			Session sesion = null;
			try{ //"Transaccion"
					
				//Como activar el Listener de Eventos MyLoadListener
				SessionFactoryImpl sfi = emf.unwrap(SessionFactoryImpl.class);
				EventListenerRegistry registry = sfi.getServiceRegistry().getService(EventListenerRegistry.class);
				registry.getEventListenerGroup(EventType.LOAD).appendListener(new MyLoadListener());
				
				sesion = sfi.openSession();
				tx = sesion.beginTransaction();
				sesion.get(Regions.class, new BigDecimal(1));
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
			emf.close();
		}
	}
}
