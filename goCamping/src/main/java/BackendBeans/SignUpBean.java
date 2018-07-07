/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import Persistencia.Camper;
import Persistencia.Manager;
import Persistencia.Utilizador;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    @ManagedProperty(value = "#{NIF}")
    private int NIF;
    @ManagedProperty(value = "#{campsiteCard}")
    private int campsiteCard;

    @ManagedProperty(value = "#{flagCamper}")
    private boolean flagCamper;

    @ManagedProperty(value = "#{flagManager}")
    private boolean flagManager;

    @Resource
    UserTransaction utx;

    @PersistenceContext(unitName = "PUnit")
    private EntityManager em;

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

    public Camper saveCamper(String Uname, String fulname, String mail, int nif, int campsitecard) {
        Camper camperino = new Camper();
        try {
            camperino.setUsername(Uname);
            camperino.setFullName(fulname);
            camperino.setEmail(mail);
            camperino.setNIF(nif);
            camperino.setCampsiteCard(campsitecard);
            utx.begin();
            getEntityManager().persist(camperino);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("save camper did't work");
        }
        return camperino;
    }

    public Utilizador saveUtilizador(Camper camp, Manager man, String nome, String password) {
        Utilizador user = new Utilizador();

        try {
            utx.begin();
            user.setUsername(nome);
            user.setPassword(password);
            if (camp != null) {
                user.setCamper(camp);
            } else {
                System.out.println("setting manager");
                user.setManager(man);//getEntityManager().find(Manager.class, man.getUsername()));
            }

            getEntityManager().merge(user);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("save didn't work on user");
        }
        return user;
    }

    private Manager saveManager(String username, String fullName, String email, int NIF) {
        Manager manager = new Manager();
        try {
            utx.begin();
            manager.setUsername(username);
            manager.setFullName(fullName);
            manager.setEmail(email);
            manager.setNIF(NIF);
            getEntityManager().persist(manager);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("save didn't work on manager");
        }
        return manager;
    }

    public Utilizador SearchUtilizador(String name) {
        Utilizador user1 = new Utilizador();
        try {
            utx.begin();

            user1 = getEntityManager().find(Utilizador.class, name);
            System.out.println(user1);
            utx.commit();

        } catch (SecurityException | IllegalStateException ex1) {
            Logger.getLogger(AuthenticationBean.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (NotSupportedException ex1) {
            Logger.getLogger(AuthenticationBean.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (SystemException ex1) {
            Logger.getLogger(AuthenticationBean.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (RollbackException ex1) {
            Logger.getLogger(AuthenticationBean.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (HeuristicMixedException ex1) {
            Logger.getLogger(AuthenticationBean.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (HeuristicRollbackException ex1) {
            Logger.getLogger(AuthenticationBean.class.getName()).log(Level.SEVERE, null, ex1);
        }
        return user1;
    }

    public String register() {
        if (flagCamper == true && flagManager == false) {
            Camper camper1 = saveCamper(username, fullName, email, NIF, campsiteCard);
            Utilizador user1 = saveUtilizador(camper1, null, username, password);
            return "login.xhtml";
        }
        if (flagManager == true && flagCamper == false) {
            Manager manager1 = saveManager(username, fullName, email, NIF);
            Utilizador user2 = saveUtilizador(null, manager1, username, password);
            return "login.xhtml";
        }
        return "signup.xhtml";
    }

    protected EntityManager getEntityManager() {
        return em;
    }

}
