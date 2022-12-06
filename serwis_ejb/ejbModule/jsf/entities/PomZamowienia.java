package jsf.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pom_zamowienia database table.
 * 
 */
@Entity
@Table(name="pom_zamowienia")
@NamedQuery(name="PomZamowienia.findAll", query="SELECT p FROM PomZamowienia p")
public class PomZamowienia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PomZamowieniaPK id;

	private double cena;

	//bi-directional many-to-one association to Naprawa
	@ManyToOne
	@JoinColumn(name="id_naprawy", insertable=false, updatable=false)
	private Naprawa naprawa;

	//bi-directional many-to-one association to Zamowienie
	@ManyToOne
	@JoinColumn(name="id_zamowienia", insertable=false, updatable=false)
	private Zamowienie zamowienie;

	public PomZamowienia() {
	}

	public PomZamowieniaPK getId() {
		return this.id;
	}

	public void setId(PomZamowieniaPK id) {
		this.id = id;
	}

	public double getCena() {
		return this.cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Naprawa getNaprawa() {
		return this.naprawa;
	}

	public void setNaprawa(Naprawa naprawa) {
		this.naprawa = naprawa;
	}

	public Zamowienie getZamowienie() {
		return this.zamowienie;
	}

	public void setZamowienie(Zamowienie zamowienie) {
		this.zamowienie = zamowienie;
	}

}