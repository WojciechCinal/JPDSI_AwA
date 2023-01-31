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

import jsf.dao.*;
import jsf.entities.*;
import jsf.user.Client;

@Named
@RequestScoped
public class NaprawaListBB {
	 //private static final String PAGE_USER_EDIT = "userEdit?faces-redirect=true";
	 //private static final String PAGE_STAY_AT_THE_SAME = null;

	private String idNaprawy;

	@Inject
	ExternalContext extcontext;

	@Inject
	Flash flash;
	
    @Inject
    FacesContext context;
	
	@Inject
	Client client;
	
	@EJB
	NaprawaDAO naprawaDAO;
	


	public List<Naprawa> getFullList() {
		return naprawaDAO.getFullList();
	}

	public List<Naprawa> getList() {
		List<Naprawa> list = null;

		Map<String, Object> searchParams = new HashMap<String, Object>();

		if (idNaprawy != null && idNaprawy.length() > 0) {
			searchParams.put("idNaprawy", idNaprawy);
		}

		list = naprawaDAO.getList(searchParams);

		return list;
	} 
	
	
	public String edit(Naprawa naprawa) {
		flash.put("naprawa", naprawa);
        return "/pages/admin/editOffer?faces-redirect=true";
	}
}