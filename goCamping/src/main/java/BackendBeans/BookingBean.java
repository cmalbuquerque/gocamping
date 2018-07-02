/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import Persistencia.Camper;
import Persistencia.Campsite;
import Persistencia.JPAExample;
import Persistencia.Reservation;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

@ManagedBean(name = "bookingbean")
@SessionScoped
public class BookingBean implements Serializable{ 
    
    @ManagedProperty(value = "#{fullName}")
    private String fullName;
    @ManagedProperty(value = "#{email}")
    private String email;
    @ManagedProperty(value = "#{nrAdults}")
    private int nrAdults;
    @ManagedProperty(value = "#{nrChildren}")
    private int nrChildren;
    @ManagedProperty(value = "#{nrBabies}")
    private int nrBabies;
    @ManagedProperty(value = "#{NIF}")
    private int NIF;
    @ManagedProperty(value = "#{cellphone}")
    private int cellphone;
    @ManagedProperty(value = "#{campingCard}")
    private int campingCard;
    @ManagedProperty (value = "#{totalPrice}")
    private double totalPrice;
    @ManagedProperty (value="#{listBooks}")
    private List<Reservation> listBooks;
   
    
    private Date checkin;
    private Date checkout;
    private long tresdias = 259200;
    private Reservation reservation;
    private Campsite campsite;
    private Camper camper;
    private SearchBean searchBean;
    
    JPAExample ex = new JPAExample(); 
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    
    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
   
    public List<Reservation> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<Reservation> listBooks) {
        this.listBooks = ex.listarReservations(ex.searchCamper(session.getAttribute("username").toString()));
    }
    
    public int getNrBabies() {
        return nrBabies;
    }

    public void setNrBabies(int nrBabies) {
        this.nrBabies = nrBabies;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice =totalPrice;
    }
    
       
    public int getCampingCard() {
        return ex.searchCamper(session.getAttribute("username").toString()).getCampsiteCard();
    }

    public void setCampingCard(int campingCard) {
        this.campingCard = campingCard;
    }
      
    public String getFullName() {
        return ex.searchCamper(session.getAttribute("username").toString()).getFullName();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getEmail() {
        return ex.searchCamper(session.getAttribute("username").toString()).getEmail();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNrAdults() {
        return nrAdults;
    }

    public void setNrAdults(int nrAdults) {
        this.nrAdults = nrAdults;
    }

    public int getNrChildren() {
        return nrChildren;
    }

    public void setNrChildren(int nrChildren) {
        this.nrChildren = nrChildren;
    }

    public int getNIF() {
        return ex.searchCamper(session.getAttribute("username").toString()).getNIF();
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    public int getCellphone() {
        return cellphone;
    }

    public void setCellphone(int cellphone) {
        this.cellphone = cellphone;
    }

    public Campsite getCampsite() {
        return campsite;
    }

    public void setCampsite(Campsite campsite) {
        this.campsite = campsite;
    }

    public Camper getCamper() {
        return camper;
    }

    public void setCamper(Camper camper) {
        this.camper = camper;
    }
   
    public String view(Campsite campsiteOutro){
        this.campsite=campsiteOutro;
        return "booking.xhtml";
    }
    
    public String booking (Date checkin, Date checkout){
        camper = ex.searchCamper(session.getAttribute("username").toString());
        calculatePrice();
        Reservation reservation = ex.saveReservation(checkin, checkout, camper, campsite, nrAdults, nrChildren, nrBabies, cellphone, totalPrice);
        calculatePrice();
        listBooks=ex.listarReservations(ex.searchCamper(session.getAttribute("username").toString()));
        return "payment.xhtml";
    }
    
    public double calculatePrice(){
        double discount = (campsite.getAdultPrice()*(campsite.getCampingCardDiscount()))/100;
        totalPrice = (nrAdults*campsite.getAdultPrice())+(nrChildren*campsite.getChildPrice())+(nrBabies+campsite.getBabyPrice())-discount;
        return totalPrice;
    }
    
    public String cancelReservation(int id, Date startDate){
        LocalDate localDate= LocalDate.now();
        long secsCheckIn=(startDate.getTime()/1000);
        ZoneId zoneId = ZoneId.systemDefault();
        long secsAtual = localDate.atStartOfDay(zoneId).toEpochSecond();
        //so cancela a reserva se for feita 3 dias antes
        long difSecs= secsCheckIn-secsAtual;
        if (difSecs>tresdias){
            ex.deleteReservation(id);
            listBooks=ex.listarReservations(ex.searchCamper(session.getAttribute("username").toString()));
            return "myReservations.xhtml";
        }
        return "myReservations.xhtml";
    }
    
}
