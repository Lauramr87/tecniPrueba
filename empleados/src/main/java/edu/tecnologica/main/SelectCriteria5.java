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

public class SelectCriteria5 {

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
				CriteriaQuery<MiniEmpleado> cq = cb.createQuery(MiniEmpleado.class);
				//Root es el conjunto de datos original con las regiones
				Root<Employees> root = cq.from(Employees.class);
				
				Path<Integer> pathId = root.get("employeeId");
				Path<String> pathFn = root.get("firstName");
				
				//Lo extraemos todo porque queremos la entidad completa
				//cq.select(cb.array(pathId,pathFn));
				//Construct llama al constructor por parámetros. Los parámetros tiene 
				//que ir en el mismo orden
				cq.select(cb.construct(MiniEmpleado.class, pathId, pathFn));
				
				TypedQuery<MiniEmpleado> tq  = em.createQuery(cq);
				List<MiniEmpleado> lista_ob = tq.getResultList();
				
				for(MiniEmpleado str : lista_ob){
					System.out.println(str.getFirstName()+ " " + str.getEmployeeId());
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