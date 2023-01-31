package jsf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.entities.Naprawa;

@Stateless
public class NaprawaDAO {
	private final static String UNIT_NAME = "serwis-simplePU";

	@PersistenceContext // (unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Naprawa naprawa) {
		em.persist(naprawa);
	}

	public Naprawa merge(Naprawa naprawa) {
		return em.merge(naprawa);
	}

	public void remove(Naprawa naprawa) {
		em.remove(em.merge(naprawa));
	}

	public Naprawa find(Object idNaprawy) {
		return em.find(Naprawa.class, idNaprawy);
	}
	
	public List<Naprawa> getFullList() {
		List<Naprawa> list = null;

		Query query = em.createQuery("SELECT n FROM Naprawa n");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
		public List<Naprawa> getList(Map<String, Object> searchParams) {
		List<Naprawa> list = null;

		// 1. Build query string with parameters
		String select = "select n ";
		String from = "from Naprawa n ";
		String where = "";
		String orderby = "order by n.idNaprawy ";

		
		String nazwaNaprawy = (String) searchParams.get("idNaprawy");
		if (nazwaNaprawy != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "n.idNaprawy like :idNaprawy ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (nazwaNaprawy != null) {
			query.setParameter("nazwaNaprawy", nazwaNaprawy+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Naprawa objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
