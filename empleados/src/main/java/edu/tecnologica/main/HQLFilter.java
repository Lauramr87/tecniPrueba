package edu.tecnologica.main;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.tecnologica.entity.Employees;

public class HQLFilter {

	public static void main(String[] args){
		SessionFactory sf = null;
		try{
			sf = HibernateFactory.getSessionFactory();
			Session sesion = sf.openSession();
			Transaction tx = null;
			
			try{ //"Transaccion"
				tx=sesion.beginTransaction();
				
				//Activamos el filtro definido en la entidad EMPLOYEES
				Filter f = sesion.enableFilter("employeeFilter");
				f.setParameter("salary", 4000);
				
				Query query = sesion.createQuery("from Employees e");
				List<Employees> listae = query.list();
				
				for(Employees e:listae){
					System.out.println(e.getFirstName()+" "+e.getSalary());
				}
				
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
