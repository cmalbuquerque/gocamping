/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendbeans;

import persistencia.Campsite;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import persistencia.Utilizador;
import javax.annotation.PostConstruct;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 *
 */
@ManagedBean(name = "authenticationbean")
@SessionScoped
public class AuthenticationBean implements Serializable {

    private Utilizador user;
    @ManagedProperty(value = "#{username}")
    private String username;
    @ManagedProperty(value = "#{password}")
    private String password;

    private final static String INDEX = "index.xhtml";
    private final static String LOGIN = "login.xhtml";

    
    private NewSessionBean newSessionBean;

    @PostConstruct
    private void init() {
        user = new Utilizador();
        newSessionBean = new  NewSessionBean("PUnit");
    }

    public AuthenticationBean() {
        //default Constructor
    }

    public Utilizador getUser() {
        return user;
    }

    public void setUser(Utilizador user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String validate() {
        user.setUsername(username);
        user.setPassword(password);
        Utilizador user1 = newSessionBean.searchUtilizador(user.getUsername());
        if (user1 == null) {
            return LOGIN;
        }
        if (user1.equals(user)) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("username", username);
            if (user1.getCamper() != null) {
                session.setAttribute("isCamper", true);
                return INDEX;
            } else if (user1.getManager() != null) {
                try {
                    session.setAttribute("isManager", true);
                    return INDEX;
                } catch (SecurityException | IllegalStateException ex) {
                    Logger.getLogger(AuthenticationBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return LOGIN;
    }

    public String logOut() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (null != session) {
            session.invalidate();
        }
        return INDEX;
    }
}
