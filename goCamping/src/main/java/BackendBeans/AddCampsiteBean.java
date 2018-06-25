/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import Persistencia.Campsite;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Persistencia.JPAExample;
import java.util.List;
import javax.faces.bean.ManagedProperty;
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
@ManagedBean(name = "addcampsitebean")
@SessionScoped
public class AddCampsiteBean implements Serializable {
    @ManagedProperty(value = "#{title}")
    private String title;
    @ManagedProperty(value = "#{location}")
    private String location;
    @ManagedProperty(value = "#{desc}")
    private String desc;
    @ManagedProperty(value = "#{adultsPrice}")
    private double adultsPrice;
    @ManagedProperty(value = "#{childsPrice}")
    private double childsPrice;
    @ManagedProperty(value = "#{babiesPrice}")
    private double babiesPrice;
    @ManagedProperty(value = "#{contacts}")
    private String contacts;
    @ManagedProperty(value = "#{NIF}")
    private int NIF;
    @ManagedProperty(value = "#{listaCampsites}")
    private List<Campsite> listaCampsites;
        
    JPAExample ex = new JPAExample();
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

               
    public List<Campsite> getListaCampsites() {
        return listaCampsites;
    }

    public void setListaCampsites(List<Campsite> listaCampsites) {
        this.listaCampsites = ex.listarCampsite(ex.searchManager(session.getAttribute("username").toString()));
    }
           
    public int getNIF() {
        return NIF;
    }

    public void setNIF(int NIF) {
        this.NIF = ex.getUtilizadorNIF(session.getAttribute("username").toString());
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getAdultsPrice() {
        return adultsPrice;
    }

    public void setAdultsPrice(double adultsPrice) {
        this.adultsPrice = adultsPrice;
    }

    public double getChildsPrice() {
        return childsPrice;
    }

    public void setChildsPrice(double childsPrice) {
        this.childsPrice = childsPrice;
    }

    public double getBabiesPrice() {
        return babiesPrice;
    }

    public void setBabiesPrice(double babiesPrice) {
        this.babiesPrice = babiesPrice;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
    
    public String addCampsite(){       
        Campsite campsite1 = ex.saveCampsite( title, location, adultsPrice, childsPrice, babiesPrice, contacts, desc, ex.searchManager(session.getAttribute("username").toString()));
        listaCampsites = ex.listarCampsite(ex.searchManager(session.getAttribute("username").toString()));
        return "myCampsites.xhtml";
    }
    
    public String removeCampsite(int id){     
        ex.deleteCampsite(id);
        listaCampsites = ex.listarCampsite(ex.searchManager(session.getAttribute("username").toString()));
        return "myCampsites.xhtml";
    }
}