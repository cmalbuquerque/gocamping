/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendbeans;

import persistencia.Campsite;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
    @ManagedProperty(value = "#{nif}")
    private int nif;
    @ManagedProperty(value = "#{listaCampsites}")
    private List<Campsite> listaCampsites;
    @ManagedProperty(value = "#{campingCardDiscount}")
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
    @ManagedProperty(value = "#{campingCardDiscountEdit}")
    private double campingCardDiscountEdit;

    
    private NewSessionBean newSessionBean;

    private final static String SESSIONGETUSER = "username";
    private final static String MYCAMPSITES = "myCampsites.xhtml";

    private Campsite campsite;
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    @PostConstruct
    private void init() {
        newSessionBean = new  NewSessionBean("PUnit");
        this.nif = newSessionBean.searchManager(session.getAttribute(SESSIONGETUSER).toString()).getNif();
        
    }

    public double getCampingCardDiscount() {
        return campingCardDiscount;
    }

    public void setCampingCardDiscount(double campingCardDiscount) {
        this.campingCardDiscount = campingCardDiscount;
    }

    public double getCampingCardDiscountEdit() {
        return campingCardDiscountEdit;
    }

    public void setCampingCardDiscountEdit(double campingCardDiscountEdit) {
        this.campingCardDiscountEdit = campingCardDiscountEdit;
    }

    public String getContactsEdit() {
        return contactsEdit;
    }

    public void setContactsEdit(String contactsEdit) {
        this.contactsEdit = contactsEdit;
    }

    public String getTitleEdit() {
        return titleEdit;
    }

    public String getLocationEdit() {
        return locationEdit;
    }

    public String getDescEdit() {
        return descEdit;
    }

    public double getAdultsPriceEdit() {
        return adultsPriceEdit;
    }

    public double getChildsPriceEdit() {
        return childsPriceEdit;
    }

    public double getBabiesPriceEdit() {
        return babiesPriceEdit;
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
        this.listaCampsites = listaCampsites;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
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

    public String view(Campsite campsiteOutro) {
        this.campsite = campsiteOutro;
        this.titleEdit = campsiteOutro.getTitle();
        this.locationEdit = campsiteOutro.getLocation();
        this.adultsPriceEdit = campsiteOutro.getAdultPrice();
        this.childsPriceEdit = campsiteOutro.getChildPrice();
        this.babiesPriceEdit = campsiteOutro.getBabyPrice();
        this.contactsEdit = campsiteOutro.getContact();
        this.descEdit = campsiteOutro.getDescription();
        this.campingCardDiscountEdit = campsiteOutro.getCampingCardDiscount();

        return "editCampsite.xhtml";
    }

    public String addCampsite() {
        newSessionBean.saveCampsite(title, location, adultsPrice, childsPrice, babiesPrice, contacts, desc, newSessionBean.searchManager(session.getAttribute(SESSIONGETUSER).toString()), campingCardDiscount);
        listaCampsites = newSessionBean.listarCampsite(newSessionBean.searchManager(session.getAttribute(SESSIONGETUSER).toString()));
        return MYCAMPSITES;
    }

    public String removeCampsite(int id) {
        newSessionBean.deleteCampsite(id);
        listaCampsites = newSessionBean.listarCampsite(newSessionBean.searchManager(session.getAttribute(SESSIONGETUSER).toString()));
        return MYCAMPSITES;
    }

    public String editCampsite() {
        newSessionBean.updateCampsite(campsite.getId(), titleEdit, locationEdit, adultsPriceEdit, childsPriceEdit, babiesPriceEdit, contactsEdit, descEdit, campingCardDiscountEdit);
        listaCampsites = newSessionBean.listarCampsite(newSessionBean.searchManager(session.getAttribute(SESSIONGETUSER).toString()));
        return MYCAMPSITES;
    }

}
