package jsf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.entities.*;

@Stateless
public class PomZamowieniaDAO {

	private final static String UNIT_NAME = "serwis-simplePU";

	@PersistenceContext // (unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(PomZamowienia pomZamowienia) {
		em.persist(pomZamowienia);
	}

	public PomZamowienia merge(PomZamowienia pomZamowienia) {
		return em.merge(pomZamowienia);
	}

	public void remove(PomZamowienia pomZamowienia) {
		em.remove(em.merge(pomZamowienia));
	}

	public PomZamowienia find(Object idZamowienia) {
		return em.find(PomZamowienia.class, idZamowienia);
	}
}
