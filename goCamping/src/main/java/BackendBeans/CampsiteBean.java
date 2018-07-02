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
import javax.faces.model.DataModel;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 *
 */
@ManagedBean(name = "campsitebean")
@SessionScoped
public class CampsiteBean implements Serializable {
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
    @ManagedProperty(value ="#{campingCardDiscount}")
    private double campingCardDiscount;
    
    @ManagedProperty(value = "#{titleEdit}")
    private String titleEdit; 
    @ManagedProperty(value = "#{locationEdit}")
    private String locationEdit;
    @ManagedProperty(value = "#{descEdit}")
    private String descEdit;
    @ManagedProperty(value = "#{adultsPriceEdit}")
    private double adultsPriceEdit;
    @ManagedProperty(value = "#{childsPriceEdit}")
    private double childsPriceEdit;
    @ManagedProperty(value = "#{babiesPriceEdit}")
    private double babiesPriceEdit;
    @ManagedProperty(value = "#{contactsEdit}")
    private String contactsEdit;
    @ManagedProperty(value ="#{campingCardDiscountEdit}")
    private double campingCardDiscountEdit;
    
    
    private Campsite campsite;
    JPAExample ex = new JPAExample();
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    public double getCampingCardDiscount() {
        return campingCardDiscount;
    }

    public void setCampingCardDiscount(double campingCardDiscount) {
        this.campingCardDiscount = campingCardDiscount;
    }

    public double getCampingCardDiscountEdit() {
        return campsite.getCampingCardDiscount();
    }

    public void setCampingCardDiscountEdit(double campingCardDiscountEdit) {
        this.campingCardDiscountEdit = campingCardDiscountEdit;
    }
    
    public String getContactsEdit() {
        return campsite.getContact();
    }

    public void setContactsEdit(String contactsEdit) {
        this.contactsEdit = contactsEdit;
    }
    
    public String getTitleEdit() {
        return campsite.getTitle();
    }

    public String getLocationEdit() {
        return campsite.getLocation();
    }

    public String getDescEdit() {
        return campsite.getDescription();
    }

    public double getAdultsPriceEdit() {
        return campsite.getAdultPrice();
    }

    public double getChildsPriceEdit() {
        return campsite.getChildPrice();
    }

    public double getBabiesPriceEdit() {
        return campsite.getBabyPrice();
    }

    public void setTitleEdit(String titleEdit) {
        this.titleEdit = titleEdit;
    }

    public void setLocationEdit(String locationEdit) {
        this.locationEdit = locationEdit;
    }

    public void setDescEdit(String descEdit) {
        this.descEdit = descEdit;
    }

    public void setAdultsPriceEdit(double adultsPriceEdit) {
        this.adultsPriceEdit = adultsPriceEdit;
    }

    public void setChildsPriceEdit(double childsPriceEdit) {
        this.childsPriceEdit = childsPriceEdit;
    }

    public void setBabiesPriceEdit(double babiesPriceEdit) {
        this.babiesPriceEdit = babiesPriceEdit;
    }

    public void setCampsite(Campsite campsite) {
        this.campsite = campsite;
    }
    
               
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
        this.NIF = ex.searchManager(session.getAttribute("username").toString()).getNIF();
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

    public Campsite getCampsite() {
        return campsite;
    }
    
    public String view(Campsite campsiteOutro){
        this.campsite=campsiteOutro;
        return "editCampsite.xhtml";
    }
    
    public String addCampsite(){       
        Campsite campsite1 = ex.saveCampsite( title, location, adultsPrice, childsPrice, babiesPrice, contacts, desc, ex.searchManager(session.getAttribute("username").toString()),campingCardDiscount);
        listaCampsites = ex.listarCampsite(ex.searchManager(session.getAttribute("username").toString()));
        return "myCampsites.xhtml";
    }
    
    public String removeCampsite(int id){     
        ex.deleteCampsite(id);
        listaCampsites = ex.listarCampsite(ex.searchManager(session.getAttribute("username").toString()));
        return "myCampsites.xhtml";
    }
    
    public String editCampsite(){
        ex.updateCampsite(campsite.getId(), titleEdit, locationEdit, adultsPriceEdit, childsPriceEdit, babiesPriceEdit, contactsEdit, descEdit, campingCardDiscountEdit);
        return "myCampsites.xhtml";
    }
}