/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendbeans;

import generalstuff.Hash;
import persistencia.Camper;
import persistencia.Manager;
import persistencia.Utilizador;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class AccountBean implements Serializable {

    @ManagedProperty(value = "#{username}")
    private String username;
    @ManagedProperty(value = "#{password}")
    private String password;
    @ManagedProperty(value = "#{fullName}")
    private String fullName;
    @ManagedProperty(value = "#{email}")
    private String email;
    @ManagedProperty(value = "#{campsiteCard}")
    private int campsiteCard;
    @ManagedProperty(value = "#{newPassword}")
    private String newPassword;
    @ManagedProperty(value = "#{confirmationPassword}")
    private String confirmationPassword;
    @ManagedProperty(value = "#{fullNameManager}")
    private String fullNameManager;
    @ManagedProperty(value = "#{emailManager}")
    private String emailManager;

    private final static String ACCOUNT = "account.xhtml";
    private final static String SESSIONGETUSER = "username";

    @EJB
    NewSessionBean newSessionBean;

    @PostConstruct
    private void init() {
        if (session.getAttribute("isCamper") != null) {
            Camper c = newSessionBean.searchCamper(session.getAttribute(SESSIONGETUSER).toString());
            this.fullName = c.getFullName();
            this.email = c.getEmail();
            this.campsiteCard = c.getCampsiteCard();
        } else {
            Manager m = newSessionBean.searchManager(session.getAttribute(SESSIONGETUSER).toString());
            this.fullNameManager = m.getFullName();
            this.emailManager = m.getEmail();

        }
    }

    private Manager manager;
    private Camper camper;

    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    public int getCampsiteCard() {
        return campsiteCard;
    }

    public void setCampsiteCard(int campsiteCard) {
        this.campsiteCard = campsiteCard;
    }

    public String getFullNameManager() {
        return fullNameManager;
    }

    public void setFullNameManager(String fullNameManager) {
        this.fullNameManager = fullNameManager;
    }

    public String getEmailManager() {
        return emailManager;
    }

    public void setEmailManager(String emailManager) {
        this.emailManager = emailManager;
    }

    public String editPersonalInformation() {
        camper = newSessionBean.updateCamper(session.getAttribute(SESSIONGETUSER).toString(), fullName, email, campsiteCard);
        return ACCOUNT;
    }

    public String editPersonalInformationManager() {
        manager = newSessionBean.updateManager(session.getAttribute(SESSIONGETUSER).toString(), fullNameManager, emailManager);
        return ACCOUNT;
    }

    public String editLoginAccess() {
        Utilizador user = newSessionBean.searchUtilizador(session.getAttribute(SESSIONGETUSER).toString());
        if ((user.getPassword().equals(Hash.getmd5Hash(password))) && (newPassword.equals(confirmationPassword))) {
            newSessionBean.updateUtilizador(session.getAttribute(SESSIONGETUSER).toString(), newPassword);
            return ACCOUNT;
        }
        return ACCOUNT;
    }

}
