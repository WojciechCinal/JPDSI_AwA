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

	@Id
	private int id;

	private double cena;

	//bi-directional many-to-one association to Naprawa
	@ManyToOne
	@JoinColumn(name="id_naprawy")
	private Naprawa naprawa;

	//bi-directional many-to-one association to Zamowienie
	@ManyToOne
	@JoinColumn(name="id_zamowienia")
	private Zamowienie zamowienie;

	public PomZamowienia() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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