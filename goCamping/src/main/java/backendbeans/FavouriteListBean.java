/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendbeans;

import persistencia.Camper;
import persistencia.Campsite;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@ManagedBean(name = "favouritelistbean")
@SessionScoped
public class FavouriteListBean implements Serializable{
    
    @ManagedProperty(value = "#{listCampsitesFav}")
    private List<Campsite> listCampsitesFav = new ArrayList<>(); 
    @ManagedProperty(value = "#{listIntCampsites}")
    private List<Integer> listIntCampsites;
    
    private NewSessionBean newSessionBean;
    
    private final static String SESSIONGETUSER = "username";
    private Camper camper;
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    
    @PostConstruct
    private void init() {
        newSessionBean = new  NewSessionBean("PUnit");
        this.listCampsitesFav= newSessionBean.listarCampsitesFavList(session.getAttribute(SESSIONGETUSER).toString());
    }
    
    public List<Campsite> getListCampsitesFav() {
        return listCampsitesFav;
    }

    public void setListCampsitesFav(List<Campsite> listCampsitesFav) {
        this.listCampsitesFav= listCampsitesFav;
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
        
    public String addFavouriteList(int campsiteID){
        newSessionBean.saveFavouriteList(session.getAttribute(SESSIONGETUSER).toString(), campsiteID);  
        listCampsitesFav= newSessionBean.listarCampsitesFavList(session.getAttribute(SESSIONGETUSER).toString());
        return "favList.xhtml" ;
    }
    
    public String removeFavouriteList(int campsiteID){
        newSessionBean.deleteFavouriteList(session.getAttribute(SESSIONGETUSER).toString(), campsiteID);
        listCampsitesFav= newSessionBean.listarCampsitesFavList(session.getAttribute(SESSIONGETUSER).toString());
        return "favList.xhtml" ;
    }
    
}