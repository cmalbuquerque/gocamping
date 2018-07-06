/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import Persistencia.Campsite;
import Persistencia.JPAExample;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@ManagedBean(name = "searchbean")
@SessionScoped
public class SearchBean implements Serializable{
    
    @ManagedProperty(value = "#{campsiteLocation}")
    private String campsiteLocation;
    @ManagedProperty(value = "#{listaCampsites}")
    private List<Campsite> listaCampsites; 
    
    @ManagedProperty(value = "#{checkin}")
    private Date checkin;
    
   @ManagedProperty(value = "#{checkout}")
    private Date checkout;
    
            
    @Resource
    UserTransaction utx;

    @PersistenceContext(unitName = "PUnit")
    private EntityManager em;
    
    
    public String getCampsiteLocation() {
        return campsiteLocation;
    }

    public void setCampsiteLocation(String campsiteLocation) {
        this.campsiteLocation = campsiteLocation;
    }


    public List<Campsite> getListaCampsites() {
        return listaCampsites;
    }

    public void setListaCampsites(List<Campsite> listaCampsites) {
        this.listaCampsites = listaCampsites;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }
    
    public List<Campsite> listarTodosCampsites() {
        List<Campsite> campsites = new ArrayList<Campsite>();
        List<Campsite> list = new ArrayList<Campsite>();
        try {
            utx.begin();
            Query query = getEntityManager().createQuery("select c from Campsite c");

            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                list.add(campsite);
            }
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("listar campsite didn't work");
        }
        return list;
    }

    public List<Campsite> listarCampsite(String location) {
        List<Campsite> campsites = new ArrayList<Campsite>();
        List<Campsite> list = new ArrayList<Campsite>();
        try {
            utx.begin();
            Query query = getEntityManager().createQuery("select c from Campsite as c where c.location like :location");
            query.setParameter("location", "%" + location + "%");
            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                System.out.println(campsite.getId() + " \t " + campsite.getTitle() + "\t" + campsite.getLocation());
                list.add(campsite);
            }
            System.out.println("just before commit");
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("listar campsite didn't work");
        }
        return list;
    }
  
    public String showAll(){
        listaCampsites = listarTodosCampsites();     
        return "results.xhtml";
    }
    
    public String search(){
        listaCampsites = listarCampsite(campsiteLocation); 
        return "results.xhtml";
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
}
