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

public class SelectCriteria2 {

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
				CriteriaQuery<Regions> cq = cb.createQuery(Regions.class);
				//Root es el conjunto de datos original con las regiones
				Root<Regions> root = cq.from(Regions.class);
				//Lo extraemos todo porque queremos la entidad completa
				cq.select(root);
				
				TypedQuery<Regions> tq  = em.createQuery(cq);
				List<Regions> lista_nr = tq.getResultList();
				
				for(Regions str : lista_nr){
					System.out.println(str.getRegionName() + " " + str.getRegionId());
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
