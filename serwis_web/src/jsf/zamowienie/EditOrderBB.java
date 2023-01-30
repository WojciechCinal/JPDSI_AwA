package jsf.zamowienie;


import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jsf.dao.*;
import jsf.entities.*;


@Named
@ViewScoped
public class EditOrderBB implements Serializable{
	 private static final long serialVersionUID = 1L;

		private static final String PAGE_ORDER = "/pages/order?faces-redirect=true";
		private static final String PAGE_STAY_AT_THE_SAME = null;
	
	@EJB
	ZamowienieDAO zamowienieDAO;
	
	@EJB
	NaprawaDAO naprawaDAO;
	
	@EJB
	PomZamowieniaDAO pomZamowieniaDAO;
	
    @Inject
    FacesContext context;

    @Inject
    Flash flash;
	
    private Zamowienie zamowienie = new Zamowienie();
    private Zamowienie loaded = null;
    private List<Integer> doDodania;
    
    public void onLoad() throws IOException {
        loaded = (Zamowienie) flash.get("zamowienie");

        if (loaded != null) {
        	zamowienie = loaded;
        	System.out.println(zamowienie.getModel());
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
        }

    }
    

    public Zamowienie getZamowienie() {
		return zamowienie;
	}
    

	public Zamowienie getLoaded() {
		return loaded;
	}

	public void setLoaded(Zamowienie loaded) {
		this.loaded = loaded;
	}

	public void setZamowienie(Zamowienie zamowienie) {
		this.zamowienie = zamowienie;
	}


	public String saveData() {
        if (loaded == null) {
            return PAGE_STAY_AT_THE_SAME;
        }

        try {
            if (zamowienie == null) {
            	zamowienieDAO.create(zamowienie);
            } else {
            	zamowienieDAO.merge(zamowienie);
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
            return PAGE_STAY_AT_THE_SAME;
        }

        return PAGE_ORDER;
    }
	
	
	public List<Naprawa> getFullList(){
		return naprawaDAO.getFullList();
	}


	public List<Integer> getDoDodania() {
		return doDodania;
	}


	public void setDoDodania(List<Integer> doDodania) {
		this.doDodania = doDodania;
	}
	
	public String DodajNaprawe() {
		if(doDodania.size()>0) {
		for(Integer nap : doDodania) {
		PomZamowienia p = new PomZamowienia();
		Naprawa n = naprawaDAO.find(nap);
		p.setZamowienie(zamowienie);
		p.setNaprawa(n);
		p.setCena(n.getCena());
		pomZamowieniaDAO.create(p);
		}
		}
		zamowienie = zamowienieDAO.find(zamowienie.getIdZamowienia());
		flash.put("zamowienie", zamowienie);
		return "/pages/editOrder?faces-redirect=true";
	}
	
	public String UsunNaprawe(PomZamowienia a) {
		pomZamowieniaDAO.remove(a);
		zamowienie = zamowienieDAO.find(zamowienie.getIdZamowienia());
		
		flash.put("zamowienie", zamowienie);
		return "/pages/editOrder?faces-redirect=true";
	}
}
