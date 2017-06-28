package edu.tecnologica.main;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;

public class MyLoadListener implements LoadEventListener {

	public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
		
		System.out.println("LOAD --> "+ event.getEntityClassName() + " Cargado");
		
	}

}
