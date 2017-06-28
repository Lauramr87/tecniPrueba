package edu.tecnologica.main;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.tecnologica.entity.Employees;


public class SQLNativo {

	public static void main(String[] args){
		SessionFactory sf = null;
		try{
			sf = HibernateFactory.getSessionFactory();
			Session sesion = sf.openSession();
			Transaction tx = null;
			
			try{ //"Transaccion"
				tx=sesion.beginTransaction();
				
			//	SQLQuery sql = sesion.createSQLQuery("Select EMPLOYEE_ID,EMAIL from EMPLOYEES ");
			//List<Object[]> lempleados = sql.list();
			SQLQuery sql = sesion.createSQLQuery("Select * from EMPLOYEES ");
			
			//Es como hacer un casting
			sql.addEntity(Employees.class);
			List<Employees> empleados = sql.list();
			
			/*	for(Object[] obj: lempleados){
					System.out.println(obj[0]+" "+obj[1]);
			}*/
			
			for(Employees e: empleados){
				System.out.println(e.getEmail()+" "+e.getEmployeeId());
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
