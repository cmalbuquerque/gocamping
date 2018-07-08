/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import Persistencia.Camper;
import Persistencia.Campsite;
import Persistencia.FavouriteList;
import Persistencia.FavouriteListKey;
import Persistencia.Manager;
import Persistencia.Reservation;
import java.io.Serializable;
import Persistencia.Utilizador;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 *
 */
@Stateful
public class NewSessionBean implements Serializable {

    

    @PersistenceContext(unitName = "PUnit")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    //Camper 
    public Camper saveCamper(String username, String fullname, String email, int NIF, int campsiteCard) {
        Camper camper1 = new Camper();
        System.out.println("new camper");
        try {
            System.out.println("Username " + username +  "fullname " +  fullname + "email " +  email +  "NIF " +  NIF + " camping " + campsiteCard);
            camper1.setUsername(username);
            camper1.setFullName(fullname);
            camper1.setEmail(email);
            camper1.setNIF(NIF);
            camper1.setCampsiteCard(campsiteCard);
            getEntityManager().persist(camper1);
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("save didn't work on camper");
        }
        return camper1;
    }
    
    public Camper searchCamper(String name) {
        Camper user = new Camper();
        try {
            Query query = getEntityManager().createQuery("select c from Camper c where c.username = :name");
            query.setParameter("name", name);
            List<Camper> utilizador = query.getResultList();
            for (Iterator<Camper> iterator = utilizador.iterator(); iterator.hasNext();) {
                user = (Camper) iterator.next();
            }
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("camper search didn't work");
        }
        return user;
    }
    
    public Camper updateCamper(String username, String fullname, String email, int campingCard) {
        Camper camper = new Camper();
        try {
            camper = (Camper) getEntityManager().find(Camper.class, username);
            camper.setFullName(fullname);
            camper.setEmail(email);
            camper.setCampsiteCard(campingCard);
            getEntityManager().merge(camper);
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("update camper didn't work");
        }
        return camper;
    }
    
     public boolean deleteCamper(String username) {
        try {
            Camper camper = (Camper) getEntityManager().find(Camper.class, username);
            getEntityManager().remove(camper);
            return true;
        } catch (Exception e) {
            System.out.println("delete camper didn't work");
            return false;
        }
    }
    
    
    //Manager
    public Manager saveManager(String username, String fullName, String email, int NIF) {
        Manager manager = new Manager();
        try {
            manager.setUsername(username);
            manager.setFullName(fullName);
            manager.setEmail(email);
            manager.setNIF(NIF);
            getEntityManager().persist(manager);
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("save didn't work on manager");
        }
        return manager;
    }
    
    public Manager searchManager(String name) {
        Manager manager1 = new Manager();
        try {
            Query query = getEntityManager().createQuery("select c from Manager c where c.username = :name");
            query.setParameter("name", name);
            List<Manager> managers = query.getResultList();
            for (Iterator<Manager> iterator = managers.iterator(); iterator.hasNext();) {
                manager1 = (Manager) iterator.next();
                System.out.println(manager1.getUsername());
            }
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("manager search didn't work");
        }
        return manager1;
    }
     
    public Manager updateManager(String username, String fullname, String email) {
        Manager manager = new Manager();
        try {
            manager = (Manager) getEntityManager().find(Manager.class, username);
            manager.setFullName(fullname);
            manager.setEmail(email);
            System.out.println("pst" + manager);
            getEntityManager().merge(manager);
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("update manager didn't work");
        }
        return manager;
    } 
    
    public boolean deleteManager(String username) {
        try {
            Manager manager = (Manager) getEntityManager().find(Manager.class, username);
            getEntityManager().remove(manager);
            return true;
        } catch (Exception e) {
            System.out.println("delete manager didn't work");
            return false;
        }
    }
    
    
    //Utilizador
    public Utilizador saveUtilizador(Camper camp, Manager man, String nome, String password) {
        Utilizador utilizador = new Utilizador();
        try {
            utilizador.setUsername(nome);
            utilizador.setPassword(password);
            if (camp != null) {
                utilizador.setCamper(camp);
            } else {
                utilizador.setManager(man);
            }
            getEntityManager().merge(utilizador);
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("save didn't work on user");
        }
        return utilizador;
    }

   
    public Utilizador searchUtilizador(String name) {
        Utilizador user1 = new Utilizador();
        try {
            user1 = getEntityManager().find(Utilizador.class, name);
        } catch (SecurityException | IllegalStateException ex1) {
            Logger.getLogger(AuthenticationBean.class.getName()).log(Level.SEVERE, null, ex1);
        }
        return user1;
    }
   
    public Utilizador updateUtilizador(String username, String newPassword) {
        Utilizador user = new Utilizador();
        try {
            user = (Utilizador) getEntityManager().find(Utilizador.class, username);
            user.setUsername(username);
            user.setPassword(newPassword);
            getEntityManager().merge(user);
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("update utilizador didn't work");
        }
        return user;
    }
    
    public boolean deleteUtilizador(String username) {
        try {
            Utilizador utilizador = (Utilizador) getEntityManager().find(Utilizador.class, username);
            Camper c = utilizador.getCamper();
            Manager m = utilizador.getManager();            
            getEntityManager().remove(utilizador);
            return true;
        } catch (Exception e) {
            System.out.println("delete utilizador didn't work");
            return false;
        }
    }
    
    //Campsite

    public Campsite saveCampsite(String title, String location, double adultPrice, double childPrice, double babyPrice, String contact, String desc, Manager manager, double campingCardDiscount) {
        Campsite campsite1 = new Campsite();
        try {
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
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("save didnt' work on campsite");
        }
        return campsite1;
    }
    
