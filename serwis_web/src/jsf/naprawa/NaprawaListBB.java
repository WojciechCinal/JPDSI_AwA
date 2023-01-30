package jsf.naprawa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

import jsf.dao.NaprawaDAO;
import jsf.entities.Naprawa;

@Named
@RequestScoped
public class NaprawaListBB {
	 //private static final String PAGE_USER_EDIT = "userEdit?faces-redirect=true";
	 //private static final String PAGE_STAY_AT_THE_SAME = null;

	private String nazwaNaprawy;

	@Inject
	ExternalContext extcontext;

	@Inject
	Flash flash;

	@EJB
	NaprawaDAO naprawaDAO;


	public List<Naprawa> getFullList() {
		return naprawaDAO.getFullList();
	}

	public List<Naprawa> getList() {
		List<Naprawa> list = null;

		Map<String, Object> searchParams = new HashMap<String, Object>();

		if (nazwaNaprawy != null && nazwaNaprawy.length() > 0) {
			searchParams.put("nazwaNaprawy", nazwaNaprawy);
		}

		list = naprawaDAO.getList(searchParams);

		return list;
	} 

}