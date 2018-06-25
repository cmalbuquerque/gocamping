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

@ManagedBean(name = "accountbean")
@SessionScoped
public class AccountBean implements Serializable{ 
    
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
    @ManagedProperty(value = "#{newPassword}")
    private String newPassword;
    
    JPAExample ex = new JPAExample(); 
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    
    public String editPersonalInformation(){
        System.out.println("DOMINO");
        System.out.println("*" + fullName);
        System.out.println("**" + email);
        System.out.println("***" + campsiteCard);
        Camper camper1 = ex.updateCamper(session.getAttribute("username").toString(), fullName, email, campsiteCard);
        return "account.xhtml";
        
    }
    
    public String editLoginAccess(){
        return "";
    }    
}