    public Campsite updateCampsite(int id, String title, String location, double adultPrice, double childPrice, double babyPrice, String contact, String desc, double campingCardDiscount) {
        Campsite campsite2 = new Campsite();
        try {
            campsite2 = getEntityManager().find(Campsite.class, id);
            campsite2.setTitle(title);
            campsite2.setLocation(location);
            campsite2.setAdultPrice(adultPrice);
            campsite2.setChildPrice(childPrice);
            campsite2.setBabyPrice(babyPrice);
            campsite2.setContact(contact);
            campsite2.setDescription(desc);
            campsite2.setCampingCardDiscount(campingCardDiscount);
            getEntityManager().merge(campsite2);
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("update campsite didn't work");
        }
        return  campsite2;
    }

    public boolean deleteCampsite(int id) {
        try {
            Campsite campsite1 = getEntityManager().find(Campsite.class, id);
            getEntityManager().remove(campsite1);
            return true;
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("delete campsite didn't work");
            return false;
        }
    }
    
    
    public List<Campsite> listarCampsite(Manager manager) {
        List<Campsite> campsites = new ArrayList<>();
        List<Campsite> list = new ArrayList<>();
        try {
            Query query = getEntityManager().createQuery("select c from Campsite as c where c.manager = :manager");
            query.setParameter("manager", manager);
            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                list.add(campsite);
            }
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("listar campsite with manager didn't work");
        }
        return list;
    }
    
    public List<Campsite> listarTodosCampsites() {
        List<Campsite> campsites = new ArrayList<>();
        List<Campsite> list = new ArrayList<>();
        try {
            Query query = getEntityManager().createQuery("select c from Campsite c");

            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                list.add(campsite);
            }
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("listar campsite didn't work");
        }
        return list;
    }

    public List<Campsite> listarCampsite(String location) {
        List<Campsite> campsites = new ArrayList<>();
        List<Campsite> list = new ArrayList<>();
        try {
            Query query = getEntityManager().createQuery("select c from Campsite as c where c.location like :location");
            query.setParameter("location", "%" + location + "%");
            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                System.out.println(campsite.getId() + " \t " + campsite.getTitle() + "\t" + campsite.getLocation());
                list.add(campsite);
            }
            System.out.println("just before commit");
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("listar campsite didn't work");
        }
        return list;
    }
    
      public Campsite searchCampsite(int id) {
        Campsite campsite = new Campsite();
        try {
            Query query = getEntityManager().createQuery("select c from Campsite c where c.id = :id");
            query.setParameter("id", id);
            List<Campsite> campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                campsite = (Campsite) iterator.next();
            }
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("utilizador search didn't work");
        }
        return campsite;
    }
    
      
    //Favourite List
    public FavouriteList saveFavouriteList(String camperUsername, int campsiteID) {
        FavouriteList favouriteList = new FavouriteList();
        System.out.println("new fav list");
        try {
            favouriteList.setCamperUsername(camperUsername);
            favouriteList.setCampsiteID(campsiteID);
            getEntityManager().persist(favouriteList);
            System.out.println("just after comit");
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("save didnt' work on campsite");
        }
        return favouriteList;
    }
 
      
    public List<Campsite> listarCampsitesFavList(String username) {
        List<FavouriteList> favouriteLists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        List<Campsite> campsites = new ArrayList<>();
        try {
            Query query = getEntityManager().createQuery("select c from FavouriteList as c where c.camperUsername = :username");
            query.setParameter("username", username);
            favouriteLists = query.getResultList();
            for (Iterator<FavouriteList> iterator = favouriteLists.iterator(); iterator.hasNext();) {
                Integer campsiteID = iterator.next().getCampsiteID();
                list.add(campsiteID);
            }
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("listar campsite with manager didn't work");
        }
        for (int elem : list) {
            campsites.add(searchCampsite(elem));
        }

        return campsites;
    }

    public boolean deleteFavouriteList(String camperUsername, int campsiteID) {
        FavouriteListKey favKey = new FavouriteListKey(camperUsername, campsiteID);
        try {
            FavouriteList favouriteList = (FavouriteList) getEntityManager().find(FavouriteList.class, favKey);
            getEntityManager().remove(favouriteList);
            return true;
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("delete favourite lit pitch didn't work");
            return false;
        }
    }
    
    public Reservation saveReservation(Date startDate, Date endDate, Camper camper, Campsite campsite, int nrAdults, int nrChildren, int nrBabies, int cellphone, double totalPrice) {
        Reservation reservation = new Reservation();
        System.out.println("new Reservation");
        try {
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
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("save  didnt' work on reservation");
        }
        return reservation;
    }

    public List<Reservation> listarReservations(Camper camper) {
        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Reservation> list = new ArrayList<Reservation>();
        try {
            Query query = getEntityManager().createQuery("select c from Reservation as c where c.camper = :camper");
            query.setParameter("camper", camper);
            reservations = query.getResultList();
            for (Iterator<Reservation> iterator = reservations.iterator(); iterator.hasNext();) {
                Reservation reservation = (Reservation) iterator.next();
                list.add(reservation);
            }
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("listar reservation with camper didn't work");
        }
        return list;
    }

    public boolean deleteReservation(int id) {
        try {
            Reservation reservation = (Reservation) getEntityManager().find(Reservation.class, id);
            getEntityManager().remove(reservation);
            return true;
        } catch (IllegalStateException | SecurityException e) {
            System.out.println("delete reservation didn't work");
            return false;
        }
    }
       
    
}
