/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendbeans;

import persistencia.Camper;
import persistencia.Manager;
import persistencia.Utilizador;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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
public class SignUpBean implements Serializable {

    private Utilizador user;
    private Manager manager;
    private Camper camper;

    @ManagedProperty(value = "#{username}")
    private String username;
    @ManagedProperty(value = "#{password}")
    private String password;
    @ManagedProperty(value = "#{fullName}")
    private String fullName;
    @ManagedProperty(value = "#{email}")
    private String email;
    @ManagedProperty(value = "#{nif}")
    private int nif;
    @ManagedProperty(value = "#{campsiteCard}")
    private int campsiteCard;

    @ManagedProperty(value = "#{flagCamper}")
    private boolean flagCamper;

    @ManagedProperty(value = "#{flagManager}")
    private boolean flagManager;

    private NewSessionBean newSessionBean ;
    

    @PostConstruct
    public void init() {
        user = new Utilizador();
        newSessionBean = new  NewSessionBean("PUnit");
 
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

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
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

    

    public String register() {
        if (flagCamper && !flagManager) {
            Camper camper1 = newSessionBean.saveCamper(username, fullName, email, nif, campsiteCard);
            newSessionBean.saveUtilizador(camper1, null, username, password);
            return "login.xhtml";
        }
        if (flagManager && !flagCamper) {
            Manager manager1 = newSessionBean.saveManager(username, fullName, email, nif);
            newSessionBean.saveUtilizador(null, manager1, username, password);
            return "login.xhtml";
        }
        return "signup.xhtml";
    }


}
