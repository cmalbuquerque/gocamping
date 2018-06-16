/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Persistencia.Utilizador;
import GeneralStuff.Hash;
import javax.annotation.PostConstruct;
import Persistencia.JPAExample;
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
    
    JPAExample ex = new JPAExample();
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    
    
    public int getNIF() {
        return NIF;
    }

    public void setNIF(int NIF) {
        //System.out.println("pst"+ ex.getUtilizadorNIF("joana"));
        //session.getAttribute("joana")
        System.out.println("123 " + ex.getUtilizadorNIF(session.getAttribute("username").toString()));
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
       
        
        
        return "addCampsite.xhtml";
    }
    //    private Utilizador user;
//    public String validate() {
//        System.out.println("entrei na validate");
//        JPAExample ex = new JPAExample();
//        user.setUsername(username);
//        user.setPassword(password);
//        Utilizador user1 = ex.searchUtilizador(user.getUsername());
//        System.out.println("inserted password: " + Hash.getmd5Hash(password));
//        if (user1.equals(user)) {
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
//            session.setAttribute("username", username);
//            System.out.println(session.getAttribute("username"));
//            return "index.xhtml";
//        }
//        System.out.println("user is not right");
//        return "login.xhtml";
//    }
    
}