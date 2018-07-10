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
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    
   
    public Camper saveCamper(String username, String fullname, String email, int NIF, int campsiteCard) {
        Camper camper = new Camper();
        System.out.println("new camper");
        try {
            entityManager.getTransaction().begin();
            System.out.println("at the start of transaction");
            camper.setUsername(username);
            camper.setFullName(fullname);
            camper.setEmail(email);
            camper.setNIF(NIF);
            camper.setCampsiteCard(campsiteCard);
            entityManager.persist(camper);
            entityManager.getTransaction().commit();
            System.out.println("just after comit");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save didn't work on camper");
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
                user = (Camper) iterator.next();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("camper search didn't work");
        }
        return user;
    }

    public void listCamper() {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Camper c");
            List<Camper> Campers = query.getResultList();
            for (Iterator<Camper> iterator = Campers.iterator(); iterator.hasNext();) {
                Camper camper = (Camper) iterator.next();
                System.out.println(camper.getUsername() + " \t" + camper.getFullName());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("camper listing didn't work");
        }
    }

    public Camper updateCamper(String username, String fullname, String email, int campingCard) {
        Camper camper = new Camper();
        try {
            entityManager.getTransaction().begin();
            camper = (Camper) entityManager.find(Camper.class, username);
            camper.setFullName(fullname);
            camper.setEmail(email);
            camper.setCampsiteCard(campingCard);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("update camper didn't work");
        }
        return camper;
    }
    
     public List<Camper> listarTodosCampers() {
        List<Camper> campers = new ArrayList<Camper>();
        List<Camper> list = new ArrayList<Camper>();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Camper c");

            campers = query.getResultList();
            for (Iterator<Camper> iterator = campers.iterator(); iterator.hasNext();) {
                Camper utilizador = (Camper) iterator.next();
                list.add(utilizador);
            }

            System.out.println("just before commit");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("listar campsite didn't work");
        }
        return list;
    }

    public boolean deleteCamper(String username) {
        try {
            entityManager.getTransaction().begin();
            Camper camper = (Camper) entityManager.find(Camper.class, username);
            entityManager.remove(camper);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("delete camper didn't work");
            return false;
        }
    }

    public Manager saveManager(String username, String fullname, String email, int NIF) {
        Manager manager = new Manager();
        System.out.println("new manager");
        try {
            entityManager.getTransaction().begin();
            //System.out.println("at the start of transaction");
            manager.setUsername(username);
            manager.setFullName(fullname);
            manager.setEmail(email);
            manager.setNIF(NIF);
            entityManager.persist(manager);
            entityManager.getTransaction().commit();
            //System.out.println("just after comit");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save didn't work on manager");
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
                manager = (Manager) iterator.next();
                System.out.println(manager.getUsername());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("manager search didn't work");
        }
        return manager;
    }

    public void listManager() {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Manager c");
            List<Manager> Managers = query.getResultList();
            for (Iterator<Manager> iterator = Managers.iterator(); iterator.hasNext();) {
                Manager manager = (Manager) iterator.next();
                System.out.println(manager.getUsername() + " \t" + manager.getFullName());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("manager listing didn't work");
        }
    }

    public Manager updateManager(String username, String fullname, String email) {
        Manager manager = new Manager();
        try {
            entityManager.getTransaction().begin();
            manager = (Manager) entityManager.find(Manager.class, username);
            manager.setFullName(fullname);
            manager.setEmail(email);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("update manager didn't work");
        }
        return manager;
    }
    
    public List<Manager> listarTodosManagers() {
        List<Manager> managers = new ArrayList<Manager>();
        List<Manager> list = new ArrayList<Manager>();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Manager c");

            managers = query.getResultList();
            for (Iterator<Manager> iterator = managers.iterator(); iterator.hasNext();) {
                Manager utilizador = (Manager) iterator.next();
                list.add(utilizador);
            }

            System.out.println("just before commit");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("listar campsite didn't work");
        }
        return list;
    }
    
    public boolean deleteManager(String username) {
        try {
            entityManager.getTransaction().begin();
            Manager manager = (Manager) entityManager.find(Manager.class, username);
            entityManager.remove(manager);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("delete manager didn't work");
            return false;
        }
    }

    public Utilizador saveUtilizador(Camper camper, Manager manager, String username, String password) {
        Utilizador user = new Utilizador();
        System.out.println("new user");
        try {
            entityManager.getTransaction().begin();
            System.out.println("at the start of transaction");
            if (camper != null) {
                System.out.println("camper is not null");
                user.setCamper(camper);
            } else {
                System.out.println("manager is not null");
                user.setManager(manager);
            }
            user.setUsername(username);
            user.setPassword(password);
            System.out.println(user.toString());
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            System.out.println("just after comit, saved a user");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save didn't work on camper");
        }
        return user;
    }
    
    public List<Utilizador> listarTodosUtilizadores() {
        List<Utilizador> utilizadores = new ArrayList<Utilizador>();
        List<Utilizador> list = new ArrayList<Utilizador>();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Utilizador c");

            utilizadores = query.getResultList();
            for (Iterator<Utilizador> iterator = utilizadores.iterator(); iterator.hasNext();) {
                Utilizador utilizador = (Utilizador) iterator.next();
                list.add(utilizador);
            }

            System.out.println("just before commit");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("listar campsite didn't work");
        }
        return list;
    }
    
    public Utilizador updateUtilizador(String username, String newPassword) {
        Utilizador user = new Utilizador();
        try {
            entityManager.getTransaction().begin();
            user = (Utilizador) entityManager.find(Utilizador.class, username);
            user.setUsername(username);
            user.setPassword(newPassword);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("update utilizador didn't work");
        }
        return user;
    }

    public boolean deleteUtilizador(String username) {
        try {
            entityManager.getTransaction().begin();
            Utilizador utilizador = (Utilizador) entityManager.find(Utilizador.class, username);
            Camper c = utilizador.getCamper();
            Manager m = utilizador.getManager();
            entityManager.getTransaction().commit();
            
            entityManager.remove(utilizador);
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("delete utilizador didn't work");
            return false;
        }
    }

    public Utilizador searchUtilizador(String name) {
        System.out.println("In JPA name is: " + name);
        Utilizador user = new Utilizador();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Utilizador c where c.username = :name");
            query.setParameter("name", name);
            System.out.println(query);
            List<Utilizador> utilizador = query.getResultList();
            for (Iterator<Utilizador> iterator = utilizador.iterator(); iterator.hasNext();) {
                user = (Utilizador) iterator.next();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("utilizador search didn't work");
        }
        return user;
    }

    public Campsite saveCampsite(String title, String location, double adultPrice, double childPrice, double babyPrice, String contact, String desc, Manager manager, double campingCardDiscount) {
        Campsite campsite = new Campsite();
        System.out.println("new campsite");
        try {
            entityManager.getTransaction().begin();
            //System.out.println("at the start of transaction");
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
            System.out.println("just after comit");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save didnt' work on campsite");
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
                campsite = (Campsite) iterator.next();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("utilizador search didn't work");
        }
        return campsite;
    }

    public List<Campsite> listarCampsite(Manager manager) {
        List<Campsite> campsites = new ArrayList<Campsite>();
        List<Campsite> list = new ArrayList<Campsite>();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Campsite as c where c.manager = :manager");
            query.setParameter("manager", manager);
            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                list.add(campsite);
            }
            System.out.println("just before commit");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("listar campsite with manager didn't work");
        }
        return list;
    }

    public List<Campsite> listarTodosCampsites() {
        List<Campsite> campsites = new ArrayList<Campsite>();
        List<Campsite> list = new ArrayList<Campsite>();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Campsite c");

            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                list.add(campsite);
            }

            System.out.println("just before commit");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("listar campsite didn't work");
        }
        return list;
    }

    public List<Campsite> listarCampsite(String location) {
        List<Campsite> campsites = new ArrayList<Campsite>();
        List<Campsite> list = new ArrayList<Campsite>();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Campsite as c where c.location like :location");
            query.setParameter("location", "%" + location + "%");
            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                System.out.println(campsite.getId() + " \t " + campsite.getTitle() + "\t" + campsite.getLocation());
                list.add(campsite);
            }
            System.out.println("just before commit");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("listar campsite didn't work");
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
            System.out.println("update campsite didn't work");
        }
        return  campsite;
    }

    public boolean deleteCampsite(int id) {
        try {
            entityManager.getTransaction().begin();
            Campsite campsite = (Campsite) entityManager.find(Campsite.class, id);
            entityManager.remove(campsite);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("delete campsite didn't work");
            return false;
        }
    }


    public Reservation saveReservation(Date startDate, Date endDate, Camper camper, Campsite campsite, int nrAdults, int nrChildren, int nrBabies, int cellphone, double totalPrice) {
        Reservation reservation = new Reservation();
        System.out.println("new Reservation");
        try {
            entityManager.getTransaction().begin();
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
            entityManager.persist(reservation);
            entityManager.getTransaction().commit();
            System.out.println("just after comit");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save  didnt' work on reservation");
        }
        return reservation;
    }

    public List<Reservation> listarReservations(Camper camper) {
        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Reservation> list = new ArrayList<Reservation>();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Reservation as c where c.camper = :camper");
            query.setParameter("camper", camper);
            reservations = query.getResultList();
            for (Iterator<Reservation> iterator = reservations.iterator(); iterator.hasNext();) {
                Reservation reservation = (Reservation) iterator.next();
                list.add(reservation);
            }
            System.out.println("just before commit");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("listar reservation with camper didn't work");
        }
        return list;
    }

    public void updateReservation(int id, Date startDate, Date endDate) {
        try {
            entityManager.getTransaction().begin();
            Reservation reservation = (Reservation) entityManager.find(Reservation.class, id);
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("update reservation didn't work");
        }
    }

    public boolean deleteReservation(int id) {
        try {
            entityManager.getTransaction().begin();
            Reservation reservation = (Reservation) entityManager.find(Reservation.class, id);
            entityManager.remove(reservation);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("delete reservation didn't work");
            return false;
        }
    }
    
 
    public FavouriteList saveFavouriteList(String camperUsername, int campsiteID) {
        FavouriteList favouriteList = new FavouriteList();
        System.out.println("new fav list");
        try {
            entityManager.getTransaction().begin();
            System.out.println("at the start of transaction");
            favouriteList.setCamperUsername(camperUsername);
            favouriteList.setCampsiteID(campsiteID);

            entityManager.persist(favouriteList);

            entityManager.getTransaction().commit();
            System.out.println("just after comit");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save didnt' work on campsite");
        }
        return favouriteList;
    }

    public List<Campsite> listarCampsitesFavList(String username) {
        List<FavouriteList> favouriteLists = new ArrayList<FavouriteList>();
        List<Integer> list = new ArrayList<Integer>();
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
            System.out.println("just before commit");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
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
            entityManager.getTransaction().begin();
            FavouriteList favouriteList = (FavouriteList) entityManager.find(FavouriteList.class, favKey);
            entityManager.remove(favouriteList);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("delete favourite lit pitch didn't work");
            return false;
        }
    }
       
    
}
