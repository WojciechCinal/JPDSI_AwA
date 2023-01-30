package jsf.user;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import jsf.entities.*;

@Named
@ViewScoped
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private FacesContext fCtx = FacesContext.getCurrentInstance();
	private HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);
	private RemoteClient<User> r = RemoteClient.load(session);
	
	public User getClient() {
		return r.getDetails();
	}
	
	public int getIdUser() {
		if(r != null)
			return r.getDetails().getIdUser();
		return 0;
	}
	
	public String getLogin() {
		if(r != null)
			return r.getDetails().getLogin();
		return null;
	}
	
	public String getPassword() {
		if(r != null)
			return r.getDetails().getPassword();
		return null;
	}
	
}
