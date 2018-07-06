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
import Persistencia.Manager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
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
    
    @Resource
    UserTransaction utx;

    @PersistenceContext(unitName = "PUnit")
    private EntityManager em;
    
    private final String sessionGetUser = "username";
    private Campsite campsite;
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

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
        this.listaCampsites = listarCampsite(searchManager(session.getAttribute("username").toString()));
    }
           
    public int getNIF() {
        return NIF;
    }

    public void setNIF(int NIF) {
        this.NIF = searchManager(session.getAttribute("username").toString()).getNIF();
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
        this.titleEdit=title;
        this.locationEdit=location;
        this.adultsPriceEdit=adultsPrice;
        this.childsPriceEdit=childsPrice;
        this.babiesPriceEdit=babiesPrice;
        this.contactsEdit=contacts;
        this.descEdit=desc;
        this.campingCardDiscountEdit=campingCardDiscount;
        return "editCampsite.xhtml";
    }
    
    public Campsite saveCampsite(String title, String location, double adultPrice, double childPrice, double babyPrice, String contact, String desc, Manager manager, double campingCardDiscount) {
        Campsite campsite1 = new Campsite();
        try {
            utx.begin();
            campsite1.setTitle(title);
            campsite1.setLocation(location);
            campsite1.setAdultPrice(adultPrice);
            campsite1.setChildPrice(childPrice);
            campsite1.setBabyPrice(babyPrice);
            campsite1.setContact(contact);
            campsite1.setDescription(desc);
            campsite1.setManager(manager);
            campsite1.setCampingCardDiscount(campingCardDiscount);
            
            getEntityManager().persist(campsite1);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("save didnt' work on campsite");
        }
        return campsite1;
    }
    
    public Campsite updateCampsite(int id, String title, String location, double adultPrice, double childPrice, double babyPrice, String contact, String desc, double campingCardDiscount) {
        Campsite campsite1 = new Campsite();
        try {
            utx.begin();
            campsite1 = getEntityManager().find(Campsite.class, id);
            campsite1.setTitle(title);
            campsite1.setLocation(location);
            campsite1.setAdultPrice(adultPrice);
            campsite1.setChildPrice(childPrice);
            campsite1.setBabyPrice(babyPrice);
            campsite1.setContact(contact);
            campsite1.setDescription(desc);
            campsite1.setCampingCardDiscount(campingCardDiscount);
            getEntityManager().merge(campsite1);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("update campsite didn't work");
        }
        return  campsite;
    }

    public boolean deleteCampsite(int id) {
        try {
            utx.begin();;
            Campsite campsite1 = getEntityManager().find(Campsite.class, id);
            getEntityManager().remove(campsite1);
            utx.commit();
            return true;
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("delete campsite didn't work");
            return false;
        }
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
    
    public List<Campsite> listarCampsite(Manager manager) {
        List<Campsite> campsites = new ArrayList<Campsite>();
        List<Campsite> list = new ArrayList<Campsite>();
        try {
            utx.begin();
            Query query = getEntityManager().createQuery("select c from Campsite as c where c.manager = :manager");
            query.setParameter("manager", manager);
            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                list.add(campsite);
            }
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("listar campsite with manager didn't work");
        }
        return list;
    }
    
    public String addCampsite(){       
        Campsite campsite1 = saveCampsite( title, location, adultsPrice, childsPrice, babiesPrice, contacts, desc, searchManager(session.getAttribute(sessionGetUser).toString()),campingCardDiscount);
        listaCampsites = listarCampsite(searchManager(session.getAttribute(sessionGetUser).toString()));
        return "myCampsites.xhtml";
    }
    
    public String removeCampsite(int id){     
        deleteCampsite(id);
        listaCampsites = listarCampsite(searchManager(session.getAttribute(sessionGetUser).toString()));
        return "myCampsites.xhtml";
    }
    
    public String editCampsite(){
        updateCampsite(campsite.getId(), titleEdit, locationEdit, adultsPriceEdit, childsPriceEdit, babiesPriceEdit, contactsEdit, descEdit, campingCardDiscountEdit);
        listaCampsites = listarCampsite(searchManager(session.getAttribute(sessionGetUser).toString()));
        return "myCampsites.xhtml";
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
}