package edu.tecnologica.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import edu.tecnologica.entity.Regions;

public class SelectCriteria {

	public static void main(String[] args){
		EntityManagerFactory emf = null;
		try{
			emf = JpaFactory.getEntityManagerFactory();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = null;
			
			try{ //"Transaccion"
				tx=em.getTransaction();
				tx.begin();
				
				CriteriaBuilder cb = em.getCriteriaBuilder();
				//El tipo de datos que vamos a seleccionar
				CriteriaQuery<String> cq = cb.createQuery(String.class);
				//Root es el conjunto de datos original con las regiones
				Root<Regions> root = cq.from(Regions.class);
				//Seleccionamos la columna que queremos y ponemos el tipo con as
				cq.select(root.get("regionName").as(String.class));
				TypedQuery<String> tq  = em.createQuery(cq);
				List<String> lista_nr = tq.getResultList();
				
				for(String str : lista_nr){
					System.out.println(str.toString());
				}
				
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
