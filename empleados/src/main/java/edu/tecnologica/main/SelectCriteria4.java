package edu.tecnologica.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import edu.tecnologica.entity.Employees;

public class SelectCriteria4 {

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
				//EMPLOYEE_ID, FIRSTNAME
				CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
				//Root es el conjunto de datos original con las regiones
				Root<Employees> root = cq.from(Employees.class);
				
				Path<Integer> pathId = root.get("employeeId");
				Path<String> pathFn = root.get("firstName");
				
				//Lo extraemos todo porque queremos la entidad completa
				cq.select(cb.array(pathId,pathFn));
				
				TypedQuery<Object[]> tq  = em.createQuery(cq);
				List<Object[]> lista_ob = tq.getResultList();
				
				for(Object[] str : lista_ob){
					System.out.println(str[0]+ " " + str[1]);
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