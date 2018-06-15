/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import GeneralStuff.Hash;
import Persistencia.Camper;
import Persistencia.JPAExample;
import Persistencia.Manager;
import Persistencia.Utilizador;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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

@ManagedBean(name = "signupbean")
@SessionScoped
public class SignUpBean implements Serializable{ 
    
    private Utilizador user;
    private Manager manager;
    private Camper camper;
    
    @ManagedProperty(value = "#{username}")
    private String username;
    @ManagedProperty(value = "#{password}")
    private String password;
    @ManagedProperty(value ="#{fullName}")
    private String fullName;
    @ManagedProperty(value ="#{email}")
    private String email;
    @ManagedProperty(value ="#{NIF}")
    private int NIF;
    @ManagedProperty(value ="#{campsiteCard}")
    private int campsiteCard;
    
    @ManagedProperty(value ="#{flagCamper}")
    private boolean flagCamper;
    
    @ManagedProperty(value ="#{flagManager}")
    private boolean flagManager;
    
    
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Camper getCamper() {
        return camper;
    }

    public void setCamper(Camper camper) {
        this.camper = camper;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNIF() {
        return NIF;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    public int getCampsiteCard() {
        return campsiteCard;
    }

    public void setCampsiteCard(int campsiteCard) {
        this.campsiteCard = campsiteCard;
    }

    public boolean isFlagCamper() {
        return flagCamper;
    }

    public void setFlagCamper(boolean flagCamper) {
        this.flagCamper = flagCamper;
    }

    public boolean isFlagManager() {
        return flagManager;
    }

    public void setFlagManager(boolean flagManager) {
        this.flagManager = flagManager;
    }
    
    public String validate() {
        JPAExample ex = new JPAExample();
        user.setUsername(username);
        user.setPassword(password);
        Utilizador user1 = ex.searchUtilizador(user.getUsername());
        System.out.println("inserted password: " + Hash.getmd5Hash(password));
        System.out.println("database password: " + user1.getPassword());
        if (user1.equals(user)) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("username", username);
            System.out.println(session.getAttribute("username"));
            return "index.xhtml";
        }
        System.out.println("user is not right");
        return "login.xhtml";
    }
    
    public String register(){
        JPAExample ex = new JPAExample();
        if (flagCamper==true && flagManager==false){
            Camper camper1 = ex.saveCamper(username, fullName, email, NIF, campsiteCard);
            Utilizador user1 = ex.saveUtilizador(camper1, null, username, password);
            return "index.xhtml";
        }
        if (flagManager==true && flagCamper==false){
            Manager manager1 = ex.saveManager(username, fullName, email, NIF);
            Utilizador user2= ex.saveUtilizador(null, manager1, username, password);
            return "index.xhtml";
        }
        return "signup.xhtml";
    }

}
