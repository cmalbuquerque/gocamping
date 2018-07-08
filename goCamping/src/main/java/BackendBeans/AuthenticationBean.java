/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import Persistencia.Campsite;
import Persistencia.Manager;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Persistencia.Utilizador;
import javax.annotation.PostConstruct;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

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

    @EJB
    NewSessionBean newSessionBean;

    @PostConstruct
    private void init() {
        user = new Utilizador();
    }

    public AuthenticationBean() {
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
        System.out.println("user " + user);
        System.out.println("username " + user.getUsername());

        Utilizador user1 = newSessionBean.searchUtilizador(user.getUsername());

        if (user1 == null) {
            return "login.xhtml";
        }

        if (user1.equals(user)) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("username", username);
            Campsite campsite = new Campsite();
            if (user1.getCamper() != null) {
                session.setAttribute("isCamper", true);
                return "index.xhtml";
            } else if (user1.getManager() != null) {
                try {
                    session.setAttribute("isManager", true);
                    return "index.xhtml";
                } catch (SecurityException | IllegalStateException ex) {
                    Logger.getLogger(AuthenticationBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("an error ocurred");
            }
            return "login.xhtml";
        } else {
            System.out.println("user is not right");
        }
        return "login.xhtml";
    }

    public String logOut() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (null != session) {
            System.out.println("invalidating a session");
            session.invalidate();
        }
        return "index.xhtml";
    }
}
