package com.sabel.meinrestservice;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.FormParam;


public class Service {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public Service() {
		emf = Persistence.createEntityManagerFactory("Haltestelle");
		em = emf.createEntityManager();
	}
	
	public void startTransaction() {
		em.getTransaction().begin();
	}
	
	public void stopTransaction() {
		em.getTransaction().commit();
	}
	
	public void close() {
		if (em != null) {
			em.close();
		}
		em = null;
		if (emf != null) {
			emf.close();
		}
		emf = null;
	}
	
	public List<Haltestelle> receiveAll(){
		Query query = em.createQuery("Select h from Haltestelle h");
		return query.getResultList();
	}
	
	public Haltestelle find(int id) {
		return em.find(Haltestelle.class, id);
	}
	
	public Haltestelle addStop(String name, double lon, double lat) {
		Haltestelle newHaltestelle = new Haltestelle();
		newHaltestelle.setName(name);
		newHaltestelle.setLon(lon);
		newHaltestelle.setLat(lat);
		startTransaction();
		em.persist(newHaltestelle);
		stopTransaction();
		return newHaltestelle;
	}
	
	public Haltestelle delStop(int id) {
		Haltestelle haltestelle = find(id);
		if(haltestelle!=null) {
			startTransaction();
			em.remove(haltestelle);
			stopTransaction();
		}
		return haltestelle;
	}
}
