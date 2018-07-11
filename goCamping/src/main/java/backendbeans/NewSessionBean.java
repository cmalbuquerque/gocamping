/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendbeans;

import persistencia.Camper;
import persistencia.Campsite;
import persistencia.FavouriteList;
import persistencia.FavouriteListKey;
import persistencia.Manager;
import persistencia.Reservation;
import java.io.Serializable;
import persistencia.Utilizador;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 *
 */
@Singleton

public class NewSessionBean implements Serializable {


    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }    
    
    public NewSessionBean() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PUnit");
        this.entityManager = emf.createEntityManager();
    }


    public NewSessionBean(String unit) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unit);
        this.entityManager = emf.createEntityManager();
    }

    @PostConstruct
    public void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PUnit");
        this.entityManager = emf.createEntityManager();
    }
    //Camper 
    
   
    public Camper saveCamper(String username, String fullname, String email, int nif, int campsiteCard) {
        Camper camper = new Camper();
        try {
            entityManager.getTransaction().begin();
            camper.setUsername(username);
            camper.setFullName(fullname);
            camper.setEmail(email);
            camper.setNif(nif);
            camper.setCampsiteCard(campsiteCard);
            entityManager.persist(camper);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return camper;
    }

    public Camper searchCamper(String name) {
        Camper user = new Camper();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Camper c where c.username = :name");
            query.setParameter("name", name);
            List<Camper> utilizador = query.getResultList();
            for (Iterator<Camper> iterator = utilizador.iterator(); iterator.hasNext();) {
                user = iterator.next();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return user;
    }



    public Camper updateCamper(String username, String fullname, String email, int campingCard) {
        Camper camper = new Camper();
        try {
            entityManager.getTransaction().begin();
            camper = entityManager.find(Camper.class, username);
            camper.setFullName(fullname);
            camper.setEmail(email);
            camper.setCampsiteCard(campingCard);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return camper;
    }
    
     public List<Camper> listarTodosCampers() {
        List<Camper> campers = new ArrayList<>();
        List<Camper> list = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Camper c");

            campers = query.getResultList();
            for (Iterator<Camper> iterator = campers.iterator(); iterator.hasNext();) {
                Camper utilizador =  iterator.next();
                list.add(utilizador);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return list;
    }

    public boolean deleteCamper(String username) {
        try {
            entityManager.getTransaction().begin();
            Camper camper = entityManager.find(Camper.class, username);
            entityManager.remove(camper);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    public Manager saveManager(String username, String fullname, String email, int nif) {
        Manager manager = new Manager();
        try {
            entityManager.getTransaction().begin();
            manager.setUsername(username);
            manager.setFullName(fullname);
            manager.setEmail(email);
            manager.setNif(nif);
            entityManager.persist(manager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return manager;
    }

    public Manager searchManager(String name) {
        Manager manager = new Manager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Manager c where c.username = :name");
            query.setParameter("name", name);
            List<Manager> managers = query.getResultList();
            for (Iterator<Manager> iterator = managers.iterator(); iterator.hasNext();) {
                manager = iterator.next();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return manager;
    }



    public Manager updateManager(String username, String fullname, String email) {
        Manager manager = new Manager();
        try {
            entityManager.getTransaction().begin();
            manager = entityManager.find(Manager.class, username);
            manager.setFullName(fullname);
            manager.setEmail(email);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return manager;
    }
    
    public List<Manager> listarTodosManagers() {
        List<Manager> managers = new ArrayList<>();
        List<Manager> list = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Manager c");
            managers = query.getResultList();
            for (Iterator<Manager> iterator = managers.iterator(); iterator.hasNext();) {
                Manager utilizador = iterator.next();
                list.add(utilizador);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return list;
    }
    
    public boolean deleteManager(String username) {
        try {
            entityManager.getTransaction().begin();
            Manager manager = entityManager.find(Manager.class, username);
            entityManager.remove(manager);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    public Utilizador saveUtilizador(Camper camper, Manager manager, String username, String password) {
        Utilizador user = new Utilizador();
        try {
            entityManager.getTransaction().begin();
            if (camper != null) {
                user.setCamper(camper);
            } else {
                user.setManager(manager);
            }
            user.setUsername(username);
            user.setPassword(password);
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return user;
    }
    
    public List<Utilizador> listarTodosUtilizadores() {
        List<Utilizador> utilizadores = new ArrayList<>();
        List<Utilizador> list = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Utilizador c");
            utilizadores = query.getResultList();
            for (Iterator<Utilizador> iterator = utilizadores.iterator(); iterator.hasNext();) {
                Utilizador utilizador = iterator.next();
                list.add(utilizador);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return list;
    }
    
    public Utilizador updateUtilizador(String username, String newPassword) {
        Utilizador user = new Utilizador();
        try {
            entityManager.getTransaction().begin();
            user = entityManager.find(Utilizador.class, username);
            user.setUsername(username);
            user.setPassword(newPassword);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return user;
    }

    public boolean deleteUtilizador(String username) {
        try {
            entityManager.getTransaction().begin();
            Utilizador utilizador = entityManager.find(Utilizador.class, username);
            entityManager.getTransaction().commit();
            
            entityManager.remove(utilizador);
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    public Utilizador searchUtilizador(String name) {
        Utilizador user = new Utilizador();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Utilizador c where c.username = :name");
            query.setParameter("name", name);
            List<Utilizador> utilizador = query.getResultList();
            for (Iterator<Utilizador> iterator = utilizador.iterator(); iterator.hasNext();) {
                user = iterator.next();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return user;
    }

    public Campsite saveCampsite(String title, String location, double adultPrice, double childPrice, double babyPrice, String contact, String desc, Manager manager, double campingCardDiscount) {
        Campsite campsite = new Campsite();
        try {
            entityManager.getTransaction().begin();
            campsite.setTitle(title);
            campsite.setLocation(location);
            campsite.setAdultPrice(adultPrice);
            campsite.setChildPrice(childPrice);
            campsite.setBabyPrice(babyPrice);
            campsite.setContact(contact);
            campsite.setDescription(desc);
            campsite.setManager(manager);
            campsite.setCampingCardDiscount(campingCardDiscount);
            entityManager.persist(campsite);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return campsite;
    }

    public Campsite searchCampsite(int id) {
        Campsite campsite = new Campsite();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Campsite c where c.id = :id");
            query.setParameter("id", id);
            List<Campsite> campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                campsite = iterator.next();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return campsite;
    }

    public List<Campsite> listarCampsite(Manager manager) {
        List<Campsite> campsites = new ArrayList<>();
        List<Campsite> list = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Campsite as c where c.manager = :manager");
            query.setParameter("manager", manager);
            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite =  iterator.next();
                list.add(campsite);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return list;
    }
    
    public List<Campsite> listarCampsiteREST(String name) {
        Manager manager = new Manager();
        Query query = entityManager.createQuery("select c from Manager c where c.username = :name");
        query.setParameter("name", name);
        List<Manager> managers = query.getResultList();
        for (Iterator<Manager> iterator = managers.iterator(); iterator.hasNext();) {
            manager = iterator.next();
        }

        Query query2 = entityManager.createQuery("select c from Campsite c where c.manager = :manager");
        query2.setParameter("manager", manager);
        List<Campsite> campsites = query2.getResultList();
        return campsites;
    }

    public List<Campsite> listarTodosCampsites() {
        List<Campsite> campsites = new ArrayList<>();
        List<Campsite> list = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Campsite c");

            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = iterator.next();
                list.add(campsite);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return list;
    }

    public List<Campsite> listarCampsite(String location) {
        List<Campsite> campsites = new ArrayList<>();
        List<Campsite> list = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Campsite as c where c.location like :location");
            query.setParameter("location", "%" + location + "%");
            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = iterator.next();
                list.add(campsite);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return list;
    }

    public Campsite updateCampsite(int id, String title, String location, double adultPrice, double childPrice, double babyPrice, String contact, String desc, double campingCardDiscount) {
        Campsite campsite = new Campsite();
        try {
            entityManager.getTransaction().begin();
            campsite = entityManager.find(Campsite.class, id);
            campsite.setTitle(title);
            campsite.setLocation(location);
            campsite.setAdultPrice(adultPrice);
            campsite.setChildPrice(childPrice);
            campsite.setBabyPrice(babyPrice);
            campsite.setContact(contact);
            campsite.setDescription(desc);
            campsite.setCampingCardDiscount(campingCardDiscount);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return  campsite;
    }

    public boolean deleteCampsite(int id) {
        try {
            entityManager.getTransaction().begin();
            Campsite campsite = entityManager.find(Campsite.class, id);
            entityManager.remove(campsite);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return false;
        }
    }


    public Reservation saveReservation(Date startDate, Date endDate, Camper camper, Campsite campsite, int nrAdults, int nrChildren, int nrBabies, int cellphone, double totalPrice) {
        Reservation reservation = new Reservation();
        try {
            entityManager.getTransaction().begin();
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            reservation.setCamper(camper);
            reservation.setCampsite(campsite);
            reservation.setCellfone(cellphone);
            reservation.setNrAdults(nrAdults);
            reservation.setNrBabies(nrBabies);
            reservation.setNrChildren(nrChildren);
            reservation.setTotalPrice(totalPrice);
            entityManager.persist(reservation);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return reservation;
    }

    public List<Reservation> listarReservations(Camper camper) {
        List<Reservation> reservations = new ArrayList<>();
        List<Reservation> list = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Reservation as c where c.camper = :camper");
            query.setParameter("camper", camper);
            reservations = query.getResultList();
            for (Iterator<Reservation> iterator = reservations.iterator(); iterator.hasNext();) {
                Reservation reservation =  iterator.next();
                list.add(reservation);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return list;
    }

    public void updateReservation(int id, Date startDate, Date endDate) {
        try {
            entityManager.getTransaction().begin();
            Reservation reservation =  entityManager.find(Reservation.class, id);
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public boolean deleteReservation(int id) {
        try {
            entityManager.getTransaction().begin();
            Reservation reservation = entityManager.find(Reservation.class, id);
            entityManager.remove(reservation);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return false;
        }
    }
    
 
    public FavouriteList saveFavouriteList(String camperUsername, int campsiteID) {
        FavouriteList favouriteList = new FavouriteList();
        try {
            entityManager.getTransaction().begin();
            favouriteList.setCamperUsername(camperUsername);
            favouriteList.setCampsiteID(campsiteID);

            entityManager.persist(favouriteList);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return favouriteList;
    }

    public List<Campsite> listarCampsitesFavList(String username) {
        List<FavouriteList> favouriteLists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        List<Campsite> campsites = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from FavouriteList as c where c.camperUsername = :username");
            query.setParameter("username", username);
            favouriteLists = query.getResultList();
            for (Iterator<FavouriteList> iterator = favouriteLists.iterator(); iterator.hasNext();) {
                Integer campsiteID = iterator.next().getCampsiteID();
                list.add(campsiteID);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        for (int elem : list) {
            campsites.add(searchCampsite(elem));
        }

        return campsites;
    }

    public boolean deleteFavouriteList(String camperUsername, int campsiteID) {
        FavouriteListKey favKey = new FavouriteListKey(camperUsername, campsiteID);
        try {
            entityManager.getTransaction().begin();
            FavouriteList favouriteList = entityManager.find(FavouriteList.class, favKey);
            entityManager.remove(favouriteList);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return false;
        }
    }
       
    
}
