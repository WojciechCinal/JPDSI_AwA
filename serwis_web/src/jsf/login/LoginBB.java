package jsf.login;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jsf.dao.*;
import jsf.entities.*;

@Named
@RequestScoped
public class LoginBB {
	private static final String PAGE_MAIN = "/pages/home";
	private static final String PAGE_LOGIN = "/public/login?faces-redirect=true";
	private static final String PAGE_LOGOUT = "/public/home?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String login;
	private String pass;


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Inject
	UserDAO userDAO;


	public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		// 1. verify login and password - get User from "database"
		User user = userDAO.getUserFromDatabase(login, pass);

		// 2. if bad login or password - stay with error info
		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Niepoprawny login lub hasło."));
			return PAGE_STAY_AT_THE_SAME;
		}

		// 3. if logged in: get User roles, save in RemoteClient and store it in session
		
		RemoteClient<User> client = new RemoteClient<User>(); //create new RemoteClient
		client.setDetails(user);

		

	    List<String> roles = userDAO.getUserRolesFromDatabase(user); //get User roles 
		
		if (roles != null) { //save roles in RemoteClient
			for (String role: roles) {
				client.getRoles().add(role);
			}
		}

	
		//store RemoteClient with request info in session (needed for SecurityFilter)
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);

		// and enter the system (now SecurityFilter will pass the request)
		
		return PAGE_MAIN;
	}
	
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		//Invalidate session
		// - all objects within session will be destroyed
		// - new session will be created (with new ID)
		session.invalidate();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Wylogowano pomyślnie."));
		return PAGE_LOGOUT;
	}
	
}