/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Persistencia.Utilizador;
import javax.annotation.PostConstruct;
import Persistencia.JPAExample;
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

    @PostConstruct
    private void init() {
        user = new Utilizador();
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
        JPAExample ex = new JPAExample();
        user.setUsername(username);
        user.setPassword(password);
        System.out.println("user " + user);
        System.out.println("username " + user.getUsername());
        Utilizador user1 = ex.searchUtilizador(user.getUsername());
        if (user1.equals(user)) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("username", username);
            return "index.xhtml";
        }
        System.out.println("user is not right");
        return "login.xhtml";
    }

    public String logOut() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        if (null != session) {
            System.out.println("invalidating a session");
            session.invalidate();
        }
        return "index.xhtml";
    }

}
