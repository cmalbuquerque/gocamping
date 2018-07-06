/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import Persistencia.Camper;
import Persistencia.Campsite;
import Persistencia.FavouriteList;
import Persistencia.FavouriteListKey;
import Persistencia.JPAExample;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
@ManagedBean(name = "favouritelistbean")
@SessionScoped
public class FavouriteListBean implements Serializable{
    
    @ManagedProperty(value = "#{listCampsitesFav}")
    private List<Campsite> listCampsitesFav = new ArrayList<>(); 
    @ManagedProperty(value = "#{listIntCampsites}")
    private List<Integer> listIntCampsites;
    
             
    @Resource
    UserTransaction utx;

    @PersistenceContext(unitName = "PUnit")
    private EntityManager em;
     
    private final String sessionGetUser = "username";
    private Camper camper;
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    
    public List<Campsite> getListCampsitesFav() {
        return listCampsitesFav;
    }

    public void setListCampsitesFav(List<Campsite> listCampsitesFav) {
        this.listCampsitesFav= listarCampsitesFavList(session.getAttribute(sessionGetUser).toString());
    }

    public List<Integer> getListIntCampsites() {
        return listIntCampsites;
    }

    public void setListIntCampsites(List<Integer> listIntCampsites) {
        this.listIntCampsites = listIntCampsites;
                
    }
    
    public Camper getCamper() {
        return camper;
    }

    public void setCamper(Camper camper) {
        this.camper = camper;
    }
    
    public FavouriteList saveFavouriteList(String camperUsername, int campsiteID) {
        FavouriteList favouriteList = new FavouriteList();
        System.out.println("new fav list");
        try {
            utx.begin();
            System.out.println("at the start of transaction");
            favouriteList.setCamperUsername(camperUsername);
            favouriteList.setCampsiteID(campsiteID);
            getEntityManager().persist(favouriteList);
            utx.commit();
            System.out.println("just after comit");
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("save didnt' work on campsite");
        }
        return favouriteList;
    }
    
    public Campsite searchCampsite(int id) {
        Campsite campsite = new Campsite();
        try {
            utx.begin();
            Query query = getEntityManager().createQuery("select c from Campsite c where c.id = :id");
            query.setParameter("id", id);
            List<Campsite> campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                campsite = (Campsite) iterator.next();
            }
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("utilizador search didn't work");
        }
        return campsite;
    }

    
    public List<Campsite> listarCampsitesFavList(String username) {
        List<FavouriteList> favouriteLists = new ArrayList<FavouriteList>();
        List<Integer> list = new ArrayList<Integer>();
        List<Campsite> campsites = new ArrayList<>();
        try {
            utx.begin();
            Query query = getEntityManager().createQuery("select c from FavouriteList as c where c.camperUsername = :username");
            query.setParameter("username", username);
            favouriteLists = query.getResultList();
            for (Iterator<FavouriteList> iterator = favouriteLists.iterator(); iterator.hasNext();) {
                Integer campsiteID = iterator.next().getCampsiteID();
                list.add(campsiteID);
            }
            System.out.println("just before commit");
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("listar campsite with manager didn't work");
        }
        for (int elem : list) {
            campsites.add(searchCampsite(elem));
        }

        return campsites;
    }

    public boolean deleteFavouriteList(String camperUsername, int campsiteID) {
        FavouriteListKey favKey = new FavouriteListKey(camperUsername, campsiteID);
        try {
            utx.begin();
            FavouriteList favouriteList = (FavouriteList) getEntityManager().find(FavouriteList.class, favKey);
            getEntityManager().remove(favouriteList);
            utx.commit();
            return true;
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("delete favourite lit pitch didn't work");
            return false;
        }
    }
        
    public String addFavouriteList(int campsiteID){
        saveFavouriteList(session.getAttribute(sessionGetUser).toString(), campsiteID);  
        listCampsitesFav= listarCampsitesFavList(session.getAttribute(sessionGetUser).toString());
        return "favList.xhtml" ;
    }
    
    public String removeFavouriteList(int campsiteID){
        deleteFavouriteList(session.getAttribute(sessionGetUser).toString(), campsiteID);
        listCampsitesFav= listarCampsitesFavList(session.getAttribute(sessionGetUser).toString());
        return "favList.xhtml" ;
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
}