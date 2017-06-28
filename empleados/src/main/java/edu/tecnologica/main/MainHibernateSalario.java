package edu.tecnologica.main;

import java.math.BigDecimal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.tecnologica.entity.Employees;

public class MainHibernateSalario{

	public static void main(String[] args){
		SessionFactory sf = null;
		try{
			sf = HibernateFactory.getSessionFactory();
			Session sesion = sf.openSession();
			Transaction tx = null;
			try{
				//"Transaccion"
				tx = sesion.beginTransaction();
				for(int i=100 ; i<=206 ; i++){
					Employees e = sesion.get(Employees.class, i);
					BigDecimal salario_actual = e.getSalary();
					BigDecimal salario_nuevo = salario_actual.multiply(new BigDecimal(1.07));
					System.out.println("Empleado [ Id:"+e.getEmployeeId()+" Nombre:"+e.getFirstName() + " ]");
					System.out.println("Salario actual: "+e.getSalary());
					System.out.println("Salario nuevo: "+salario_nuevo);	
					e.setSalary(salario_nuevo);
					//sesion.save(e);
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
