package edu.tecnologica.main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaFactory {

	private static EntityManagerFactory factory;

	static {
		new JpaFactory();
	}

	private JpaFactory(){
		try{
			factory = Persistence.createEntityManagerFactory("unit");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static EntityManagerFactory getEntityManagerFactory(){
		return factory;
	}

}
