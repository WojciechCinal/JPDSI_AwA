package jsf.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jsf.entities.*;

@Stateless
public class PomZamowieniaPKDAO {

	private final static String UNIT_NAME = "serwis-simplePU";

	@PersistenceContext // (unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(PomZamowieniaPK pomZamowieniaPK) {
		em.persist(pomZamowieniaPK);
	}

	public PomZamowieniaPK merge(PomZamowieniaPK pomZamowieniaPK) {
		return em.merge(pomZamowieniaPK);
	}

	public void remove(PomZamowieniaPK pomZamowieniaPK) {
		em.remove(em.merge(pomZamowieniaPK));
	}

	public PomZamowieniaPK find(Object idZamowienia) {
		return em.find(PomZamowieniaPK.class, idZamowienia);
	}
}
