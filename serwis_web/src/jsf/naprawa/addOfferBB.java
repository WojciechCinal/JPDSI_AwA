package jsf.naprawa;

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

import jsf.dao.NaprawaDAO;
import jsf.entities.Naprawa;

@Named
@ViewScoped
public class addOfferBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_OFFER = "/pages/offer?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	

	@EJB
	NaprawaDAO naprawaDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	private Naprawa naprawa = new Naprawa();
	private String nazwa;
	private String kategoria;
	private double cena;
	
	
	public String getNazwa() {
		return nazwa;
	}


	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}


	public String getKategoria() {
		return kategoria;
	}


	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}


	public double getCena() {
		return cena;
	}


	public void setCena(double cena) {
		this.cena = cena;
	}

	public Naprawa getNaprawa() {
		return naprawa;
	}

	public void setNaprawa(Naprawa naprawa) {
		this.naprawa = naprawa;
	}

	
	public String Add() {		

		Naprawa nowaNaprawa = new Naprawa();

		nowaNaprawa.setNazwaNaprawy(this.nazwa);
		nowaNaprawa.setKategoria(this.kategoria);
		nowaNaprawa.setCena(this.cena);

		naprawaDAO.create(nowaNaprawa);

		return PAGE_OFFER;
	}

}
