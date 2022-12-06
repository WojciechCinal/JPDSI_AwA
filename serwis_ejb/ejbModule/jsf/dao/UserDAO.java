package jsf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.entities.User;

//DAO - Data Access Object for User entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "serwis-simplePU";

	@PersistenceContext//(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(User user) {
		em.persist(user);
	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object idUser) {
		return em.find(User.class, idUser);
	}

	public List<User> getFullList() {
		List<User> list = null;

		Query query = em.createQuery("select u from User u");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<User> getList(Map<String, Object> searchParams) {
		List<User> list = null;

		// 1. Build query string with parameters
		String select = "select u ";
		String from = "from User u ";
		String where = "";
		String orderby = "order by u.login";

		
		String login = (String) searchParams.get("login");
		if (login != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.login like :login ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (login != null) {
			query.setParameter("login", login+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of User objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	} 
	public User getUserFromDatabase(String login, String pass) {
		User u = null;

		Query query = em.createQuery("select u FROM User u where u.login=:login");
		query.setParameter("login", login);

		try {
			User user = (User) query.getSingleResult();

			if (login.equals(user.getLogin()) && pass.equals(user.getPassword())) {
				u = new User();
				u.setLogin(login);
				u.setPassword(pass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return u;
	}

	// simulate retrieving roles of a User from DB
	public List<String> getUserRolesFromDatabase(User user) {
	
		
		
		ArrayList<String> roles = new ArrayList<String>();

		if (user.getLogin().equals("user1")) {
			roles.add("user");
		}
		if (user.getLogin().equals("user2")) {
			roles.add("manager");
		}
		if (user.getLogin().equals("admin")) {
			roles.add("admin");
		}
		return roles;
	}
}
