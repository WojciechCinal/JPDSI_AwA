package jsf.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the zamowienie database table.
 * 
 */
@Entity
@NamedQuery(name="Zamowienie.findAll", query="SELECT z FROM Zamowienie z")
public class Zamowienie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_zamowienia")
	private int idZamowienia;

	@Temporal(TemporalType.DATE)
	private Date data;

	private String marka;

	private String model;

	@Column(name="nr_rejestracyjny")
	private String nrRejestracyjny;

	//bi-directional many-to-one association to PomZamowienia
	@OneToMany(mappedBy="zamowienie")
	private List<PomZamowienia> pomZamowienias;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	public Zamowienie() {
	}

	public int getIdZamowienia() {
		return this.idZamowienia;
	}

	public void setIdZamowienia(int idZamowienia) {
		this.idZamowienia = idZamowienia;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMarka() {
		return this.marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNrRejestracyjny() {
		return this.nrRejestracyjny;
	}

	public void setNrRejestracyjny(String nrRejestracyjny) {
		this.nrRejestracyjny = nrRejestracyjny;
	}

	public List<PomZamowienia> getPomZamowienias() {
		return this.pomZamowienias;
	}

	public void setPomZamowienias(List<PomZamowienia> pomZamowienias) {
		this.pomZamowienias = pomZamowienias;
	}

	public PomZamowienia addPomZamowienia(PomZamowienia pomZamowienia) {
		getPomZamowienias().add(pomZamowienia);
		pomZamowienia.setZamowienie(this);

		return pomZamowienia;
	}

	public PomZamowienia removePomZamowienia(PomZamowienia pomZamowienia) {
		getPomZamowienias().remove(pomZamowienia);
		pomZamowienia.setZamowienie(null);

		return pomZamowienia;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}