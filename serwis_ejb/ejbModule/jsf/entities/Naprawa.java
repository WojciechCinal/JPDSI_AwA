package jsf.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the naprawa database table.
 * 
 */
@Entity
@NamedQuery(name="Naprawa.findAll", query="SELECT n FROM Naprawa n")
public class Naprawa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_naprawy")
	private int idNaprawy;

	private double cena;

	private String kategoria;

	@Column(name="nazwa_naprawy")
	private String nazwaNaprawy;

	//bi-directional many-to-one association to PomZamowienia
	@OneToMany(mappedBy="naprawa")
	private List<PomZamowienia> pomZamowienias;

	public Naprawa() {
	}

	public int getIdNaprawy() {
		return this.idNaprawy;
	}

	public void setIdNaprawy(int idNaprawy) {
		this.idNaprawy = idNaprawy;
	}

	public double getCena() {
		return this.cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getKategoria() {
		return this.kategoria;
	}

	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}

	public String getNazwaNaprawy() {
		return this.nazwaNaprawy;
	}

	public void setNazwaNaprawy(String nazwaNaprawy) {
		this.nazwaNaprawy = nazwaNaprawy;
	}

	public List<PomZamowienia> getPomZamowienias() {
		return this.pomZamowienias;
	}

	public void setPomZamowienias(List<PomZamowienia> pomZamowienias) {
		this.pomZamowienias = pomZamowienias;
	}

	public PomZamowienia addPomZamowienia(PomZamowienia pomZamowienia) {
		getPomZamowienias().add(pomZamowienia);
		pomZamowienia.setNaprawa(this);

		return pomZamowienia;
	}

	public PomZamowienia removePomZamowienia(PomZamowienia pomZamowienia) {
		getPomZamowienias().remove(pomZamowienia);
		pomZamowienia.setNaprawa(null);

		return pomZamowienia;
	}

}