/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import GeneralStuff.Hash;
import Persistencia.Camper;
import Persistencia.Manager;
import Persistencia.Utilizador;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    
    @Resource
    UserTransaction utx;

    @PersistenceContext(unitName = "PUnit")
    private EntityManager em;
    
    @PostConstruct
    private void init() {
        if(session.getAttribute("isCamper") != null){
            Camper c = searchCamper(session.getAttribute(sessionGetUser).toString());
            this.fullName = c.getFullName();
            this.email = c.getEmail();
            this.campsiteCard = c.getCampsiteCard();
        } 
        else{
            Manager m = searchManager(session.getAttribute(sessionGetUser).toString());
            this.fullNameManager = m.getFullName();
            this.emailManager = m.getEmail();
            
        }
    }
    
    private Manager manager;
    private Camper camper;
    private final String sessionGetUser = "username";
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
    
    public Manager searchManager(String name) {
        Manager manager1 = new Manager();
        try {
            utx.begin();
            Query query = getEntityManager().createQuery("select c from Manager c where c.username = :name");
            query.setParameter("name", name);
            List<Manager> managers = query.getResultList();
            for (Iterator<Manager> iterator = managers.iterator(); iterator.hasNext();) {
                manager1 = (Manager) iterator.next();
                System.out.println(manager1.getUsername());
            }
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("manager search didn't work");
        }
        return manager1;
    }
    
    public Camper searchCamper(String name) {
        Camper user = new Camper();
        try {
            utx.begin();
            Query query = getEntityManager().createQuery("select c from Camper c where c.username = :name");
            query.setParameter("name", name);
            List<Camper> utilizador = query.getResultList();
            for (Iterator<Camper> iterator = utilizador.iterator(); iterator.hasNext();) {
                user = (Camper) iterator.next();
            }
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("camper search didn't work");
        }
        return user;
    }
    
    public Utilizador searchUtilizador(String name) {
        Utilizador user1 = new Utilizador();
        try {
            utx.begin();

            user1 = getEntityManager().find(Utilizador.class, name);
            System.out.println(user1);
            utx.commit();

        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("camper search didn't work");
        }   
        return user1;
    } 
    
    public Utilizador updateUtilizador(String username, String newPassword) {
        Utilizador user = new Utilizador();
        try {
            utx.begin();
            user = (Utilizador) getEntityManager().find(Utilizador.class, username);
            user.setUsername(username);
            user.setPassword(newPassword);
            getEntityManager().merge(user);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("update utilizador didn't work");
        }
        return user;
    }
    
    public Camper updateCamper(String username, String fullname, String email, int campingCard) {
        Camper camper = new Camper();
        try {
            utx.begin();
            camper = (Camper) getEntityManager().find(Camper.class, username);
            camper.setFullName(fullname);
            camper.setEmail(email);
            camper.setCampsiteCard(campingCard);
            getEntityManager().merge(camper);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("update camper didn't work");
        }
        return camper;
    }
    
    public Manager updateManager(String username, String fullname, String email) {
        Manager manager = new Manager();
        try {
            utx.begin();
            manager = (Manager) getEntityManager().find(Manager.class, username);
            manager.setFullName(fullname);
            manager.setEmail(email);
            System.out.println("pst" + manager);
            getEntityManager().merge(manager);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("update manager didn't work");
        }
        return manager;
    }
    
    public String editPersonalInformation() {
        camper = updateCamper(session.getAttribute(sessionGetUser).toString(), fullName, email, campsiteCard);
        return "account.xhtml";
    }
    
    public String editPersonalInformationManager() {
        manager = updateManager(session.getAttribute(sessionGetUser).toString(), fullNameManager, emailManager);
        return "account.xhtml";
    }

    public String editLoginAccess() {
        Utilizador user = searchUtilizador(session.getAttribute(sessionGetUser).toString());
        if (user.getPassword().equals(Hash.getmd5Hash(password))) {
            if (newPassword.equals(confirmationPassword)) {
                updateUtilizador(session.getAttribute(sessionGetUser).toString(), newPassword);
                System.out.println("user pass updated, from " + password + "to" + newPassword);
                return "account.xhtml";
            }
        }
        System.out.println("didn't update user pass");
        return "account.xhtml";
    }
    
   
     protected EntityManager getEntityManager() {
        return em;
    }
}
