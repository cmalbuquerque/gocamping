/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import Persistencia.Camper;
import Persistencia.Campsite;
import Persistencia.JPAExample;
import java.io.Serializable;
import java.util.List;
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
    private List<Campsite> listCampsitesFav; 
    
    private Camper camper;
    JPAExample ex = new JPAExample();
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    
    public List<Campsite> getListCampsitesFav() {
        return listCampsitesFav;
    }

    public void setListCampsitesFav(List<Campsite> listCampsitesFav) {
        //this.listCampsitesFav = ex.listarCampsitesFavList(session.getAttribute("username").toString());
        this.listCampsitesFav=listCampsitesFav;
    }

    public Camper getCamper() {
        return camper;
    }

    public void setCamper(Camper camper) {
        this.camper = camper;
    }
        
    public String addFavouriteList(int campsiteID){
        ex.saveFavouriteList(session.getAttribute("username").toString(), campsiteID);
        //listCampsitesFav= ex.listarCampsitesFavList(session.getAttribute("username").toString());
        
        return "favList.xhtml" ;
    } 
    
    
}