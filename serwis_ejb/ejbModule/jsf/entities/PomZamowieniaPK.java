package jsf.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the pom_zamowienia database table.
 * 
 */
@Embeddable
public class PomZamowieniaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_zamowienia", insertable=false, updatable=false)
	private int idZamowienia;

	@Column(name="id_naprawy", insertable=false, updatable=false)
	private int idNaprawy;

	public PomZamowieniaPK() {
	}
	public int getIdZamowienia() {
		return this.idZamowienia;
	}
	public void setIdZamowienia(int idZamowienia) {
		this.idZamowienia = idZamowienia;
	}
	public int getIdNaprawy() {
		return this.idNaprawy;
	}
	public void setIdNaprawy(int idNaprawy) {
		this.idNaprawy = idNaprawy;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PomZamowieniaPK)) {
			return false;
		}
		PomZamowieniaPK castOther = (PomZamowieniaPK)other;
		return 
			(this.idZamowienia == castOther.idZamowienia)
			&& (this.idNaprawy == castOther.idNaprawy);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idZamowienia;
		hash = hash * prime + this.idNaprawy;
		
		return hash;
	}
}