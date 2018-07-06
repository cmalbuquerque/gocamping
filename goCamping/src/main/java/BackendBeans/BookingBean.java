/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import Persistencia.Camper;
import Persistencia.Campsite;
import Persistencia.Reservation;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
   
    @Resource
    UserTransaction utx;

    @PersistenceContext(unitName = "PUnit")
    private EntityManager em;
    
    private final String sessionGetUser = "username";
    private final long tresdias = 259200;
    private Reservation reservation;
    private Campsite campsite;
    private Camper camper;
   
    
    
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
        this.listBooks = listBooks;
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
        return campingCard;
    }

    public void setCampingCard(int campingCard) {
        this.campingCard = campingCard;
    }
      
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getEmail() {
        return email;
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

     public int getNIF(){
        return NIF;
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
    
    public Camper searchCamper(String name) {
        Camper user = new Camper();
        try {
            utx.begin();
            Query query = getEntityManager().createQuery("select c from Camper c where c.username = :name");
            query.setParameter("name", name);
            List<Camper> utilizador = query.getResultList();
            for (Iterator<Camper> iterator = utilizador.iterator(); iterator.hasNext();) {
                user = (Camper) iterator.next();
            }
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("camper search didn't work");
        }
        return user;
    }
    
      public Reservation saveReservation(Date startDate, Date endDate, Camper camper, Campsite campsite, int nrAdults, int nrChildren, int nrBabies, int cellphone, double totalPrice) {
        Reservation reservation = new Reservation();
        System.out.println("new Reservation");
        try {
            utx.begin();
            System.out.println("at the start of transaction");
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            reservation.setCamper(camper);
            reservation.setCampsite(campsite);
            reservation.setCellfone(cellphone);
            reservation.setNrAdults(nrAdults);
            reservation.setNrBabies(nrBabies);
            reservation.setNrChildren(nrChildren);
            reservation.setTotalPrice(totalPrice);
            getEntityManager().persist(reservation);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("save  didnt' work on reservation");
        }
        return reservation;
    }

    public List<Reservation> listarReservations(Camper camper) {
        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Reservation> list = new ArrayList<Reservation>();
        try {
            utx.begin();
            Query query = getEntityManager().createQuery("select c from Reservation as c where c.camper = :camper");
            query.setParameter("camper", camper);
            reservations = query.getResultList();
            for (Iterator<Reservation> iterator = reservations.iterator(); iterator.hasNext();) {
                Reservation reservation = (Reservation) iterator.next();
                list.add(reservation);
            }
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("listar reservation with camper didn't work");
        }
        return list;
    }

    public boolean deleteReservation(int id) {
        try {
            utx.begin();
            Reservation reservation = (Reservation) getEntityManager().find(Reservation.class, id);
            getEntityManager().remove(reservation);
            utx.commit();
            return true;
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            System.out.println("delete reservation didn't work");
            return false;
        }
    }
   
    public String view(Campsite campsiteOutro){
        this.campsite=campsiteOutro;
        Camper c = searchCamper(session.getAttribute(sessionGetUser).toString());
        this.campingCard=c.getCampsiteCard();
        this.fullName=c.getFullName();
        this.email=c.getEmail();
        this.NIF=c.getNIF();
        return "booking.xhtml";
    }
    
    public String booking (Date checkin, Date checkout){
        camper = searchCamper(session.getAttribute(sessionGetUser).toString());
        calculatePrice();
        reservation = saveReservation(checkin, checkout, camper, campsite, nrAdults, nrChildren, nrBabies, cellphone, totalPrice);
        listBooks=listarReservations(searchCamper(session.getAttribute(sessionGetUser).toString()));
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
            deleteReservation(id);
            listBooks=listarReservations(searchCamper(session.getAttribute(sessionGetUser).toString()));
            return "myReservations.xhtml";
        }
        return "myReservations.xhtml";
    }
    
    
    protected EntityManager getEntityManager() {
        return em;
    }
}
