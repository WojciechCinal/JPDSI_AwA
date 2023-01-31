package jsf.zamowienie;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;

import jsf.dao.*;
import jsf.entities.*;
import jsf.user.*;


@Named
@RequestScoped
public class ZamowienieListBB {
	 //private static final String PAGE_USER_EDIT = "userEdit?faces-redirect=true";
	 //private static final String PAGE_STAY_AT_THE_SAME = null;

	private String idZamowienia;
	private Double suma;
 //   private LazyDataModel<Zamowienie> lazyModel;

	@Inject
	ExternalContext extcontext;

	@Inject
	Flash flash;
	
    @Inject
    FacesContext context;
	
	@Inject
	Client client;
	
	@EJB
	ZamowienieDAO zamowienieDAO;
	
	@EJB
	UserDAO userDAO;
	
	@EJB
	NaprawaDAO naprawaDAO;
	
	@EJB
	PomZamowieniaDAO pomzamowieniaDAO;
	
	@EJB
	PomZamowieniaPKDAO pomzamowieniaPKDAO;
	

	public List<Zamowienie> getFullList() {
		return zamowienieDAO.getFullList();
	}

	public List<Zamowienie> getList() {
		List<Zamowienie> list = null;

		Map<String, Object> searchParams = new HashMap<String, Object>();

		if (idZamowienia != null && idZamowienia.length() > 0) {
			searchParams.put("idZamowienia", idZamowienia);
		}

		list = zamowienieDAO.getList(searchParams);

		return list;
	} 
 
	public String Add() {
		
		Zamowienie noweZamowienie = new Zamowienie();
		
		User u = new User();
		u.setIdUser(client.getIdUser());
		
		noweZamowienie.setUser(u);
		
		noweZamowienie.setData(new Date());
		noweZamowienie.setMarka("b/d");
		noweZamowienie.setModel("b/d");
		noweZamowienie.setNrRejestracyjny("b/d");
		
		zamowienieDAO.create(noweZamowienie);
		
		return "/pages/order.jsf?faces-redirect=true";
	}	
	
	public String resetsuma() {
		suma = 0.0;
		return null;
	}
	
	public String addsuma(Double x) {
		suma += x;
		return null;
	}
	
	public Double getsuma() {
		return suma;
	}

	public String usunZamowienie(Zamowienie z) {
		zamowienieDAO.remove(z);
		
		return "/pages/order?faces-redirect=true";
	}
	
	public String edit(Zamowienie zamowienie){
        flash.put("zamowienie", zamowienie);
        return "/pages/editOrder?faces-redirect=true";
    }
	

}