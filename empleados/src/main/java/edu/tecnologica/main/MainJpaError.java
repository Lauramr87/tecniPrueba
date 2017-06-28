package edu.tecnologica.main;


import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import edu.tecnologica.entity.Countries;

//import org.hibernate.Session;
//import org.hibernate.Transaction;

import edu.tecnologica.entity.Regions;

public class MainJpaError {

	public static void main(String[] args){
		EntityManagerFactory emf = null;
		emf = JpaFactory.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		Regions rg = em.find(Regions.class, new BigDecimal(1));
		try{
			try{ //"Transaccion"
				tx=em.getTransaction();
				tx.begin();
				//Regions rg = sesion.get(Regions.class, new BigDecimal(1));
				System.out.println("Region nombre="+rg.getRegionName());
				System.out.println("Region id="+rg.getRegionId());	
				System.out.println("Mostrar países");		
				tx.commit();
			}
			catch(Exception e){
				tx.rollback();
				e.printStackTrace();
			}finally{
				em.close();
			}
			
			/*
			 * Esto provoca error porque intentamos hacer un get cuando ya hemos desconectado la sesión
			 * En este caso el objeto pasa de estado Transient a estado Detached
			 */
		}
		catch(Exception ex){
			ex.printStackTrace();
		}finally{
			emf.close();
		}
		
		Set<Countries> cjto_paises = rg.getCountrieses();
		Iterator<Countries> it = cjto_paises.iterator();
		Countries caux=null;
		
		while(it.hasNext()){
			caux = it.next();
			System.out.println(caux.toString());
		}
	}
}
