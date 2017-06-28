package edu.tecnologica.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import edu.tecnologica.entity.Departments;

public class EjemploNamedQuery {

	public static void main(String[] args){
		EntityManagerFactory emf = null;
		try{
			emf = JpaFactory.getEntityManagerFactory();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = null;
			
			try{ //"Transaccion"
				tx=em.getTransaction();
				tx.begin();
				//Alias para la query definida en la Entidad Departments
				TypedQuery<Departments> query  = em.createNamedQuery("Departments.pornombre", Departments.class);
				query.setParameter("name", "Operations");
				
				/* Cada query que ejecute se realiza de forma inmediata
				 * query.setFlushMode(FlushModeType.AUTO);
				 * Se ejecuta a nivel de transaccion
				query.setFlushMode(FlushModeType.COMMIT);*/
				
				Departments d = query.getSingleResult();
				System.out.println(d.getDepartmentName() + " " + d.getDepartmentId());
				tx.commit();
			}
			catch(Exception e){
				tx.rollback();
				e.printStackTrace();
			}finally{
				em.close();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}finally{
			emf.close();
		}
	}
	
}