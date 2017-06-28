package edu.tecnologica.main;

//Ejemplo como convertir de JPA a HIBERNATE EntityManager -> Session
//Al contrario no puede ser ya que JPA envuelve a HIBERNATE, de ahí que siempre se aplique la API JPA


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import edu.tecnologica.entity.Employees;

//import org.hibernate.Session;
//import org.hibernate.Transaction;

public class MainJpa2 {

	public static void main(String[] args){
		EntityManagerFactory emf = null;
		try{
			emf = JpaFactory.getEntityManagerFactory();
			EntityManager em = emf.createEntityManager();
			
			//Transformo a hibernate
			SessionFactory sf = emf.unwrap(SessionFactory.class);
			Session sesion = sf.openSession();
			
			//EntityTransaction tx = null;
			org.hibernate.Transaction txh = sesion.beginTransaction();
			
			try{ //"Transaccion"
				//tx=em.getTransaction();
				//tx.begin();
				//Regions rg = sesion.get(Regions.class, new BigDecimal(1));
				Employees e = sesion.get(Employees.class, 100);
				System.out.println("Empleado nombre="+e.getFirstName());
				System.out.println("Empleado salario="+e.getSalary());
				txh.commit();
			}
			catch(Exception e){
				txh.rollback();
				e.printStackTrace();
			}finally{
				em.close();
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
