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

public class MainJpa {

	public static void main(String[] args){
		EntityManagerFactory emf = null;
		try{
			emf = JpaFactory.getEntityManagerFactory();
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = null;
			
			try{ //"Transaccion"
				tx=em.getTransaction();
				tx.begin();
				//Regions rg = sesion.get(Regions.class, new BigDecimal(1));
				Regions rg = em.find(Regions.class, new BigDecimal(1));
				System.out.println("Region nombre="+rg.getRegionName());
				System.out.println("Region id="+rg.getRegionId());
				
				System.out.println("Mostrar países");
				Set<Countries> cjto_paises = rg.getCountrieses();
				Iterator<Countries> it = cjto_paises.iterator();
				Countries caux=null;
				
				while(it.hasNext()){
					caux = it.next();
					System.out.println(caux.toString());
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
