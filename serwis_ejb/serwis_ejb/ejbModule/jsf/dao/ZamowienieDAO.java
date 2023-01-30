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
public class ZamowienieDAO {
	private final static String UNIT_NAME = "serwis-simplePU";

	@PersistenceContext // (unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Zamowienie zamowienie) {
		em.persist(zamowienie);
	}

	public Zamowienie merge(Zamowienie zamowienie) {
		return em.merge(zamowienie);
	}

	public void remove(Zamowienie zamowienie) {
		em.remove(em.merge(zamowienie));
	}

	public Zamowienie find(Object idZamowienia) {
		return em.find(Zamowienie.class, idZamowienia);
	}
	
		public List<Zamowienie> getList(Map<String, Object> searchParams) {
		List<Zamowienie> list = null;

		// 1. Build query string with parameters
		String select = "select z ";
		String from = "from Zamowienie z ";
		String where = "";
		String orderby = "order by z.idZamowienia";

		
		String idZamowienia = (String) searchParams.get("idZamowienia");
		if (idZamowienia != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "z.idZamowienia like :idZamowienia ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (idZamowienia != null) {
			query.setParameter("nazwaNaprawy", idZamowienia+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Zamowienie objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

			public List<Zamowienie> getFullList() {
			List<Zamowienie> list = null;
			
			Query query = em.createQuery("SELECT z, z.user.login FROM Zamowienie z");
			
			//Query query = em.createQuery("SELECT z, z.user.login, z.pomZamowienias.cena FROM Zamowienie z"); //JOIN z.pomZamowienia p WHERE p.id_naprawy = :id_naprawy
			
			//Query query = em.createQuery("SELECT z FROM Zamowienie z JOIN z.pomZamowienias p JOIN p.naprawa n WHERE z.idZamowienia = :idZamowienia," , Zamowienie.class);
			try {
				list = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return list;

		}
		

}
