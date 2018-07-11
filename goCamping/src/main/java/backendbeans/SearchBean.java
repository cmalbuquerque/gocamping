/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendbeans;

import persistencia.Campsite;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    
    @EJB
    NewSessionBean newSessionBean;
    
    
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
  
    public String showAll(){
        listaCampsites = newSessionBean.listarTodosCampsites();     
        return "results.xhtml";
    }
    
    public String search(){
        listaCampsites = newSessionBean.listarCampsite(campsiteLocation); 
        return "results.xhtml";
    }
    
   
}
